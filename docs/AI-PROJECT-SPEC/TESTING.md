# Testing Strategy

## 1. Test Levels

1. Unit Test.
2. Integration Test.
3. Contract Test.
4. Kafka integration test.
5. End-to-End Test.

## 2. Unit Test

Use JUnit 5 + Mockito.

Must cover business rules.

Examples:

```text
create account
invalid amount
insufficient balance
blocked account
self transfer
duplicate idempotency key
invalid transaction state
```

## 3. Account Service

Important tests:

### TEST-ACC-001

Deposit 1,000,000 into balance 0.

Expected:

```text
balance = 1,000,000
```

### TEST-ACC-002

Withdraw 500,000 from balance 1,000,000.

Expected:

```text
balance = 500,000
```

### TEST-ACC-003

Withdraw greater than balance.

Expected:

```text
INSUFFICIENT_BALANCE
```

### TEST-ACC-004

Two concurrent withdrawals cannot cause negative balance.

## 4. Transaction Service

Test:

- PENDING creation.
- PROCESSING.
- SUCCESS.
- FAILED.
- REVERSED.
- Invalid transitions.

## 5. Idempotency Tests

Same request twice:

```text
Request 1 → execute
Request 2 → return previous result
```

Different request with same key:

```text
→ conflict
```

## 6. Kafka Tests

Test:

- Event serialization.
- Event consumption.
- Duplicate event.
- Retry.
- DLT.

## 7. Saga Tests

Happy path:

```text
Debit → Credit → SUCCESS
```

Failure:

```text
Debit → Credit failed → Compensation → REVERSED
```

## 8. API Tests

Verify:

- Status code.
- Response body.
- Error code.
- Validation.
- Authentication.
- Authorization.

## 9. Integration Environment

Use Testcontainers where practical:

- SQL Server.
- Kafka.
- Redis.

## 10. Acceptance Criteria

A financial feature is complete only when:

- Happy path passes.
- Validation cases pass.
- Concurrency cases pass.
- Idempotency passes.
- Failure/compensation passes where applicable.
- API contract passes.
- Security checks pass.
