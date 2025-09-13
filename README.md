# Scribler : A Multi-Tenant SaaS Notes Application

This project is a multi-tenant SaaS application designed to securely manage notes across multiple tenants with strict data isolation. It features role-based access controls and subscription-driven feature limits, demonstrating a comprehensive full-stack architecture.

## Project Overview

The system supports multiple tenants (companies) with isolated data domains so that each tenant’s users and notes remain private. Users have roles—Admin users can manage users and subscriptions, while Member users focus on note management. Tenants have subscription levels that limit note creation on Free plans and allow unlimited notes on Pro plans.

## Technologies Used

- **Spring Boot:** Backend framework that provides dependency injection, configuration, embedded web server, and REST API support.
- **Spring Security & JWT:** Stateless authentication and role-based authorization using JSON Web Tokens.
- **JPA with Hibernate:** Object-relational mapping to a PostgreSQL database for robust data persistence and querying.
- **PostgreSQL:** Relational database that holds tenant, user, and note data.
- **ReactJS:** Frontend UI framework used to build a responsive interface with filtering, searching, and sorting.
- **DTO Pattern & Validation:** Separation of entity and data transfer objects ensures clean API design and data integrity.

## Architecture and Design

- **Model-View-Controller (MVC):** Controllers handle requests, services contain business logic, and repositories manage database access.
- **Multi-Tenancy:** Shared schema with tenant ID on entities to enforce data segregation.
- **RBAC (Role-Based Access Control):** Admin and Member roles enforce operational boundaries through security policies.
- **Subscription Gating:** Conditional feature limits based on tenant subscription status implemented in backend logic.

## Key Features

- Fully isolated tenant data access ensuring security and privacy.
- JWT-based authentication for scalable, stateless sessions.
- Role rules that empower Admin users and restrict Members appropriately.
- Subscription plans with note creation limits and an upgrade endpoint.
- Dynamic frontend filtering and sorting that enhances user experience.

## Learning Outcomes

This project strengthened skills in:

- Architecting multi-tenant SaaS solutions with Spring Boot and JPA.
- Implementing secure JWT authentication and authorization flows.
- Building clean, maintainable REST APIs with separation of concerns.
- Integrating modern React frontends with dynamic, interactive features.
- Applying subscription and role-based business logic effectively.

## Summary

This application demonstrates a complete full-stack SaaS platform, leveraging state-of-the-art Java backend frameworks and React frontend technologies to address critical real-world concerns in multi-tenant, secure, and scalable software design.
