# Open Questions

Các nội dung dưới đây chưa được chốt thành business rule. AI không được tự quyết định nếu implementation phụ thuộc trực tiếp vào chúng.

## OQ-001 Currency

MVP có chỉ hỗ trợ:

```text
VND
```

hay nhiều currency?

## OQ-002 Customer Account Limit

Một customer được sở hữu tối đa bao nhiêu account?

## OQ-003 Account Closing

Có cho phép đóng account khi balance > 0 không?

## OQ-004 Blocked Customer

Customer BLOCKED có được phép nhận tiền vào account không?

## OQ-005 Transfer Processing

Transfer API có:

- synchronous response
- hay asynchronous PENDING response

là contract cuối cùng?

## OQ-006 Notification

Provider thực tế:

- Email SMTP
- SMS provider
- Push provider

chưa được chọn.

## OQ-007 Authentication

JWT issuer/identity provider chưa được quyết định.

Có thể dùng:

- Keycloak.
- Auth0.
- Custom authentication service.

## OQ-008 Ledger

MVP có yêu cầu double-entry accounting ledger đầy đủ không?

Hiện tại:

```text
TRANSACTION_ENTRY
```

chỉ là transaction/balance history model, chưa phải accounting ledger hoàn chỉnh.

## OQ-009 Timezone

Storage nên thống nhất UTC.

UI có thể convert sang timezone người dùng.

## OQ-010 Transfer Compensation

Business policy khi compensation thất bại cần được xác định thêm.

MVP nên ghi nhận trạng thái cần xử lý và retry thay vì giả định compensation luôn thành công.

## OQ-011 Idempotency Retention

Thời gian lưu idempotency record chưa được chốt.

## OQ-012 Kafka Partition Strategy

Partition key cần được chốt theo aggregate khi triển khai production.

## OQ-013 Database Migration

Chọn:

- Flyway
- Liquibase

Khuyến nghị chọn một công cụ duy nhất cho tất cả service.

## OQ-014 Deployment

MVP local:

```text
Docker Compose
```

Production:

```text
Kubernetes
```

cần được đặc tả riêng nếu triển khai thực tế.
