# Project Brief — SisLocação
> Sistema Intranet Familiar de Gestão de Locações Imobiliárias

---

## 1. Visão Geral

**SisLocação** é uma aplicação web intranet de uso privado e familiar, destinada ao gerenciamento completo do ciclo de vida de locações imobiliárias. O sistema centraliza o cadastro de imóveis, inquilinos, locações e recibos de pagamento, eliminando controles em planilhas ou papel e garantindo rastreabilidade histórica.

> **Estado atual (abril/2026):** Backend com arquitetura hexagonal implementado e funcional para os módulos de Autenticação, Imóveis, Inquilinos e Locações. Frontend ainda não iniciado.

---

## 2. Contexto e Motivação

O projeto nasce da necessidade de organizar e profissionalizar a gestão de um portfólio de imóveis para aluguel administrado pela família. Hoje o processo é manual, sujeito a erros e difícil de auditar. A intranet resolve isso com um único ponto de verdade, acessível apenas aos membros autorizados da família.

Por se tratar de uso interno, **não há requisito de escalabilidade pública**, mas há exigência de **confiabilidade, usabilidade e geração de documentos legais** (contratos e recibos).

---

## 3. Objetivos de Negócio

| # | Objetivo | Indicador de Sucesso |
|---|----------|----------------------|
| 1 | Eliminar controle manual em planilhas | 100% dos dados migrados para o sistema |
| 2 | Gerar contratos de aluguel automaticamente | Contrato gerado em < 2 minutos |
| 3 | Rastrear pagamentos de aluguel | Zero recibos perdidos ou não registrados |
| 4 | Manter histórico de inquilinos e imóveis | Consulta de histórico disponível em qualquer momento |

---

## 4. Usuários e Perfis

Dado que é uso **exclusivamente familiar/interno**, o sistema prevê dois perfis, já implementados no backend via enum `Role`:

### 4.1 Administrador (`Role.ADMIN`)
- Gerencia todos os cadastros (imóveis, inquilinos, locações)
- Gera e imprime contratos e recibos
- Acessa relatórios e históricos completos
- Cadastra novos usuários no sistema

### 4.2 Usuário (`Role.USER`)
- Visualiza dados sem permissão de edição
- Pode consultar histórico de pagamentos
- Não acessa dados sensíveis de inquilinos (CPF, RG)

---

## 5. Escopo Funcional Macro

### Módulo 1 — Inquilinos ✅ Backend implementado
- Cadastro completo (dados pessoais, documentos, contato)
- Histórico de locações por inquilino
- Status: ativo/inativo (Boolean — evolução para enum prevista)

### Módulo 2 — Imóveis ✅ Backend implementado
- Cadastro de propriedades (endereço, descrição, cômodos, garagem, número)
- Status: ativo/inativo (Boolean — evolução prevista para: disponível, locado, em manutenção)
- Histórico de locações vinculadas

### Módulo 3 — Locações ✅ Backend implementado
- Criação de locação vinculando imóvel + inquilino + locador
- Cálculo automático da data de término com base no tempo de contrato (em meses)
- Validações de domínio (datas, valor, entidades obrigatórias)
- Status ativo/inativo (Boolean)
- Geração de PDF do contrato — **pendente**
- Alertas de vencimento — **pendente**

### Módulo 4 — Recibos de Pagamento ⚠️ Entidade criada, controller pendente
- Entidade `ReciboEntity` mapeada no banco (TB_RECIBOS) com: número, valor energia, valor água, valor juros, valor total, valor total por extenso, período
- CRUD via API — **pendente**
- Geração de recibo em PDF — **pendente**

### Módulo 5 — Dashboard ❌ Não iniciado
- Visão geral: imóveis ocupados vs disponíveis, receita mensal, inadimplência
- Alertas: contratos vencendo, pagamentos em atraso
- Gráficos de receita ao longo do tempo

### Módulo 6 — Locador ⚠️ Entidade e use case parcial, controller pendente
- Entidade `LocadorEntity` mapeada no banco (TB_LOCADOR)
- Use case `BuscarLocadorPorIdUseCase` implementado
- CRUD completo via API — **pendente**
- Relatórios por locador — **pendente**

---

## 6. Restrições e Premissas

| Item | Detalhe |
|------|---------|
| **Acesso** | Intranet / VPN familiar — sem exposição pública |
| **Autenticação** | JWT com Spring Security — usuários cadastrados via API `/usuarios` |
| **Idioma** | Português (pt-BR) |
| **Moeda** | Real (BRL) |
| **Dispositivos** | Desktop-first (uso predominante em computador); responsividade básica para tablets |
| **Offline** | Não é requisito — sistema sempre conectado à rede local |
| **CORS** | Atualmente configurado para `http://localhost:4200` — ajustar para a porta do frontend escolhido |

---

## 7. Stack Tecnológica

### Backend ✅ Implementado
- Java 17 + Spring Boot 3.5.10
- Arquitetura Hexagonal (Ports & Adapters) — pacotes: `core`, `ports`, `adapter`
- Spring Data JPA + PostgreSQL (produção) / H2 (desenvolvimento, `ddl-auto: create`)
- Spring Security + JWT (jjwt 0.11.5) — stateless, BCryptPasswordEncoder
- MapStruct 1.5.5 + Lombok 1.18.30
- Maven

### Frontend ❌ Não iniciado — decisão tomada no Discovery
- **React 18 + TypeScript + Vite** (conforme discovery-design-system.md)
- Design System inspirado no Soft UI Dashboard (Creative Tim)
- Geração de PDF no cliente via `@react-pdf/renderer`

---

## 8. Modelo de Domínio Real (implementado)

```
Imovel
├── id, descricao, garagem (Boolean), comodos (Integer), numero (String)
├── status (Boolean)
├── endereco → Endereco
└── locacoes → List<Locacao>

Inquilino
├── id, nome, sobrenome, cpf, rg
├── telefone, email, nacionalidade, estadoCivil, profissao, genero
├── dataNascimento (LocalDate)
├── status (Boolean)
└── locacoesEntity → List<Locacao>

Locacao
├── id, dataDeInicio (LocalDate), dataDeTermino (LocalDate)
├── valorAluguel (BigDecimal)
├── status (Boolean) — ativado automaticamente na criação
├── inquilino → Inquilino
├── imovel → Imovel
└── locador → Locador
  [Factory: criarLocacao(id, dataInicio, tempoMeses, valor, inquilino, imovel, locador)]

Locador
├── id, nome, sobrenome, cpf, rg
├── telefone, email, nacionalidade, estadoCivil, profissao, genero
└── dataNascimento (LocalDate)

Endereco
└── logradouro, estado, bairro, cidade, cep

Usuario
├── id, nome, sobrenome, email, senha (BCrypt)
├── ativo (Boolean) — true na criação
└── role → Role (ADMIN | USER)

ReciboEntity (JPA apenas — domínio pendente)
├── id, numeroRecibo
├── valorEnergia, valorAgua, valorJuros, valorTotal (BigDecimal)
├── valorTotalPorExtenso (String)
├── dataInicio, dataTermino (LocalDate)
└── locacao → LocacaoEntity
```

---

## 9. Integrações e Dependências Externas

| Integração | Status | Notas |
|------------|--------|-------|
| Geração de PDF (recibos/contratos) | Pendente | `@react-pdf/renderer` no frontend (decisão tomada) |
| Índices de reajuste (IGP-M/IPCA) | Pós-MVP | Input manual no MVP; API do Banco Central depois |
| E-mail de notificação | Pós-MVP | Spring Mail + SMTP local |

---

## 10. Riscos Identificados

| Risco | Probabilidade | Mitigação |
|-------|--------------|-----------|
| Geração de PDF com layout de contrato complexo | Alta | Usar `@react-pdf/renderer` no frontend; validar template cedo |
| Status de Imovel/Inquilino como Boolean em vez de enum | Alta | Migrar para enum com significado semântico antes de construir o frontend |
| CORS configurado para porta 4200 (Angular) vs Vite porta 5173 | Alta | Atualizar `SecurityConfig` ao iniciar frontend com Vite |
| Dados de inquilinos (LGPD) | Baixa (uso familiar) | Acesso restrito; backup criptografado |
| Perda de dados por falta de backup | Média | Script de backup automático do PostgreSQL |
| `ddl-auto: create` em produção (recria tabelas) | Alta | Migrar para `validate` ou usar Flyway/Liquibase antes do deploy |

---

## 11. Critérios de Aceite do Projeto

- [x] Autenticação JWT funcionando com dois perfis (ADMIN / USER)
- [x] CRUD completo de imóveis funcionando via API
- [x] CRUD completo de inquilinos funcionando via API
- [x] CRUD completo de locações funcionando via API
- [ ] CRUD completo de recibos funcionando via API
- [ ] CRUD completo de locadores funcionando via API
- [ ] Geração de recibo em PDF com dados do pagamento
- [ ] Geração de contrato em PDF com dados completos
- [ ] Dashboard com métricas principais visíveis
- [ ] Deploy funcional na rede local (intranet)
- [ ] Frontend completo integrado ao backend

---

## 12. Fora do Escopo (Explicitamente)

- Integração com portais imobiliários (ZAP, OLX)
- App mobile nativo
- Assinatura digital eletrônica (DocuSign, etc.)
- Cobrança automatizada (boleto, PIX automático)
- Multi-empresa / multi-portfólio

---

*Documento de referência — versão 2.0 | Projeto: SisLocação | Uso Interno Familiar | Atualizado: abril/2026*
