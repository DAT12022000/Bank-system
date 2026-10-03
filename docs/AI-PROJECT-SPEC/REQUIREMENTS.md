# Functional Requirements

## 1. Customer

### FR-CUS-001 Create Customer

System MUST allow creating customer.

Input:

- fullName
- email
- phone

Output:

- customerId
- customerCode
- status
- timestamps

Initial status:

```text
ACTIVE
```

### FR-CUS-002 Get Customer

Get customer by ID.

### FR-CUS-003 Update Customer

Update profile information.

### FR-CUS-004 Change Customer Status

Supported:

```text
ACTIVE
BLOCKED
INACTIVE
```

## 2. Account

### FR-ACC-001 Create Account

Account requires valid customer.

Account fields:

- accountNumber
- customerId
- accountType
- currency
- balance
- status

Initial balance:

```text
0
```

Initial status:

```text
ACTIVE
```

### FR-ACC-002 Get Account

Return account details.

### FR-ACC-003 Get Balance

Return current balance.

### FR-ACC-004 Block Account

Blocked account cannot perform financial transactions.

### FR-ACC-005 Close Account

Closed account cannot perform financial transactions.

## 3. Deposit

### FR-DEP-001 Deposit

Deposit amount MUST be greater than zero.

Flow:

```text
Create transaction
→ validate account
→ increase balance
→ create transaction entry
→ publish event
→ complete transaction
```

## 4. Withdraw

### FR-WDR-001 Withdraw

Conditions:

- Account ACTIVE.
- Amount > 0.
- Sufficient balance.

Flow:

```text
Create transaction
→ validate account
→ check balance
→ decrease balance
→ create entry
→ publish event
→ complete transaction
```

## 5. Transfer

### FR-TRF-001 Transfer

Transfer requires:

- source account.
- target account.
- amount.
- currency.

Conditions:

- source != target.
- source ACTIVE.
- target ACTIVE.
- amount > 0.
- sufficient balance.

## 6. Transaction History

### FR-TRX-001 List Account Transactions

Support:

- accountId
- page
- size
- transaction type
- status
- date range

## 7. Audit

### FR-AUD-001 Audit Transaction

Audit Service consumes transaction events.

Audit record should contain:

- eventId
- transactionId
- action
- oldStatus
- newStatus
- actor
- message
- timestamp

## 8. Notification

### FR-NOT-001 Transaction Notification

Notification Service consumes successful/failed transaction events.

Supported channels can include:

- EMAIL
- SMS
- PUSH

Actual provider is implementation-dependent.

## 9. Idempotency

### FR-IDP-001 Duplicate Request

Same idempotency key + same logical request MUST NOT execute the financial operation twice.

## 10. Observability

### FR-OBS-001 Trace

Each request should be traceable across services.

Minimum:

```text
traceId
correlationId
```
