# 🛒 E-Commerce Microservices

A full-featured e-commerce backend built on a **microservices architecture** using **Java / Spring Boot**. Each service is independently deployable, communicates asynchronously via **Apache Kafka**, and the entire stack is orchestrated with **Docker Compose**.

---

## 📑 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Services](#services)
- [Tech Stack](#tech-stack)
- [Infrastructure](#infrastructure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Port Reference](#port-reference)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

---

## Overview

This project demonstrates a production-style microservices system for an e-commerce platform. It separates business concerns into discrete, independently scalable services — each with its own database — and uses event-driven communication to keep them loosely coupled.

Key goals:
- Independent deployability of each service
- Loose coupling via asynchronous messaging (Kafka)
- Distributed tracing for observability (Zipkin)
- Local email testing (MailDev)

---

## Architecture

```
                         ┌─────────────────────┐
                         │     API Gateway      │
                         └────────┬────────────┘
                                  │
               ┌──────────────────┼──────────────────┐
               │                  │                  │
       ┌───────▼──────┐  ┌────────▼──────┐  ┌───────▼──────┐
       │   Product    │  │    Order      │  │  Notification │
       │   Service    │  │   Service     │  │   Service     │
       │  (MongoDB)   │  │ (PostgreSQL)  │  │  (MailDev)    │
       └──────────────┘  └───────┬───────┘  └──────────────┘
                                 │
                        ┌────────▼────────┐
                        │  Apache Kafka   │
                        │  + Zookeeper    │
                        └─────────────────┘
                                 │
                         ┌───────▼──────┐
                         │    Zipkin    │
                         │  (Tracing)   │
                         └─────────────┘
```

Services communicate synchronously (REST) for direct queries and asynchronously (Kafka topics) for events such as order placement and notification dispatch.

---

## Services

| Service | Description | Database |
|---|---|---|
| **Product Service** | Manages the product catalogue — creation, updates, and retrieval | MongoDB |
| **Order Service** | Handles order placement and lifecycle management | PostgreSQL |
| **Notification Service** | Sends transactional emails triggered by domain events (e.g. order confirmed) | — |
| **API Gateway** | Single entry point; routes requests to downstream services | — |

> **Note:** Each service owns its own schema/database and communicates with other services only through well-defined interfaces or Kafka events, never by direct database access.

---

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java |
| Frameworks | Spring Boot, Spring Cloud |
| Message Broker | Apache Kafka + Zookeeper |
| Databases | PostgreSQL, MongoDB |
| Distributed Tracing | Zipkin |
| Email Testing | MailDev |
| Containerisation | Docker, Docker Compose |
| Build Tool | Maven |

---

## Infrastructure

All infrastructure dependencies are defined in `docker-compose.yaml` and started with a single command.

| Container | Image | Purpose |
|---|---|---|
| `ms_postgres` | `postgres` | Relational database for order data |
| `ms_mongodb` | `mongo:7` | Document store for product catalogue |
| `ms_zookeeper` | `confluentinc/cp-zookeeper` | Coordination service for Kafka |
| `ms_kafka` | `confluentinc/cp-kafka` | Async event streaming |
| `ms_mail_dev` | `maildev/maildev` | Local SMTP + web UI for email testing |
| `zipkin` | `openzipkin/zipkin` | Distributed tracing UI |

All containers share the `ms_network` bridge network. Persistent data is stored in named Docker volumes (`postgres`, `mongo`).

---

## Prerequisites

Make sure you have the following installed before proceeding:

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [Docker](https://www.docker.com/get-started) & [Docker Compose](https://docs.docker.com/compose/)

---

## Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/ahmed-sial/e-commerce-microservices.git
cd e-commerce-microservices
```

**2. Start infrastructure services**

```bash
docker-compose up -d
```

This brings up PostgreSQL, MongoDB, Kafka (+ Zookeeper), MailDev, and Zipkin.

**3. Build all services**

```bash
cd services
mvn clean package -DskipTests
```

**4. Run each service**

Start each Spring Boot service from its own module directory (or from your IDE):

```bash
# Example — adjust for each service module
mvn spring-boot:run -pl <service-module-name>
```

> Services automatically connect to the infrastructure containers running in Docker.

---

## Running the Application

Once everything is running, the following UIs are available in your browser:

| Service | URL |
|---|---|
| MailDev Web UI (email inbox) | http://localhost:1080 |
| Zipkin Tracing UI | http://localhost:9411 |
| Kafka Broker | `localhost:9092` |
| PostgreSQL | `localhost:5432` |
| MongoDB | `localhost:27017` |

---

## Port Reference

| Port | Service |
|---|---|
| `5432` | PostgreSQL |
| `27017` | MongoDB |
| `2181` | Zookeeper |
| `9092` | Kafka |
| `1025` | MailDev SMTP |
| `1080` | MailDev Web UI |
| `9411` | Zipkin |

---

## Project Structure

```
e-commerce-microservices/
├── docker-compose.yaml          # Infrastructure: Postgres, Mongo, Kafka, Zipkin, MailDev
├── .gitignore
└── services/
    ├── api-gateway/             # Spring Cloud Gateway — routes requests
    ├── product-service/         # Product catalogue (MongoDB)
    ├── order-service/           # Order management (PostgreSQL)
    └── notification-service/    # Email notifications via Kafka events
```

---

## Contributing

Contributions are welcome! To get started:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "feat: describe your change"`
4. Push to your fork: `git push origin feature/your-feature`
5. Open a Pull Request

Please follow standard Java/Spring Boot coding conventions and make sure your changes work with the existing Docker Compose setup before submitting.

---

> Built with ☕ Java and Spring Boot.
