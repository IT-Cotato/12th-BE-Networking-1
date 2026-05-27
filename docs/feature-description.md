# 배포 과제 문서

## 1. 아키텍처

사용자
↓
EC2_PUBLIC_IP.nip.io
↓
Nginx
↓
Spring Boot Docker Container
↓
MySQL Docker Container

## 2. 배포 URL

- 서비스 URL: https://13.211.153.115.nip.io
- Swagger URL: https://13.211.153.115.nip.io/swagger-ui/index.html

## 3. Dockerfile

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 4. Nginx 설정

```nginx
server {
    listen 80;
    server_name 13.211.153.115.nip.io;

    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

## 5. 트러블슈팅

문제:
컨테이너에서 DB 연결이 실패했다.

원인:
컨테이너 내부에서 localhost는 DB 컨테이너가 아니라 자기 자신을 의미했다.

해결:
docker-compose 서비스명인 mysql을 DB_HOST로 사용했다.

