# Documentation Index

Đây là bộ specification chuẩn cho AI coding.

## Reading order

1. AI-INSTRUCTIONS.md
2. README.md
3. ARCHITECTURE.md
4. REQUIREMENTS.md
5. BUSINESS-RULES.md
6. DATABASE.md
7. API-SPEC.md
8. KAFKA-EVENTS.md
9. SAGA.md
10. REDIS.md
11. SECURITY.md
12. ERROR-CODES.md
13. CODING-STANDARDS.md
14. TESTING.md
15. DEVELOPMENT-PLAN.md
16. OPEN-QUESTIONS.md

## Suggested AI Prompt

```text
Đọc toàn bộ bộ tài liệu AI-PROJECT-SPEC trước khi code.

Tuân thủ AI-INSTRUCTIONS.md và các business rule trong BUSINESS-RULES.md.

Không tự suy diễn business rule chưa được định nghĩa.

Task hiện tại:
[ghi task tại đây]

Trước khi code:
1. Xác định service bị ảnh hưởng.
2. Liệt kê các file specification liên quan.
3. Nêu các business rule áp dụng.
4. Nêu API/event/database contract bị ảnh hưởng.
5. Nếu có điểm chưa rõ, tham chiếu OPEN-QUESTIONS.md.

Sau đó implement code + test.

Không thay đổi API contract hoặc service boundary nếu chưa được yêu cầu.
```
