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

- 서비스 URL: https://EC2_PUBLIC_IP.nip.io
- Swagger URL: https://EC2_PUBLIC_IP.nip.io/swagger-ui/index.html

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
    server_name EC2_PUBLIC_IP.nip.io;

    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```