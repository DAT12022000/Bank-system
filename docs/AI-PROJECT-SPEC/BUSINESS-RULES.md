# Business Rules

## Customer Rules

### BR-CUS-001

Customer code MUST be unique.

### BR-CUS-002

Customer status MUST be one of:

```text
ACTIVE
BLOCKED
INACTIVE
```

### BR-CUS-003

A blocked/inactive customer should not be allowed to create new financial accounts unless a future requirement explicitly permits it.

## Account Rules

### BR-ACC-001

Account number MUST be unique.

### BR-ACC-002

Account status:

```text
ACTIVE
BLOCKED
CLOSED
```

### BR-ACC-003

Only ACTIVE accounts can perform financial transactions.

### BR-ACC-004

Balance MUST NOT become negative.

### BR-ACC-005

Account balance is owned by Account Service.

### BR-ACC-006

Every balance mutation should record balance before and after.

## Money Rules

### BR-MNY-001

Amount MUST be greater than zero.

### BR-MNY-002

Money uses `BigDecimal`.

### BR-MNY-003

Database money uses `DECIMAL(19,4)`.

### BR-MNY-004

Currency MUST be validated.

## Deposit Rules

### BR-DEP-001

Deposit increases account balance.

### BR-DEP-002

Deposit requires ACTIVE account.

### BR-DEP-003

Repeated request with same idempotency key MUST NOT double-credit account.

## Withdraw Rules

### BR-WDR-001

Withdraw decreases account balance.

### BR-WDR-002

Insufficient balance MUST fail the withdrawal.

### BR-WDR-003

Repeated request with same idempotency key MUST NOT double-debit account.

## Transfer Rules

### BR-TRF-001

Source account and target account MUST be different.

### BR-TRF-002

Both accounts MUST be ACTIVE.

### BR-TRF-003

Source balance MUST be sufficient.

### BR-TRF-004

Transfer must be idempotent.

### BR-TRF-005

If debit succeeds and credit cannot complete, compensation MUST restore source balance.

### BR-TRF-006

A transfer must not be marked SUCCESS until required business steps complete.

## Transaction Rules

### BR-TRX-001

Transaction code MUST be unique.

### BR-TRX-002

Transaction states:

```text
PENDING
PROCESSING
SUCCESS
FAILED
CANCELLED
REVERSED
```

### BR-TRX-003

Invalid state transitions MUST be rejected.

## Concurrency Rules

### BR-CON-001

Concurrent balance modifications MUST be protected.

Allowed strategies:

- Optimistic locking.
- Pessimistic locking.
- Atomic SQL update where appropriate.
- Distributed lock where required.

Account entity includes:

```text
version
```

for optimistic locking.

## Event Rules

### BR-EVT-001

Each event MUST have unique eventId.

### BR-EVT-002

Business events MUST be durable.

### BR-EVT-003

Consumers MUST tolerate duplicate event delivery.

### BR-EVT-004

Event consumers SHOULD be idempotent.
