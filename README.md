# ERD
<img width="960" height="304" alt="Image" src="https://github.com/user-attachments/assets/0bd3cfbc-2ef2-407a-9ee9-e0ed12266a51" />

# API 명세서

| 구분 | 동작 | HTTP 메서드 | URI | 
| :--- | :--- | :--- | :--- |
| **지원자 서류(Application)** | 지원자 서류 등록 | `POST` | `/api/applications` |
| | 지원자 서류 리스트 조회 | `GET` | `/api/applications` |
| | ID에 따라 서류 상세 정보 조회 | `GET` | `/api/applications/{applicationId}` |
| | 지원자 서류에 좋아요 추가 | `PATCH` | `/api/applications/{applicationId}/likes/{managerId}` |
| | 좋아요 누른 운영진 목록 조회 | `GET` | `/api/applications/{applicationId}/likes` |
| **지원자(Applicant)** | 지원자 정보 조회 | `GET` | `/api/applicants/{applicantId}` |
| **운영진(Manager)** | 운영진 정보 조회 | `GET` | `/api/managers/{managerId}` |
| | 운영진 정보 수정 | `PATCH` | `/api/managers/{managerId}` |
