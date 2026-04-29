# MVP Scope — SisLocação
> Escopo mínimo viável para a primeira entrega funcional

---

## 1. Filosofia do MVP

O MVP do SisLocação deve ser **funcional e completo para o fluxo central**, não apenas um protótipo. Ao final do MVP, deve ser possível:

1. Cadastrar um imóvel
2. Cadastrar um inquilino
3. Criar uma locação vinculando imóvel + inquilino + locador
4. Registrar recibos de pagamento
5. Gerar o recibo em PDF
6. Ver um dashboard com visão geral

Tudo isso com autenticação funcionando e o sistema deployado na rede local da família.

---

## 2. Estado Atual do Projeto (abril/2026)

### ✅ Backend — Já implementado

| Módulo | Endpoints | Use Cases | Observações |
|--------|-----------|-----------|-------------|
| Autenticação | `POST /auth/login` | `LoginUseCase` | JWT + BCrypt |
| Usuários | `POST /usuarios` | `SalvarUsuarioUseCase` | Sem autenticação na rota (dev) |
| Imóveis | CRUD `/imovel` | 5 use cases completos | Status como Boolean |
| Inquilinos | CRUD `/inquilinos` | 5 use cases completos | Status como Boolean |
| Locações | CRUD `/locacao` | 5 use cases completos | Cálculo de término automático |

### ⚠️ Backend — Estrutura criada, implementação incompleta

| Módulo | O que existe | O que falta |
|--------|-------------|-------------|
| Recibos | `ReciboEntity` (JPA) + dados de seed | Domain model, use cases, controller |
| Locadores | `LocadorEntity` + `BuscarLocadorPorIdUseCase` | CRUD completo, controller |

### ❌ Backend — Não iniciado

- Endpoints de Dashboard (KPIs, receita mensal, alertas)
- Validações com `spring-boot-starter-validation` (Bean Validation)
- Paginação nas listagens
- Gestão de migração de banco (Flyway ou Liquibase)

### ❌ Frontend — Não iniciado

- Todo o frontend (React + Vite + TypeScript)

---

## 3. Funcionalidades do MVP

### 3.1 Autenticação

| ID | Funcionalidade | Status |
|----|---------------|--------|
| AUTH-01 | Login com e-mail e senha | ✅ Backend pronto |
| AUTH-02 | Autenticação via JWT (Spring Security) | ✅ Backend pronto |
| AUTH-03 | Logout + expiração automática do token | ✅ Token expira; logout no frontend |
| AUTH-04 | Dois perfis: ADMIN e USER | ✅ Backend pronto |
| AUTH-05 | Proteção de rotas por perfil no frontend | ❌ Frontend pendente |
| AUTH-06 | Cadastro de usuários via interface (admin) | ⚠️ Endpoint existe (`/usuarios`), sem restrição de role ainda |

---

### 3.2 Módulo: Imóveis

| ID | Funcionalidade | Status |
|----|---------------|--------|
| IMO-01 | Listagem de imóveis | ✅ `GET /imovel` |
| IMO-02 | Cadastro de imóvel | ✅ `POST /imovel` |
| IMO-03 | Edição de dados do imóvel | ✅ `PUT /imovel/{id}` |
| IMO-04 | Busca de imóvel por ID | ✅ `GET /imovel/{id}` |
| IMO-05 | Exclusão de imóvel | ✅ `DELETE /imovel/{id}` |
| IMO-06 | Status semântico (disponível/locado/manutenção) | ⚠️ Hoje é Boolean — precisa evoluir para enum |
| IMO-07 | Filtros por status/tipo na listagem | ❌ Pendente |
| IMO-08 | Upload de fotos do imóvel | ❌ Fase 2 |

**Campos do cadastro de imóvel (implementado):**
- `descricao` — nome/apelido identificador
- `garagem` (Boolean)
- `comodos` (Integer)
- `numero` — identificador físico (ex: "CASA 01-A")
- `enderecoId` — referência para um Endereço cadastrado (hoje via ID)

**⚠️ Campos previstos no MVP mas ainda não implementados:**
- Tipo do imóvel (Apartamento, Casa, Sala Comercial, etc.)
- Área (m²)
- Valor padrão de aluguel

---

### 3.3 Módulo: Inquilinos

| ID | Funcionalidade | Status |
|----|---------------|--------|
| INQ-01 | Listagem de inquilinos | ✅ `GET /inquilinos` |
| INQ-02 | Cadastro de inquilino | ✅ `POST /inquilinos` |
| INQ-03 | Edição de dados do inquilino | ✅ `PUT /inquilinos/{id}` |
| INQ-04 | Busca de inquilino por ID | ✅ `GET /inquilinos/{id}` |
| INQ-05 | Exclusão de inquilino | ✅ `DELETE /inquilinos/{id}` |
| INQ-06 | Status semântico (ativo/inadimplente) | ⚠️ Hoje é Boolean — precisa evoluir para enum |
| INQ-07 | Busca por nome/CPF | ❌ Pendente |
| INQ-08 | Cadastro de fiador | ❌ Pendente |

**Campos do cadastro de inquilino (implementado):**
- `nome`, `sobrenome`, `cpf`, `rg`
- `telefone`, `email`
- `nacionalidade`, `estadoCivil`, `profissao`, `genero`
- `dataNascimento` (LocalDate)

**⚠️ Campos previstos no MVP mas ainda não implementados:**
- Endereço do inquilino
- Dados bancários

---

### 3.4 Módulo: Locações (= Contratos)

> **Nota de nomenclatura:** O que o brief chama de "Contrato", o domínio implementado chama de `Locacao`. O modelo atual é mais simples que o especificado originalmente.

| ID | Funcionalidade | Status |
|----|---------------|--------|
| LOC-01 | Listagem de locações | ✅ `GET /locacao` |
| LOC-02 | Criação de locação (imóvel + inquilino + locador) | ✅ `POST /locacao` |
| LOC-03 | Edição de locação | ✅ `PUT /locacao/locacaoId/{id}` |
| LOC-04 | Exclusão de locação | ✅ `DELETE /locacao/{id}` |
| LOC-05 | Busca por ID | ⚠️ Use case existe, endpoint pendente |
| LOC-06 | Encerramento de locação | ⚠️ `desativaLocacao()` existe no domínio, sem endpoint dedicado |
| LOC-07 | Cálculo automático de data de término | ✅ `criarLocacao(dataInicio, tempoMeses)` |
| LOC-08 | Geração de PDF do contrato | ❌ Pendente |
| LOC-09 | Alertas de vencimento ≤ 30 dias | ❌ Pendente |

**Campos da locação (implementado):**
- `dataDeInicio` (LocalDate)
- `tempoDeContrato` (Integer — meses; calcula `dataDeTermino` automaticamente)
- `valorAluguel` (BigDecimal)
- `idInquilino`, `idImovel`, `idLocador`

**⚠️ Campos previstos no MVP mas ainda não implementados:**
- Dia de vencimento mensal
- Valor do depósito caução
- Índice de reajuste
- Multa por rescisão
- Condições especiais

---

### 3.5 Módulo: Recibos de Pagamento

| ID | Funcionalidade | Status |
|----|---------------|--------|
| REC-01 | Listagem de recibos por locação | ❌ Controller pendente |
| REC-02 | Criação de recibo | ❌ Controller pendente |
| REC-03 | Edição de recibo | ❌ Controller pendente |
| REC-04 | Geração de recibo em PDF | ❌ Pendente |
| REC-05 | Histórico completo por locação | ❌ Pendente |

**Campos do recibo (estrutura atual na entidade JPA):**
- `numeroRecibo` (String — ex: "REC-001")
- `valorEnergia`, `valorAgua`, `valorJuros`, `valorTotal` (BigDecimal)
- `valorTotalPorExtenso` (String)
- `dataInicio`, `dataTermino` (LocalDate — período do recibo)
- `locacao_id` (FK para TB_LOCACOES)

---

### 3.6 Módulo: Locadores

| ID | Funcionalidade | Status |
|----|---------------|--------|
| LOC-01 | Listagem de locadores | ❌ Pendente |
| LOC-02 | Cadastro de locador | ❌ Pendente |
| LOC-03 | Edição de locador | ❌ Pendente |
| LOC-04 | Busca por ID | ⚠️ Use case existe, endpoint pendente |
| LOC-05 | Exclusão de locador | ❌ Pendente |

---

### 3.7 Dashboard

| ID | Funcionalidade | Status |
|----|---------------|--------|
| DAS-01 | KPIs: total imóveis, ocupação, receita, inadimplentes | ❌ Pendente |
| DAS-02 | Gráfico de receita mensal (12 meses) | ❌ Pendente |
| DAS-03 | Lista de alertas: vencimentos + pagamentos em atraso | ❌ Pendente |
| DAS-04 | Distribuição de imóveis por status | ❌ Pendente |
| DAS-05 | Últimos pagamentos registrados | ❌ Pendente |

---

## 4. O Que NÃO está no MVP

| Funcionalidade | Fase Prevista |
|----------------|---------------|
| Upload de fotos de imóveis | Fase 2 |
| Upload de documentos dos inquilinos | Fase 2 |
| E-mail automático de notificação | Fase 2 |
| Assinatura digital eletrônica | Fase 3 |
| App Mobile | Fora de escopo |
| Relatórios de IR / Declaração | Fase 2 |
| Cobrança automática via PIX | Fora de escopo |
| Integração com índices de reajuste (API BCB) | Fase 2 |
| Auditoria de ações (quem fez o quê) | Fase 2 |

---

## 5. Priorização MoSCoW — Restante do MVP

### 🔴 Must Have (próximos passos obrigatórios)
- Evoluir status de Imovel e Inquilino para enum semântico
- CRUD de Recibos via API (controller + use cases + domain model)
- CRUD completo de Locadores via API
- Endpoint `GET /locacao/{id}` (busca por ID)
- Endpoint de encerramento de locação
- Frontend: setup Vite + React + TS + design system + autenticação
- Frontend: telas de Imóveis, Inquilinos e Locações

### 🟡 Should Have
- Dashboard com KPIs
- Paginação nas listagens
- Filtros por status nas listagens
- Geração de PDF de recibos (frontend)
- Alertas de vencimento

### 🟢 Could Have
- Geração de PDF do contrato de locação
- Renovação de locação
- Upload de fotos e documentos

### ⚪ Won't Have (fora do escopo atual)
- Tudo listado na seção 4

---

## 6. Entidades do Domínio — Estado Real Implementado

```
Imovel
├── id, descricao, garagem (Boolean), comodos (Integer), numero (String)
├── status (Boolean) — ⚠️ evoluir para enum
├── endereco → Endereco (via enderecoId)
└── locacoes → List<Locacao>

Inquilino
├── id, nome, sobrenome, cpf, rg
├── telefone, email, nacionalidade, estadoCivil, profissao, genero
├── dataNascimento (LocalDate)
├── status (Boolean) — ⚠️ evoluir para enum
└── locacoesEntity → List<Locacao>

Locacao  [= "Contrato" na linguagem de negócio]
├── id, dataDeInicio, dataDeTermino (calculado), valorAluguel
├── status (Boolean) — ativado na criação, desativável pelo domínio
├── inquilino → Inquilino
├── imovel → Imovel
└── locador → Locador
  Factory: criarLocacao(id, dataInicio, tempoMeses, valor, inquilino, imovel, locador)

Locador
├── id, nome, sobrenome, cpf, rg
├── telefone, email, nacionalidade, estadoCivil, profissao, genero
└── dataNascimento (LocalDate)

Endereco
└── logradouro, estado, bairro, cidade, cep

Usuario
├── id, nome, sobrenome, email, senha (BCrypt), ativo (Boolean)
└── role → Role (ADMIN | USER)

Recibo  [apenas entidade JPA por ora — domínio não criado ainda]
├── id, numeroRecibo
├── valorEnergia, valorAgua, valorJuros, valorTotal, valorTotalPorExtenso
├── dataInicio, dataTermino
└── locacao → LocacaoEntity
```

---

## 7. Endpoints REST — Estado Real da API

**Base URL:** `http://localhost:8080` (sem prefixo `/api`)

```
# Auth
POST   /auth/login                       ✅ implementado
POST   /usuarios                         ✅ implementado (sem restrição de role)

# Imóveis
GET    /imovel                           ✅ implementado
POST   /imovel                           ✅ implementado
GET    /imovel/{id}                      ✅ implementado
PUT    /imovel/{id}                      ✅ implementado
DELETE /imovel/{id}                      ✅ implementado

# Inquilinos
GET    /inquilinos                       ✅ implementado
POST   /inquilinos                       ✅ implementado
GET    /inquilinos/{id}                  ✅ implementado
PUT    /inquilinos/{id}                  ✅ implementado
DELETE /inquilinos/{id}                  ✅ implementado

# Locações
GET    /locacao                          ✅ implementado
POST   /locacao                          ✅ implementado
PUT    /locacao/locacaoId/{id}           ✅ implementado
DELETE /locacao/{id}                     ✅ implementado
GET    /locacao/{id}                     ❌ pendente (use case existe, sem endpoint)
POST   /locacao/{id}/encerrar            ❌ pendente

# Recibos
GET    /recibos                          ❌ pendente
POST   /recibos                          ❌ pendente
GET    /recibos/{id}                     ❌ pendente
PUT    /recibos/{id}                     ❌ pendente
GET    /recibos/{id}/pdf                 ❌ pendente

# Locadores
GET    /locadores                        ❌ pendente
POST   /locadores                        ❌ pendente
GET    /locadores/{id}                   ❌ pendente (use case existe, sem endpoint)
PUT    /locadores/{id}                   ❌ pendente
DELETE /locadores/{id}                   ❌ pendente

# Dashboard
GET    /dashboard/kpis                   ❌ pendente
GET    /dashboard/receita-mensal         ❌ pendente
GET    /dashboard/alertas                ❌ pendente
```

**Autenticação:** Todas as rotas exigem `Authorization: Bearer <token>` exceto `/auth/login` e `/usuarios`.

**CORS configurado para:** `http://localhost:4200` — ⚠️ atualizar para `http://localhost:5173` ao iniciar o frontend com Vite.

---

## 8. Plano de Entregas Revisado

### Sprint 1 — Backend restante (atual / próxima)
- [x] Autenticação JWT
- [x] CRUD Imóveis, Inquilinos, Locações
- [ ] Evoluir status de Imovel/Inquilino para enum (`DISPONIVEL`, `LOCADO`, `MANUTENCAO` / `ATIVO`, `INATIVO`, `INADIMPLENTE`)
- [ ] CRUD completo de Locadores (controller + use cases restantes)
- [ ] CRUD completo de Recibos (domain model + use cases + controller)
- [ ] Endpoint `GET /locacao/{id}`
- [ ] Endpoint `POST /locacao/{id}/encerrar`
- [ ] Adicionar `spring-boot-starter-validation` + Bean Validation nos DTOs
- [ ] Paginação nas listagens (`Pageable`)
- [ ] Migrar de `ddl-auto: create` para Flyway ou script SQL versionado

### Sprint 2 — Frontend: Fundação
- [ ] Setup Vite + React + TypeScript
- [ ] Implementação do design system (tokens CSS, componentes base)
- [ ] Layout base: Sidebar + Topbar + PageLayout
- [ ] Autenticação no frontend: tela de login, JWT, rotas protegidas, Zustand
- [ ] Atualizar CORS no backend para porta 5173

### Sprint 3 — Frontend: Imóveis, Inquilinos e Locações
- [ ] CRUD de imóveis (listagem + formulário + detalhe)
- [ ] CRUD de inquilinos (listagem + formulário + detalhe)
- [ ] CRUD de locações (listagem + formulário + detalhe)
- [ ] Tela de locadores

### Sprint 4 — Recibos e Dashboard
- [ ] CRUD de recibos por locação
- [ ] Geração de recibo em PDF (`@react-pdf/renderer`)
- [ ] Dashboard com KPIs e gráfico de receita
- [ ] Alertas de vencimento e inadimplência

### Sprint 5 — Qualidade e Deploy
- [ ] Testes nos fluxos principais
- [ ] Ajustes de responsividade
- [ ] Deploy na rede interna (Docker Compose)
- [ ] Documentação de uso para a família
- [ ] Backup automático do PostgreSQL (`ddl-auto: validate` em produção)

---

## 9. Definição de Pronto (DoD)

Uma funcionalidade está **pronta** quando:

- [ ] Backend: endpoint implementado, validado com Bean Validation e com tratamento de erros
- [ ] Frontend: tela implementada seguindo o design system
- [ ] Fluxo completo testado manualmente (happy path + edge cases)
- [ ] Responsivo em desktop (1280px+) e tablet (768px+)
- [ ] Sem erros no console do navegador
- [ ] Sem dados hardcoded — tudo vem da API

---

## 10. Riscos Específicos do MVP

| Risco | Mitigação |
|-------|-----------|
| `ddl-auto: create` recriar tabelas ao reiniciar | Migrar para Flyway antes do primeiro dado real de produção |
| Status Boolean sem semântica dificultar filtros no frontend | Criar enums de status antes de iniciar o frontend |
| CORS bloqueando o frontend | Atualizar `SecurityConfig` ao definir a porta do Vite (5173) |
| Geração de PDF com layout correto | Criar template de recibo no Sprint 4 e validar antes de fechar |
| Listagens lentas com muitos registros | Implementar paginação com `Pageable` no Sprint 1 |

---

*Documento de Escopo MVP — versão 2.0 | SisLocação | Uso Interno Familiar | Atualizado: abril/2026*
