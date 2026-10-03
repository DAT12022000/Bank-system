# Development Plan

## Phase 0 — Foundation

### Tasks

- Create Git repository.
- Create Maven projects.
- Configure Java 21.
- Configure Spring Boot.
- Configure Docker Compose.
- Start SQL Server.
- Start Kafka.
- Start Redis.

### Done

All infrastructure containers start successfully.

## Phase 1 — Customer Service

Implement:

- Customer entity.
- Repository.
- Service.
- Controller.
- Validation.
- Exception handling.
- CRUD.
- Status management.
- Tests.
- Customer events.

## Phase 2 — Account Service

Implement:

- Account entity.
- Balance.
- Account status.
- Optimistic locking.
- Transaction Entry.
- Deposit.
- Withdraw.
- Concurrency tests.

## Phase 3 — Transaction Service

Implement:

- Transaction entity.
- Transaction state.
- Deposit command.
- Withdraw command.
- Transfer command.
- Transaction history.
- Idempotency.

## Phase 4 — Kafka

Implement:

- Kafka producer.
- Kafka consumer.
- Event envelope.
- Retry.
- DLT.
- Consumer idempotency.

## Phase 5 — Outbox

Implement:

```text
Business DB transaction
+
OUTBOX_EVENT insert
```

Then:

```text
Outbox Publisher
→ Kafka
```

Verify no event loss when Kafka is temporarily unavailable.

## Phase 6 — Saga

Implement transfer Saga:

```text
TransferCreated
→ Debit/Credit
→ TransferCompleted
```

Failure:

```text
Debit success
→ Credit failed
→ Compensation
→ TransferReversed
```

## Phase 7 — Audit

Consume business events and persist audit log.

## Phase 8 — Notification

Consume:

- Transaction success.
- Transaction failure.
- Transfer completed.
- Transfer reversed.

Implement retry.

## Phase 9 — API Gateway

Implement:

- Routing.
- JWT.
- CORS.
- Rate limiting.
- Correlation ID.

## Phase 10 — Resilience

Add:

- Timeout.
- Retry.
- Circuit breaker.
- Bulkhead where appropriate.

Use Resilience4j.

## Phase 11 — Observability

Implement:

- Structured logging.
- Trace ID.
- OpenTelemetry.
- Prometheus.
- Grafana.

## Phase 12 — Final Integration

Run:

```text
Customer
→ Account
→ Deposit
→ Withdraw
→ Transfer
→ Audit
→ Notification
```

Validate failure scenarios.

## MVP Scope

MVP MUST include:

- Customer CRUD.
- Account CRUD/basic status.
- Balance.
- Deposit.
- Withdraw.
- Transfer.
- Transaction history.
- Idempotency.
- Kafka.
- Basic Saga.
- Audit.
- JWT.
- Docker.

## Post-MVP

- Advanced notification provider.
- Multi-currency.
- Fraud detection.
- Rate engine.
- Ledger.
- Reconciliation.
- Kubernetes.
- CI/CD.
