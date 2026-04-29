# Discovery & Design System — SisLocação
> Fundamentação de escolhas de frontend e guia de identidade visual

---

## 1. Discovery: React vs Angular

### 1.1 Critérios de Avaliação

| Critério | Peso | React | Angular |
|----------|------|-------|---------|
| Curva de aprendizado | Alta | ✅ Menor — JSX é natural | ⚠️ Maior — TypeScript obrigatório, decorators, modules |
| Ecossistema de UI | Alta | ✅ Imenso (shadcn, MUI, Ant, etc.) | ✅ Angular Material maduro |
| Compatibilidade com Soft UI Dashboard (Creative Tim) | Alta | ✅ Template oficial existe em React | ⚠️ Versão Angular não oficial |
| Geração de PDF no cliente | Média | ✅ PDFMake, react-pdf, jsPDF | ✅ Suporte equivalente |
| TypeScript | Média | ✅ Opcional mas recomendado | ✅ Nativo |
| Tamanho do bundle | Baixa | ✅ Menor com Vite | ⚠️ Bundle maior por padrão |
| Manutenibilidade (projeto pessoal) | Alta | ✅ Mais flexível, menos boilerplate | ⚠️ Mais estruturado mas mais verboso |
| Integração com REST API (Spring Boot) | Alta | ✅ Axios / TanStack Query | ✅ HttpClient nativo |

### 1.2 Decisão Recomendada: **React + TypeScript + Vite**

**Justificativa:**
- O template de referência (Soft UI Dashboard) tem versão **oficial em React** da Creative Tim
- Ecossistema muito mais rico para composição de UI customizada
- Menor overhead de configuração para um projeto pessoal/familiar
- TanStack Query simplifica muito o gerenciamento de estado de servidor (listagens, cache)
- Vite oferece HMR instantâneo e build rápido

### 1.3 Stack Frontend Definida

```
React 18 + TypeScript + Vite
├── Roteamento:        React Router v6
├── Estado do servidor: TanStack Query v5
├── Estado global:     Zustand (auth, preferências)
├── Forms:             React Hook Form + Zod
├── Estilo:            CSS Modules + CSS Variables (design system próprio)
├── Ícones:            Phosphor Icons (estilo limpo, amplo)
├── Gráficos:          Recharts (leve, customizável)
├── PDF Cliente:       react-pdf / @react-pdf/renderer
└── HTTP:              Axios com interceptors para JWT
```

---

## 2. Design System — SisLocação UI

### 2.1 Referência e Filosofia

O design system é **inspirado no Soft UI Dashboard** (Creative Tim), que tem como características:
- Cards com sombras suaves e bordas arredondadas generosas
- Fundo claro (off-white / cinza-azulado suave), não branco puro
- Ícones em containers coloridos com gradiente
- Tipografia limpa e moderna
- Acentos em azul/cyan vibrante
- Sidebar clara com hierarquia visual bem definida

A linguagem visual do SisLocação vai **herdar essa estética** e adaptá-la para o domínio imobiliário — adicionando uma camada de solidez e confiança que o setor exige.

---

### 2.2 Paleta de Cores

```css
:root {
  /* === PRIMÁRIAS === */
  --color-primary:        #3A7BD5;   /* Azul principal — ações, links, destaques */
  --color-primary-light:  #5B9CF6;   /* Azul claro — hover states */
  --color-primary-dark:   #2563B0;   /* Azul escuro — pressed states */

  /* === GRADIENTE PRINCIPAL (inspirado no Soft UI) === */
  --gradient-primary: linear-gradient(135deg, #3A7BD5 0%, #00D2FF 100%);
  --gradient-dark:    linear-gradient(135deg, #1A2035 0%, #2D3561 100%);

  /* === SEMÂNTICAS === */
  --color-success:   #4CAF82;   /* Verde — pagamentos ok, status ativo */
  --color-warning:   #F5A623;   /* Laranja — alertas, vencendo */
  --color-danger:    #E74C6A;   /* Vermelho — inadimplência, erros */
  --color-info:      #17C1E8;   /* Cyan — informações, badges */

  /* === NEUTROS === */
  --color-bg:           #F8F9FA;   /* Fundo geral da aplicação */
  --color-surface:      #FFFFFF;   /* Cards, modais, sidebar */
  --color-surface-2:    #F0F2F5;   /* Input backgrounds, tabelas zebra */
  --color-border:       #E9ECEF;   /* Bordas sutis */
  --color-text-primary: #344767;   /* Texto principal */
  --color-text-secondary:#67748E; /* Labels, subtextos */
  --color-text-muted:   #AAAFC5;   /* Placeholders, desativados */

  /* === SIDEBAR DARK (variante) === */
  --sidebar-bg:         #1A2035;
  --sidebar-text:       #FFFFFF;
  --sidebar-muted:      #8392AB;
  --sidebar-active-bg:  rgba(255,255,255,0.12);
}
```

#### Uso dos Ícones de Status por Módulo

| Status | Cor | Token |
|--------|-----|-------|
| Imóvel disponível | Verde | `--color-success` |
| Imóvel locado | Azul | `--color-primary` |
| Imóvel em manutenção | Laranja | `--color-warning` |
| Pagamento em dia | Verde | `--color-success` |
| Pagamento atrasado | Vermelho | `--color-danger` |
| Contrato vencendo (< 30 dias) | Laranja | `--color-warning` |
| Inquilino ativo | Verde | `--color-success` |
| Inquilino inadimplente | Vermelho | `--color-danger` |

---

### 2.3 Tipografia

```css
:root {
  /* === FONTES === */
  --font-display: 'Plus Jakarta Sans', sans-serif;  /* Headings, sidebar brand */
  --font-body:    'DM Sans', sans-serif;             /* Corpo, labels, inputs */
  --font-mono:    'JetBrains Mono', monospace;       /* Valores monetários, IDs */

  /* === ESCALA TIPOGRÁFICA === */
  --text-xs:   0.70rem;   /* 11.2px — badges, fine print */
  --text-sm:   0.813rem;  /* 13px  — labels, table cells */
  --text-base: 0.875rem;  /* 14px  — corpo padrão */
  --text-md:   1rem;      /* 16px  — subtítulos de card */
  --text-lg:   1.125rem;  /* 18px  — títulos de seção */
  --text-xl:   1.375rem;  /* 22px  — títulos de página */
  --text-2xl:  1.75rem;   /* 28px  — métricas de dashboard */
  --text-3xl:  2.25rem;   /* 36px  — KPIs grandes */

  /* === PESOS === */
  --font-regular:   400;
  --font-medium:    500;
  --font-semibold:  600;
  --font-bold:      700;

  /* === LINE-HEIGHTS === */
  --leading-tight:  1.25;
  --leading-normal: 1.5;
  --leading-relaxed:1.75;
}
```

**Import no index.html:**
```html
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&family=DM+Sans:wght@400;500;600&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">
```

---

### 2.4 Espaçamento e Grid

```css
:root {
  /* === ESPAÇAMENTO (escala 4px) === */
  --space-1:  0.25rem;   /* 4px */
  --space-2:  0.5rem;    /* 8px */
  --space-3:  0.75rem;   /* 12px */
  --space-4:  1rem;      /* 16px */
  --space-5:  1.25rem;   /* 20px */
  --space-6:  1.5rem;    /* 24px */
  --space-8:  2rem;      /* 32px */
  --space-10: 2.5rem;    /* 40px */
  --space-12: 3rem;      /* 48px */
  --space-16: 4rem;      /* 64px */

  /* === LAYOUT === */
  --sidebar-width:       260px;
  --sidebar-collapsed:   72px;
  --topbar-height:       72px;
  --content-max-width:   1440px;
  --content-padding:     var(--space-6);

  /* === GRID === */
  /* Dashboard: 12 colunas com gap de 24px */
  --grid-columns: 12;
  --grid-gap:     var(--space-6);
}
```

---

### 2.5 Bordas, Sombras e Raios

```css
:root {
  /* === BORDER RADIUS === */
  --radius-sm:   0.5rem;    /* 8px  — badges, tags */
  --radius-md:   0.75rem;   /* 12px — inputs, botões */
  --radius-lg:   1rem;      /* 16px — cards */
  --radius-xl:   1.25rem;   /* 20px — cards hero, modal */
  --radius-2xl:  1.5rem;    /* 24px — sidebar, panels grandes */
  --radius-full: 9999px;    /* Pills, avatares */

  /* === SOMBRAS (estilo Soft UI — suaves e coloridas) === */
  --shadow-sm:   0 1px 5px rgba(52, 71, 103, 0.06);
  --shadow-md:   0 4px 20px rgba(52, 71, 103, 0.10);
  --shadow-lg:   0 8px 40px rgba(52, 71, 103, 0.14);
  --shadow-card: 0 4px 24px rgba(52, 71, 103, 0.08);

  /* Sombra colorida para ícones de métricas */
  --shadow-primary: 0 4px 15px rgba(58, 123, 213, 0.35);
  --shadow-success: 0 4px 15px rgba(76, 175, 130, 0.35);
  --shadow-danger:  0 4px 15px rgba(231, 76, 106, 0.35);
  --shadow-warning: 0 4px 15px rgba(245, 166, 35, 0.35);
}
```

---

### 2.6 Componentes Base

#### Card
```
• Fundo: --color-surface (branco)
• Border-radius: --radius-lg (16px)
• Padding: --space-6 (24px)
• Sombra: --shadow-card
• Sem border — a sombra define o limite
```

#### Metric Card (Dashboard KPI)
```
• Título: --text-sm, --color-text-secondary, --font-medium
• Valor: --text-2xl, --color-text-primary, --font-bold
• Variação positiva: --color-success
• Variação negativa: --color-danger
• Ícone: container 44×44px, gradient --gradient-primary, --radius-md, --shadow-primary
```

#### Sidebar
```
• Largura: 260px
• Fundo: --color-surface (branco) — versão light
• Brand: --font-display, --font-bold, --text-md
• Item ativo: fundo --gradient-primary, texto branco, sombra --shadow-primary
• Item hover: fundo --color-surface-2
• Seção label: --text-xs, --color-text-muted, uppercase, tracking-wide
```

#### Topbar
```
• Altura: 72px
• Fundo: --color-surface com blur (backdrop-filter)
• Breadcrumb: --text-sm, --color-text-secondary
• Título da página: --text-xl, --font-bold, --color-text-primary
• Search: input com ícone, --color-surface-2, sem borda visível
```

#### Botões
```
Primary:   bg --gradient-primary, texto branco, shadow --shadow-primary
Secondary: bg transparente, borda --color-border, texto --color-text-primary
Danger:    bg --color-danger, texto branco, shadow --shadow-danger
Ghost:     sem fundo, sem borda, texto --color-primary no hover
Tamanhos:  sm (h-32px), md (h-40px), lg (h-48px)
Radius:    --radius-md em todos
```

#### Inputs / Formulários
```
• Altura: 44px
• Fundo: --color-surface-2
• Borda: 1px solid --color-border (visível apenas no focus)
• Focus: borda --color-primary, shadow 0 0 0 3px rgba(58,123,213,0.15)
• Label: --text-sm, --font-semibold, --color-text-primary, mb-8px
• Error: borda --color-danger, mensagem --color-danger --text-xs
• Radius: --radius-md
```

#### Tabelas
```
• Header: --text-xs, uppercase, --color-text-muted, --font-semibold, padding vertical 12px
• Linhas: altura 56px, border-bottom --color-border
• Zebra: linhas pares com --color-surface-2
• Hover: linha com fundo rgba(58,123,213,0.04)
• Ações: botões ghost alinhados à direita
```

#### Badges / Status Pills
```
• Radius: --radius-full
• Padding: 4px 12px
• Texto: --text-xs, --font-semibold
• Variantes por cor semântica com fundo em 15% de opacidade
  Ex: success → bg rgba(76,175,130,0.15), text --color-success
```

#### Modal / Dialog
```
• Overlay: rgba(0,0,0,0.4) com blur
• Container: --color-surface, --radius-xl, --shadow-lg
• Largura: 480px (sm), 640px (md), 800px (lg)
• Header: título --text-lg --font-bold, ícone X ghost
• Footer: alinhado à direita, gap --space-3
```

---

### 2.7 Ícones — Phosphor Icons

Phosphor Icons foi escolhido por:
- Visual mais "suave" e moderno que Heroicons ou Feather
- Múltiplos pesos (thin, light, regular, bold, fill, duotone)
- Amplo catálogo para o domínio imobiliário

**Mapeamento por módulo:**

| Módulo | Ícone Principal | Variações |
|--------|----------------|-----------|
| Dashboard | `ChartLine` | `TrendUp`, `TrendDown` |
| Inquilinos | `UserCircle` | `Users`, `UserPlus` |
| Imóveis | `Buildings` | `House`, `Building` |
| Contratos | `FileText` | `FilePlus`, `Signature` |
| Recibos | `Receipt` | `Money`, `CurrencyDollar` |
| Configurações | `Gear` | `SlidersHorizontal` |
| Status OK | `CheckCircle` | peso `fill` |
| Status Erro | `WarningCircle` | peso `fill` |
| Alertas | `Bell` | `BellRinging` |

---

### 2.8 Guia de Animações e Transições

```css
:root {
  --duration-fast:   150ms;
  --duration-normal: 250ms;
  --duration-slow:   400ms;
  --ease-out:        cubic-bezier(0.16, 1, 0.3, 1);
  --ease-in-out:     cubic-bezier(0.65, 0, 0.35, 1);
}

/* Padrões */
button, a, input:  transition all var(--duration-fast) var(--ease-out)
card hover:        transform translateY(-2px), shadow aumentado
modal enter:       fadeIn + slideUp 300ms --ease-out
sidebar collapse:  width transition 250ms --ease-in-out
page transition:   fadeIn 200ms
```

---

### 2.9 Layout da Aplicação

```
┌─────────────────────────────────────────────────┐
│                    TOPBAR (72px)                │
├──────────┬──────────────────────────────────────┤
│          │                                      │
│ SIDEBAR  │         MAIN CONTENT AREA            │
│ (260px)  │         padding: 24px                │
│          │         max-width: 1440px            │
│          │                                      │
│          │  ┌──────────────────────────────┐   │
│          │  │   BREADCRUMB + PAGE TITLE    │   │
│          │  ├──────────────────────────────┤   │
│          │  │   CONTENT (CARDS, TABLES)    │   │
│          │  └──────────────────────────────┘   │
│          │                                      │
└──────────┴──────────────────────────────────────┘
```

---

### 2.10 Estrutura de Pastas do Frontend

```
src/
├── assets/
│   └── styles/
│       ├── tokens.css         # CSS Variables (este design system)
│       ├── reset.css          # Normalize
│       └── typography.css     # Classes de texto utilitárias
├── components/
│   ├── ui/                    # Componentes base (Button, Input, Card, Badge, Modal)
│   ├── layout/                # Sidebar, Topbar, PageLayout
│   └── charts/                # Wrappers de Recharts
├── features/
│   ├── dashboard/
│   ├── tenants/               # Inquilinos
│   ├── properties/            # Imóveis
│   ├── contracts/             # Contratos
│   └── receipts/              # Recibos
├── hooks/                     # Custom hooks (useAuth, useToast, etc.)
├── services/                  # Camada de API (Axios instances)
├── store/                     # Zustand stores
├── types/                     # TypeScript interfaces/types
└── utils/                     # Formatadores (moeda, data, CPF, etc.)
```

---

### 2.11 Decisões de Acessibilidade

- Contraste mínimo AA em todos os textos sobre fundos
- Focus-visible visível em todos os elementos interativos
- Aria-labels em ícones sem texto
- Sem comunicação de estado somente por cor (usar ícone + cor + texto)
- Estrutura de headings semântica (h1 → h6 sem pular níveis)

---

---

## 3. Contrato de API — Backend Disponível

> O backend já está implementado e rodando. As informações abaixo são o que o frontend vai consumir desde o primeiro dia.

### 3.1 Base URL e Autenticação

```
Base URL (dev): http://localhost:8080
Autenticação:   Bearer Token (JWT)
Header:         Authorization: Bearer <token>
```

### 3.2 Rotas Disponíveis

```
POST   /auth/login               → { email, senha } → { token, id, nome, sobrenome, email }
POST   /usuarios                 → Criar usuário (sem autenticação — dev)

GET    /imovel                   → Lista todos os imóveis
POST   /imovel                   → Criar imóvel
GET    /imovel/{id}              → Buscar imóvel por ID
PUT    /imovel/{id}              → Atualizar imóvel
DELETE /imovel/{id}              → Excluir imóvel

GET    /inquilinos               → Lista todos os inquilinos
POST   /inquilinos               → Criar inquilino
GET    /inquilinos/{id}          → Buscar inquilino por ID
PUT    /inquilinos/{id}          → Atualizar inquilino
DELETE /inquilinos/{id}          → Excluir inquilino

GET    /locacao                  → Lista todas as locações
POST   /locacao                  → Criar locação
PUT    /locacao/locacaoId/{id}   → Atualizar locação
DELETE /locacao/{id}             → Excluir locação
```

### 3.3 Payloads de Referência

**Login:**
```json
// Request
{ "email": "william@email.com", "senha": "senha123" }

// Response
{ "token": "eyJ...", "id": 1, "nome": "William", "sobrenome": "Dias", "email": "william@email.com" }
```

**Imóvel:**
```json
// Request (POST/PUT)
{ "descricao": "Apartamento Centro", "garagem": true, "comodos": 4, "numero": "CASA 01-A", "enderecoId": 1 }

// Response
{ "id": 1, "descricao": "Apartamento Centro", "garagem": true, "comodos": 4, "numero": "CASA 01-A", "status": true }
```

**Inquilino:**
```json
// Request (POST/PUT)
{
  "nome": "Carlos", "sobrenome": "Pereira", "cpf": "123.456.789-01", "rg": "12.345.678-9",
  "telefone": "(11) 91234-5678", "email": "carlos@email.com",
  "nacionalidade": "Brasileira", "estadoCivil": "Solteiro", "profissao": "Analista",
  "genero": "Masculino", "dataNascimento": "1990-05-12"
}
```

**Locação:**
```json
// Request (POST)
{
  "dataDeInicio": "2025-01-01",
  "tempoDeContrato": 12,
  "valorAluguel": 1800.00,
  "idInquilino": 1,
  "idImovel": 1,
  "idLocador": 1
}
```

**Erro padrão:**
```json
{ "timestamp": "2025-01-01T10:00:00Z", "status": 400, "error": "Business Exception", "message": "Mensagem de negócio", "path": "/locacao" }
```

### 3.4 Configuração CORS

O backend está configurado para aceitar requisições de `http://localhost:4200`.

**⚠️ Ação necessária antes de iniciar o frontend:** atualizar a `SecurityConfig` no backend para permitir `http://localhost:5173` (porta padrão do Vite):

```java
// SecurityConfig.java — alterar esta linha:
configuration.setAllowedOrigins(List.of("http://localhost:5173"));
```

### 3.5 Axios — Configuração Recomendada

```typescript
// src/services/api.ts
import axios from 'axios';
import { useAuthStore } from '../store/authStore';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: { 'Content-Type': 'application/json' },
});

api.interceptors.request.use((config) => {
  const token = useAuthStore.getState().token;
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  (res) => res,
  (error) => {
    if (error.response?.status === 401) {
      useAuthStore.getState().logout();
    }
    return Promise.reject(error);
  }
);

export default api;
```

---

*Documento de Discovery e Design System — versão 2.0 | SisLocação | Atualizado: abril/2026*
