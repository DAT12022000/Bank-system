# Coding Standards

## 1. Java

Use Java 21.

Prefer:

- Records for immutable DTOs where appropriate.
- `BigDecimal` for money.
- `Optional` for appropriate repository/query results.
- Constructor injection.

Avoid field injection.

Preferred:

```java
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
```

## 2. Package Structure

Recommended:

```text
com.example.account
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── exception
├── config
├── event
└── client
```

## 3. Controller

Controller should:

- Validate input.
- Delegate to application service.
- Return DTO.

Controller should not contain business logic.

## 4. Service

Service contains business rules and orchestration.

## 5. Repository

Repository contains persistence access.

Do not place business logic in repository.

## 6. DTO

Do not expose JPA entity directly as public API response.

Use DTO.

## 7. Entity

JPA entity should represent persistence model.

## 8. Naming

Classes:

```text
AccountService
TransferService
TransactionController
AccountRepository
```

Methods:

```text
createAccount()
getBalance()
processTransfer()
```

Constants:

```text
MAX_RETRY_COUNT
DEFAULT_PAGE_SIZE
```

## 9. Exception

Use domain/application exceptions.

Example:

```java
throw new InsufficientBalanceException();
```

Global handler:

```java
@RestControllerAdvice
```

## 10. Logging

Use structured logs where possible.

Include:

```text
traceId
correlationId
transactionId
accountId
```

Do not log sensitive credentials.

## 11. Transactions

Use:

```java
@Transactional
```

only for local DB transaction boundaries.

## 12. Tests

Test naming:

```text
shouldCreateAccountSuccessfully()
shouldRejectWithdrawWhenBalanceIsInsufficient()
shouldRejectTransferToSameAccount()
```

## 13. SQL

Prefer explicit columns.

Avoid production code using:

```sql
SELECT *
```

Use indexes for:

- account_number
- customer_id
- transaction_code
- account_id
- created_at
- status where useful

## 14. API

REST endpoints should use nouns/resources where possible.

Financial commands can use explicit command-oriented endpoints such as:

```text
POST /transactions/deposit
POST /transactions/withdraw
POST /transactions/transfers
```
