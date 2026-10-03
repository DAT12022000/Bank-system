# Banking Transaction & Account Management System

## 1. Project Overview

Hệ thống quản lý khách hàng, tài khoản và giao dịch ngân hàng được xây dựng theo kiến trúc **Microservices** bằng **Java Spring Boot**.

Mục tiêu chính:

- Quản lý khách hàng.
- Quản lý tài khoản ngân hàng.
- Quản lý số dư.
- Thực hiện nạp tiền, rút tiền, chuyển khoản.
- Đảm bảo tính nhất quán và idempotency cho giao dịch tài chính.
- Sử dụng Kafka cho event-driven communication.
- Sử dụng Redis cho cache, idempotency và distributed lock.
- Sử dụng Saga + Compensation cho distributed transaction.
- Sử dụng Outbox Pattern để đảm bảo business transaction và event publication.
- Có Audit và Notification.
- Có API Gateway, JWT Security, Resilience và Observability.

## 2. Technology Stack

| Thành phần | Công nghệ |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| Build | Maven |
| API Gateway | Spring Cloud Gateway |
| Database | SQL Server |
| ORM | Spring Data JPA / Hibernate |
| Messaging | Apache Kafka |
| Cache | Redis |
| Security | Spring Security + JWT |
| Resilience | Resilience4j |
| Container | Docker / Docker Compose |
| Observability | OpenTelemetry, Prometheus, Grafana |
| API | REST |
| Documentation | OpenAPI / Swagger |

## 3. Services

- `api-gateway`
- `customer-service`
- `account-service`
- `transaction-service`
- `audit-service`
- `notification-service`

## 4. Core Principles

1. Database-per-service.
2. Không tạo physical foreign key giữa database của các service.
3. Account Service là owner duy nhất của account balance.
4. Financial write APIs phải hỗ trợ idempotency.
5. Amount phải dùng `DECIMAL(19,4)` ở database và `BigDecimal` trong Java.
6. Không dùng `double` hoặc `float` cho tiền.
7. Event phải có event ID duy nhất.
8. Business transaction và outbox event phải commit trong cùng local database transaction.
9. Không để Notification Service quyết định giao dịch thành công hay thất bại.
10. Không tự suy diễn business rule chưa được định nghĩa trong tài liệu.

## 5. AI Implementation Rule

AI phải đọc toàn bộ thư mục `docs/` trước khi implement một feature lớn.

Khi specification không đủ thông tin:

- Không tự tạo business rule quan trọng.
- Không tự thay đổi API contract.
- Không tự tạo cross-service database dependency.
- Ghi nhận nội dung chưa rõ trong `OPEN-QUESTIONS.md`.
- Nếu cần một giả định nhỏ để compile code, phải ghi rõ assumption.

## 6. Requirement Priority

- `MUST`: bắt buộc.
- `SHOULD`: nên có.
- `MAY`: tùy chọn.

## 7. Recommended Implementation Order

1. Infrastructure.
2. Customer Service.
3. Account Service.
4. Transaction Service.
5. Kafka + Outbox.
6. Saga + Compensation.
7. Audit Service.
8. Notification Service.
9. API Gateway + Security.
10. Redis + Resilience.
11. Testing.
12. Observability.
13. Docker deployment.

## 8. Documentation Map

- `AI-INSTRUCTIONS.md`: nguyên tắc dành cho AI coding.
- `ARCHITECTURE.md`: kiến trúc tổng thể.
- `REQUIREMENTS.md`: functional requirements.
- `BUSINESS-RULES.md`: business rules.
- `DATABASE.md`: database và data model.
- `API-SPEC.md`: REST API contracts.
- `KAFKA-EVENTS.md`: event contracts.
- `SAGA.md`: Saga, transaction state và compensation.
- `REDIS.md`: cache, idempotency, distributed lock.
- `SECURITY.md`: authentication/authorization.
- `ERROR-CODES.md`: error contract.
- `CODING-STANDARDS.md`: coding conventions.
- `TESTING.md`: testing strategy.
- `DEVELOPMENT-PLAN.md`: implementation roadmap.
- `OPEN-QUESTIONS.md`: các điểm chưa được quyết định.
