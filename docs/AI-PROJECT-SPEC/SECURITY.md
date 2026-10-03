# Security Specification

## 1. Authentication

Use:

```text
Spring Security + JWT
```

Gateway validates JWT.

## 2. Authorization

JWT should contain claims such as:

```text
sub
roles
permissions
iat
exp
```

Example roles:

```text
CUSTOMER
STAFF
ADMIN
```

Exact role matrix can be expanded.

## 3. API Access

Public client should access:

```text
API Gateway
```

not internal service ports.

## 4. Passwords

Passwords MUST:

- Never be stored plaintext.
- Use strong password hashing.
- Never be logged.

## 5. Sensitive Data

Do not log:

- Password.
- JWT.
- Secret key.
- Full payment/security credentials.

Account numbers should be masked in logs where appropriate.

## 6. JWT

Validate:

- Signature.
- Expiration.
- Issuer.
- Audience if configured.

## 7. Service-to-Service Security

Internal communication SHOULD use:

- Service credentials.
- mTLS where required.
- Network isolation.

## 8. Input Validation

Use Bean Validation:

```java
@NotNull
@NotBlank
@Positive
@Email
@Size
```

Example:

```java
@Positive
private BigDecimal amount;
```

## 9. Security Headers

Gateway should consider:

- CORS.
- Security headers.
- Rate limiting.

## 10. Audit

Security-sensitive actions should be auditable.
