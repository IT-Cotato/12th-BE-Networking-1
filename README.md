## ERD
<img width="960" height="304" alt="Image" src="https://github.com/user-attachments/assets/0bd3cfbc-2ef2-407a-9ee9-e0ed12266a51" />

## API 명세서

| <small>구분 | <small>동작 | <small>HTTP 메서드 | <small>URI | 
| :--- | :--- | :--- | :--- |
| **<small>지원자 서류(Application)** | <small>지원자 서류 등록 | <small>`POST` | <small>`/api/applications` |
| | <small>지원자 서류 리스트 조회 | <small>`GET` | <small>`/api/applications` |
| | <small>ID에 따라 서류 상세 정보 조회 | <small>`GET` | <small>`/api/applications/{applicationId}` |
| | <small>지원자 서류에 좋아요 추가 | <small>`PATCH` | <small>`/api/applications/{applicationId}/likes/{managerId}` |
| | <small>좋아요 누른 운영진 목록 조회 | <small>`GET` | <small>`/api/applications/{applicationId}/likes` |
| <small>**지원자(Applicant)** | <small>지원자 정보 조회 | <small>`GET` | <small>`/api/applicants/{applicantId}` |
| <small>**운영진(Manager)** | <small>운영진 정보 조회 | <small>`GET` | <small>`/api/managers/{managerId}` |
| | <small>운영진 정보 수정 | <small>`PATCH` | <small>`/api/managers/{managerId}` |
