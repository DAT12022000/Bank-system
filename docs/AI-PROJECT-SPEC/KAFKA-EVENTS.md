# Kafka Event Specification

## 1. Event Envelope

All business events SHOULD follow:

```json
{
  "eventId": "evt-123",
  "eventType": "TransferCompleted",
  "occurredAt": "2026-09-27T10:00:00Z",
  "traceId": "trace-123",
  "correlationId": "corr-123",
  "producer": "account-service",
  "aggregateType": "ACCOUNT",
  "aggregateId": "10001",
  "version": 1,
  "payload": {}
}
```

## 2. Topics

Suggested topics:

```text
customer.events
account.events
transaction.events
audit.events
notification.events
```

For a larger deployment, transaction event topics can be split by domain/type.

## 3. Customer Events

### EVT-CUS-001 CustomerCreated

Payload:

```json
{
  "customerId": 1001,
  "customerCode": "CUS-000001"
}
```

### EVT-CUS-002 CustomerBlocked

Payload:

```json
{
  "customerId": 1001,
  "reason": "..."
}
```

## 4. Account Events

- AccountCreated
- AccountBlocked
- AccountClosed

Example:

```json
{
  "accountId": 10001,
  "accountNumber": "123456789",
  "customerId": 1001,
  "status": "ACTIVE"
}
```

## 5. Transaction Events

Supported events:

```text
DepositCreated
DepositCompleted
DepositFailed

WithdrawCreated
WithdrawCompleted
WithdrawFailed

TransferCreated
TransferDebited
TransferCompleted
TransferFailed
TransferReversed
```

Example TransferCompleted:

```json
{
  "transactionId": 90002,
  "transactionCode": "TXN-20260927-000002",
  "sourceAccountId": 10001,
  "targetAccountId": 10002,
  "amount": 250000,
  "currency": "VND",
  "status": "SUCCESS"
}
```

## 6. Consumer Rules

Consumer MUST:

1. Validate event envelope.
2. Check event ID/idempotency.
3. Process event.
4. Persist processing result where necessary.
5. Commit offset only after successful processing.

## 7. Duplicate Event

Kafka delivery can result in duplicate processing.

Consumers MUST be idempotent.

Example:

```text
eventId = evt-123
already processed
→ ignore duplicate
```

## 8. Ordering

Where ordering is required, Kafka key SHOULD be based on the relevant aggregate:

```text
accountId
```

or:

```text
transactionId
```

depending on event stream design.

## 9. Retry

Transient errors SHOULD use retry.

Persistent failures SHOULD move to Dead Letter Topic (DLT).

Suggested:

```text
transaction.events
transaction.events.DLT
```
