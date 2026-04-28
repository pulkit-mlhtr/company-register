# Company Register

A Spring Boot REST API for registering companies and their owners, built with Hexagonal Architecture.

---

## Architecture

The project follows **Hexagonal Architecture (Ports & Adapters)**, keeping the domain isolated from infrastructure concerns.

```
com.company/
├── domain/              # Core business logic — models and port interfaces
│   ├── model/           # Company, Owner
│   └── port/
│       ├── in/          # Input ports (use case interfaces): ICompanyService, IOwnerService
│       └── out/         # Output ports: ICompanyRepository, IOwnerRepository, IProfitabilityService
│
├── application/         # Use case implementations and DTOs
│   ├── service/         # CompanyService, OwnerService
│   └── dto/             # Request/response objects
│
├── adapter/
│   ├── in/web/          # REST controllers (inbound adapters)
│   └── out/
│       ├── persistence/ # JPA repositories and entity mappers (outbound adapters)
│       └── external/    # External API client for profitability data
│
└── config/              # Spring configuration (security, API client, data seeding)
```

**Key principle:** the `domain` layer has zero dependencies on Spring or JPA. All infrastructure details are hidden behind port interfaces.

---

## Features

- **Create a company** — register a company with CVR (8-digit Danish company ID), name, address, and phone
- **Get a company** — retrieve company details by CVR
- **Add an owner** — associate an owner (identified by CPR, a 10-digit Danish personal ID) with a company
- **List all owners** — retrieve all registered owners with their associated company
- **Company profitability** — fetch profitability data from an external service via a generated OpenAPI client
- **Swagger UI** — interactive API documentation at `/swagger-ui.html`
- **H2 console** — in-memory database browser at `/h2-console`

---

## REST API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/companies` | Create a new company |
| `GET` | `/companies/{cvr}` | Get a company by CVR |
| `POST` | `/companies/{cvr}/owners` | Add an owner to a company |
| `GET` | `/owners` | Get all owners |

### Example requests

**Create a company**
```http
POST /companies
Content-Type: application/json

{
  "cvr": "12345678",
  "name": "Acme A/S",
  "address": "Vesterbrogade 1, Copenhagen",
  "phone": "12345678"
}
```

**Add an owner**
```http
POST /companies/12345678/owners
Content-Type: application/json

{
  "name": "Lars Jensen",
  "address": "Nørrebrogade 5, Copenhagen",
  "cpr": "1234567890"
}
```

---

## Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| Spring Boot | 3.2.5 | Application framework |
| Spring Data JPA | (Boot-managed) | ORM and repository abstraction |
| Spring Security | (Boot-managed) | Security filter chain |
| Spring WebFlux | (Boot-managed) | Reactive WebClient for external HTTP calls |
| H2 Database | (Boot-managed) | In-memory relational database |
| springdoc-openapi-starter-webmvc-ui | 2.5.0 | Swagger UI and OpenAPI 3 docs |
| swagger-annotations (io.swagger.core.v3) | 2.2.21 | `@Operation` and OpenAPI annotations |
| openapi-generator-maven-plugin | 7.4.0 | Generates Java client from `company-profitability.yaml` |
| jackson-databind-nullable | 0.2.6 | Nullable field support for OpenAPI-generated models |
| Gson | 2.10.1 | JSON serialization for the generated API client |
| OkHttp | 5.3.2 | HTTP client used by the generated API client |
| Bean Validation (Jakarta) | (Boot-managed) | Request validation via `@Valid`, `@NotBlank`, etc. |
| Java | 21 | Language version |

---

## How to run

### Prerequisites

- Java 21+
- Maven 3.8+

### Steps

```bash
# Clone the repository
git clone <repo-url>
cd company-register

# Build and run
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

### Useful endpoints once running

| URL | Description |
|-----|-------------|
| `http://localhost:8080/swagger-ui.html` | Interactive API docs |
| `http://localhost:8080/h2-console` | H2 database browser |

**H2 console settings:**
- JDBC URL: `jdbc:h2:mem:companydb`
- Username: `sa`
- Password: *(leave empty)*

### Seed data

On startup, `DataInitializer` automatically inserts:
- **Acme A/S** (CVR: `12345678`) with owners Lars Jensen and Mette Nielsen
- **Nordic Tech ApS** (CVR: `87654321`) with owner Anders Hansen

---

## Notes

- The database is **in-memory** and reset on every restart (`ddl-auto=create-drop`).
- The profitability endpoint calls an external service configured at `http://profitability-service.local:8080`. This will fail unless that service is reachable.
- Security is configured to permit all requests (no authentication required).
