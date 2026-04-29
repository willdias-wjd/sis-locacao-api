# SisLocação — Contexto do Projeto

Sistema intranet familiar de gestão de locações imobiliárias. Uso interno, sem escala pública.
Documentação completa: `brief.md`, `mvp-scope.md`, `discovery-design-system.md`.

---

## Stack

- **Java 17 + Spring Boot 3.5.10**
- **Arquitetura Hexagonal** (Ports & Adapters)
- **Spring Data JPA** + PostgreSQL (prod) / H2 (dev, `ddl-auto: create`)
- **Spring Security + JWT** (jjwt 0.11.5) — stateless, BCryptPasswordEncoder
- **MapStruct 1.5.5 + Lombok 1.18.30** — Maven

---

## Estrutura de Pacotes

```
com.sislocacao
├── core/
│   ├── domain/model/        # Entidades de domínio (sem anotações JPA)
│   ├── domain/enums/        # Role (ADMIN | USER)
│   ├── usecase/             # Use cases por agregado: imovel, inquilino, locacao, locador, usuario
│   ├── repository/          # Interfaces de repositório (I*Repository)
│   └── exception/           # BusinessException, ResourceNotFoundException
├── ports/
│   └── input/               # Interfaces de entrada (*InputPort)
└── adapter/
    ├── input/
    │   ├── controllers/     # REST controllers
    │   ├── dto/request/     # Records de entrada
    │   ├── dto/response/    # Records de saída
    │   └── mapper/          # MapStruct input mappers
    ├── output/
    │   ├── repository/impl/ # *RepositoryAdapter (implementam IRepository)
    │   ├── repository/jpa/  # *JpaRepository (Spring Data)
    │   ├── repository/jpa/entity/ # Entidades JPA (*Entity)
    │   └── mapper/          # MapStruct output mappers
    ├── security/            # JwtService, JwtAuthenticationFilter, SecurityConfig, CustomUserDetailsService
    └── exception/           # HandlerException, StandardError
```

---

## Modelo de Domínio (implementado)

```
Imovel          → id, descricao, garagem(Boolean), comodos(Integer), numero(String), status(Boolean), endereco, locacoes
Inquilino       → id, nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estadoCivil, profissao, genero, dataNascimento, status(Boolean), locacoesEntity
Locacao         → id, dataDeInicio, dataDeTermino, valorAluguel, status(Boolean), inquilino, imovel, locador
                  Factory: Locacao.criarLocacao(id, dataInicio, tempoMeses, valor, inquilino, imovel, locador)
Locador         → id, nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estadoCivil, profissao, genero, dataNascimento
Endereco        → logradouro, estado, bairro, cidade, cep
Usuario         → id, nome, sobrenome, email, senha(BCrypt), ativo(Boolean), role(Role)
ReciboEntity    → id, numeroRecibo, valorEnergia, valorAgua, valorJuros, valorTotal, valorTotalPorExtenso, dataInicio, dataTermino, locacao
                  ⚠️ Apenas entidade JPA — domain model e use cases ainda não criados
```

**Tabelas:** TB_IMOVEIS, TB_INQUILINOS, TB_LOCACOES, TB_LOCADOR, TB_ENDERECOS, TB_USUARIOS, TB_RECIBOS

---

## API — Endpoints Implementados

**Base URL:** `http://localhost:8080` (sem prefixo `/api`)
**Auth:** `Authorization: Bearer <token>` em todas as rotas exceto `/auth/login` e `/usuarios`

```
POST   /auth/login                    ✅  { email, senha } → { token, id, nome, sobrenome, email }
POST   /usuarios                      ✅  Criar usuário (sem proteção de role por enquanto)

GET    /imovel                        ✅  Lista imóveis
POST   /imovel                        ✅  { descricao, garagem, comodos, numero, enderecoId }
GET    /imovel/{id}                   ✅
PUT    /imovel/{id}                   ✅
DELETE /imovel/{id}                   ✅

GET    /inquilinos                    ✅  Lista inquilinos
POST   /inquilinos                    ✅  { nome, sobrenome, cpf, rg, telefone, email, nacionalidade, estadoCivil, profissao, genero, dataNascimento }
GET    /inquilinos/{id}               ✅
PUT    /inquilinos/{id}               ✅
DELETE /inquilinos/{id}               ✅

GET    /locacao                       ✅  Lista locações
POST   /locacao                       ✅  { dataDeInicio, tempoDeContrato(meses), valorAluguel, idInquilino, idImovel, idLocador }
PUT    /locacao/locacaoId/{id}        ✅
DELETE /locacao/{id}                  ✅
GET    /locacao/{id}                  ❌  Use case existe (BuscarLocacaoPorIdUseCase), falta endpoint
POST   /locacao/{id}/encerrar         ❌  Método desativaLocacao() existe no domínio, falta endpoint
```

---

## O Que Está Pendente (próximas tarefas backend)

1. **Enums de status** — `status(Boolean)` em Imovel e Inquilino precisa virar enum semântico (`DISPONIVEL/LOCADO/MANUTENCAO` e `ATIVO/INATIVO/INADIMPLENTE`)
2. **CRUD de Locadores** — entidade e use case `BuscarLocadorPorIdUseCase` existem; falta controller + use cases de salvar/atualizar/excluir
3. **CRUD de Recibos** — `ReciboEntity` existe; falta domain model `Recibo`, use cases e controller
4. **Endpoint `GET /locacao/{id}`** — use case já implementado, só falta expor no controller
5. **Endpoint `POST /locacao/{id}/encerrar`** — lógica `desativaLocacao()` existe no domínio
6. **Bean Validation** — adicionar `spring-boot-starter-validation` e anotar os DTOs de request
7. **Paginação** — implementar `Pageable` nas listagens
8. **Migração de banco** — substituir `ddl-auto: create` por Flyway antes de dados reais

---

## Avisos Importantes

- **`ddl-auto: create`** — recria todas as tabelas ao reiniciar. Nunca usar em produção com dados reais. Migrar para Flyway.
- **CORS** — configurado para `http://localhost:4200`. Ao iniciar o frontend com Vite, atualizar `SecurityConfig` para `http://localhost:5173`.
- **Locação ≠ Contrato** — o domínio usa `Locacao` para o que o brief chama de "Contrato de Locação". Manter consistência ao criar novos arquivos.
- **Convenção de nomes** — use cases em português (ex: `SalvarImovelUseCase`), methods em português (ex: `executar`, `buscarPorId`).
- **Sem prefixo `/api`** nas rotas — não adicionar.

---

## Frontend (não iniciado)

Decisão tomada: **React 18 + TypeScript + Vite**. Ver `discovery-design-system.md` para design system completo (paleta, tipografia, componentes) e configuração do Axios com interceptors JWT.
