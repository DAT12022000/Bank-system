# REST API Specification

## 1. General Rules

Base path:

```text
/api
```

Headers:

```http
Authorization: Bearer <JWT>
Content-Type: application/json
X-Correlation-Id: <uuid>
Idempotency-Key: <uuid>
```

`Idempotency-Key` is required for financial write operations.

## 2. Customer APIs

### API-CUS-001

```http
POST /api/customers
```

Request:

```json
{
  "fullName": "Nguyen Van A",
  "email": "a@example.com",
  "phone": "0900000000"
}
```

### API-CUS-002

```http
GET /api/customers/{id}
```

### API-CUS-003

```http
PUT /api/customers/{id}
```

### API-CUS-004

```http
PATCH /api/customers/{id}/status
```

## 3. Account APIs

### API-ACC-001

```http
POST /api/accounts
```

Request:

```json
{
  "customerId": 1001,
  "accountType": "PAYMENT",
  "currency": "VND"
}
```

### API-ACC-002

```http
GET /api/accounts/{accountId}
```

### API-ACC-003

```http
GET /api/accounts/{accountId}/balance
```

### API-ACC-004

```http
PATCH /api/accounts/{accountId}/status
```

## 4. Deposit

### API-DEP-001

```http
POST /api/transactions/deposit
```

Request:

```json
{
  "accountId": 10001,
  "amount": 1000000,
  "currency": "VND",
  "description": "Cash deposit"
}
```

Response:

```json
{
  "transactionId": 90001,
  "transactionCode": "TXN-20260927-000001",
  "type": "DEPOSIT",
  "amount": 1000000,
  "currency": "VND",
  "status": "SUCCESS"
}
```

## 5. Withdraw

### API-WDR-001

```http
POST /api/transactions/withdraw
```

Request:

```json
{
  "accountId": 10001,
  "amount": 500000,
  "currency": "VND",
  "description": "Cash withdrawal"
}
```

## 6. Transfer

### API-TRF-001

```http
POST /api/transactions/transfers
```

Request:

```json
{
  "sourceAccountId": 10001,
  "targetAccountId": 10002,
  "amount": 250000,
  "currency": "VND",
  "description": "Transfer"
}
```

Response for asynchronous processing MAY initially be:

```json
{
  "transactionId": 90002,
  "transactionCode": "TXN-20260927-000002",
  "status": "PENDING"
}
```

Final status is obtained through transaction query/event-driven update.

## 7. Transaction Query

### API-TRX-001

```http
GET /api/transactions/{transactionId}
```

### API-TRX-002

```http
GET /api/accounts/{accountId}/transactions?page=0&size=20
```

Optional filters:

```text
type
status
fromDate
toDate
```

## 8. HTTP Status

| Status | Usage |
|---|---|
| 200 | Successful query/update |
| 201 | Created |
| 202 | Accepted for asynchronous processing |
| 400 | Validation/business error |
| 401 | Unauthenticated |
| 403 | Unauthorized |
| 404 | Resource not found |
| 409 | Conflict/idempotency/concurrency |
| 429 | Rate limit |
| 500 | Internal error |
| 503 | Dependency unavailable |

## 9. Standard Error

```json
{
  "timestamp": "2026-09-27T10:00:00Z",
  "status": 400,
  "code": "INSUFFICIENT_BALANCE",
  "message": "Insufficient account balance",
  "traceId": "abc-123"
}
```

## 10. API Design Constraints

- No direct client access to internal service ports.
- Gateway is the external entry point.
- Financial commands must be idempotent.
- API responses must not expose internal stack traces.
