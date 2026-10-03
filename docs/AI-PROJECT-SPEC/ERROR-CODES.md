# Error Codes

## Customer

| Code | Meaning |
|---|---|
| CUSTOMER_NOT_FOUND | Customer does not exist |
| CUSTOMER_BLOCKED | Customer is blocked |
| CUSTOMER_INACTIVE | Customer is inactive |
| DUPLICATE_CUSTOMER | Duplicate customer data |

## Account

| Code | Meaning |
|---|---|
| ACCOUNT_NOT_FOUND | Account does not exist |
| ACCOUNT_BLOCKED | Account is blocked |
| ACCOUNT_CLOSED | Account is closed |
| DUPLICATE_ACCOUNT | Account already exists |
| INVALID_ACCOUNT_STATUS | Invalid status |

## Money

| Code | Meaning |
|---|---|
| INVALID_AMOUNT | Amount <= 0 or invalid |
| INVALID_CURRENCY | Currency is invalid |
| INSUFFICIENT_BALANCE | Balance is insufficient |
| BALANCE_UPDATE_FAILED | Balance update failed |

## Transaction

| Code | Meaning |
|---|---|
| TRANSACTION_NOT_FOUND | Transaction does not exist |
| TRANSACTION_FAILED | Transaction failed |
| TRANSACTION_INVALID_STATE | Invalid state transition |
| TRANSFER_SELF_ACCOUNT | Source = target |
| DUPLICATE_REQUEST | Idempotency key conflict |
| TRANSACTION_TIMEOUT | Transaction timed out |

## Security

| Code | Meaning |
|---|---|
| UNAUTHORIZED | Missing/invalid authentication |
| FORBIDDEN | Insufficient permission |
| TOKEN_EXPIRED | JWT expired |

## Infrastructure

| Code | Meaning |
|---|---|
| SERVICE_UNAVAILABLE | Dependency unavailable |
| RATE_LIMIT_EXCEEDED | Too many requests |
| INTERNAL_ERROR | Unexpected server error |

## Standard Response

```json
{
  "timestamp": "2026-09-27T10:00:00Z",
  "status": 400,
  "code": "INSUFFICIENT_BALANCE",
  "message": "Insufficient account balance",
  "traceId": "abc-123"
}
```

Never expose:

- Java stack trace.
- SQL statement.
- Database credentials.
- Internal service topology.
