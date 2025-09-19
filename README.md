# Scribbler: A Multi-Tenant SaaS Notes Application

Scribbler is a **multi-tenant SaaS application** built with **Spring Boot** and **ReactJS**, designed to securely manage notes across different tenants while enforcing **data isolation**, **subscription plans**, and **role-based access control (RBAC)**.  
It demonstrates a **production-style architecture** combining **Core Java concepts** with modern **Spring ecosystem features**.

---

## Project Overview

- **Multi-Tenancy**: Each tenant (organization) has its own isolated users and notes.
- **Roles**:
  - **Admin** → manages users, tenants, and subscriptions.
  - **Member** → creates and manages notes within their tenant.
- **Subscription Plans**:
  - **Free Plan** → limited features.
  - **Pro Plan** → full features (e.g., unlimited notes).
- **Secure Access**: Authentication and authorization are enforced via **JWT tokens** and **role checks**.

---

## Technologies Used

- **Core Java Concepts**

  - OOP Principles → encapsulation, inheritance, polymorphism used across entities, DTOs, and services.
  - Collections API → lists, maps, and streams for DTO transformations and data filtering.
  - Exception Handling → custom exceptions for validation and business logic errors.
  - Interfaces & Abstraction → service interfaces separate contracts from implementations.

- **Spring Boot**

  - Dependency Injection & Inversion of Control (IoC).
  - RESTful API layer using Spring Web.
  - Configuration management with profiles and beans.

- **Spring Security & JWT**

  - Stateless authentication with **JSON Web Tokens (JWT)**.
  - Role-based access control (`@PreAuthorize`, `hasRole`, `hasAnyRole`).
  - Secure endpoints for Admin and Member roles.

- **Spring Data JPA & Hibernate**

  - ORM mapping for entities like **User**, **Tenant**, and **Note**.
  - Repository pattern (`JpaRepository`) for CRUD operations.
  - Query derivation (`findBySlug`, `findAllByTenantId`, etc.) for complex lookups.

- **Database**

  - **PostgreSQL** for relational persistence.
  - Shared schema multi-tenancy using **tenantId foreign key** to enforce isolation.

- **Frontend**

  - **ReactJS** for a responsive and dynamic UI.
  - Integration with backend APIs for authentication, notes, and tenant management.

- **DTO Pattern & Validation**
  - DTOs (e.g., `NoteDto`, `TenantDto`) used to separate internal models from exposed API data.
  - Validation annotations (`@Valid`, `@NotNull`, etc.) ensure API integrity.

---

## Architecture and Design

### MVC Structure (Broad Terms)

- **Model** → Entities (`User`, `Tenant`, `Note`) mapped to database tables.
- **View** → React frontend consuming REST APIs, providing the UI for users.
- **Controller** → REST endpoints (`UserController`, `TenantController`, `NoteController`) handle HTTP requests.
- **Service Layer** → Business logic (`TenantService`, `NoteService`, `AuthService`) handles validation, rules, and orchestration.
- **Repository Layer** → Interfaces extending `JpaRepository` that provide database access.

### Multi-Tenancy

- **Shared schema strategy**.
- Every entity references a `tenantId`.
- Service layer enforces tenant isolation by checking the current user’s tenant before data access.

### RBAC (Role-Based Access Control)

- Admin vs Member roles enforced with **Spring Security annotations**:
  ```java
  @PreAuthorize("hasRole('ADMIN')")
  @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
  ```

### Authentication

- Stateless session management with **JWT tokens**.
- Tokens carry **user ID**, **role**, and **tenant info**.
- Used by **Spring Security filters** to grant/deny access.

---

### Subscription Gating

- **FREE plan tenants** → limited features (like note creation caps).
- **PRO plan tenants** → full feature set.
- **Subscription upgrade endpoint** allows plan transitions.

---

### Key Features

- **Strict tenant isolation**: No user can access another tenant’s data.
- **JWT authentication**: Secure, stateless access.
- **Role-based permissions**: Admins manage tenants, Members focus on notes.
- **Dynamic subscription logic**: Business rules differ for Free vs Pro tenants.
- **Clean API design** with DTOs, validations, and service abstractions.
