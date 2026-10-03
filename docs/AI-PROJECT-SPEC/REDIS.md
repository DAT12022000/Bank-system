# Redis Specification

## 1. Use Cases

Redis is used for:

1. Cache.
2. Idempotency.
3. Distributed lock.
4. Short-lived state where appropriate.

## 2. Cache

Potential cache:

```text
account:{accountId}
customer:{customerId}
```

Cache MUST NOT become the authoritative source for account balance.

Authoritative balance remains in Account DB.

## 3. Idempotency

Key:

```text
idempotency:{service}:{idempotencyKey}
```

Value can contain:

```json
{
  "requestHash": "sha256...",
  "transactionId": 90001,
  "status": "SUCCESS",
  "response": {}
}
```

## 4. Request Hash

If the same idempotency key is reused with a different request body:

```text
409 DUPLICATE_REQUEST
```

or another dedicated conflict error.

Do not execute the second request.

## 5. Distributed Lock

Potential key:

```text
lock:account:{accountId}
```

Use only when required.

Lock MUST have:

- TTL.
- Safe release.
- Unique lock owner/token.

Never create an infinite lock.

## 6. Balance Consistency

Do not rely only on Redis for balance protection.

Database transaction/locking is the final consistency boundary.

## 7. Cache Invalidation

After balance mutation:

```text
UPDATE DB
→ invalidate/update cache
→ publish event
```

Exact strategy can be:

- Cache-aside.
- Event-driven invalidation.

## 8. Redis Failure

Financial transaction MUST NOT silently create incorrect balance if Redis is unavailable.

Depending on use case:

- Cache unavailable → fallback to DB.
- Idempotency store unavailable → fail closed for financial command if duplicate execution cannot be safely prevented.
- Lock unavailable → fail safely rather than execute unsafe concurrent operation.
