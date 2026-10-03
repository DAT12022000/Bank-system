# AI Coding Instructions

## 1. Role

Bạn là AI Software Engineer hỗ trợ phát triển hệ thống Banking Transaction & Account Management System.

Mục tiêu là tạo code production-oriented, dễ test, dễ bảo trì và tuân thủ architecture/specification.

## 2. Mandatory Rules

### AI-001 — Read Specification First

Trước khi implement feature, phải đọc các tài liệu liên quan trong `docs/`.

### AI-002 — Do Not Invent Business Rules

Không tự suy diễn các business rule ảnh hưởng đến tiền, trạng thái giao dịch, quyền hạn hoặc dữ liệu.

Nếu thiếu thông tin, đưa vào `OPEN-QUESTIONS.md`.

### AI-003 — Service Ownership

Mỗi domain có một service owner.

| Domain | Owner |
|---|---|
| Customer | Customer Service |
| Account + Balance | Account Service |
| Transaction | Transaction Service |
| Audit | Audit Service |
| Notification | Notification Service |

### AI-004 — Database-per-Service

Mỗi service có database riêng.

Không:

- JOIN trực tiếp database khác service.
- Repository truy cập DB của service khác.
- Physical FK giữa database khác service.

### AI-005 — Money

Java:

```java
BigDecimal
```

Database:

```sql
DECIMAL(19,4)
```

Không dùng `double` hoặc `float`.

### AI-006 — API Stability

Không tự ý thay đổi:

- HTTP method.
- URL.
- Request field.
- Response field.
- Error code.

Nếu cần thay đổi, cập nhật `API-SPEC.md`.

### AI-007 — Financial Write Idempotency

Các API financial write phải hỗ trợ idempotency.

Ví dụ:

```http
Idempotency-Key: 6c8f...
```

### AI-008 — Balance Ownership

Chỉ Account Service được phép thay đổi balance.

### AI-009 — Event Reliability

Event business quan trọng phải đi qua Outbox Pattern.

### AI-010 — Traceability

Request và event phải hỗ trợ:

- `traceId`
- `correlationId`
- `eventId`

## 3. Implementation Style

Ưu tiên:

```text
Controller
   ↓
Application Service
   ↓
Domain/Business Logic
   ↓
Repository
```

Integration:

```text
Service
  ↓
Client / Event Publisher
  ↓
Other Service / Kafka
```

Không đặt business logic phức tạp trong Controller.

## 4. Error Handling

Không trả exception stack trace cho client.

Sử dụng standardized error response theo `ERROR-CODES.md`.

## 5. Transaction Rules

Local DB transaction dùng:

```java
@Transactional
```

Không cố dùng một local `@Transactional` để bao phủ nhiều microservice.

Distributed transaction phải sử dụng Saga/Compensation.

## 6. AI Workflow

Khi nhận task:

1. Xác định service.
2. Đọc requirement.
3. Đọc business rules.
4. Đọc database model.
5. Đọc API/event contract.
6. Kiểm tra dependency.
7. Implement.
8. Viết unit test.
9. Viết integration test nếu cần.
10. Kiểm tra consistency với architecture.

## 7. Definition of Done

Một feature chỉ được coi là hoàn thành khi:

- Code compile.
- Unit test pass.
- Business rules được cover.
- Error cases được xử lý.
- API contract đúng specification.
- Logging/trace phù hợp.
- Không phá service boundary.
- Documentation được cập nhật nếu contract thay đổi.
