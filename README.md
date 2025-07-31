# SZS Backend API

SZS Backend API는 소득 정보 스크래핑, 결정세액 조회, 회원가입/로그인을 위한 Spring Boot 기반의 삼쩜삼 백엔드 API입니다.  
소득 정보 스크래핑, 결정세액 조회, 회원가입/로그인 기능을 제공합니다.

---

## 🛠️ 기술 스택

| 구분       | 사용 기술 |
|------------|-----------|
| Language   | Java 17 |
| Framework  | Spring Boot |
| Build Tool | Gradle (Kotlin DSL) |
| DB         | H2 Database (개발용) |
| Auth       | Spring Security + JWT |
| API Doc    | Springdoc OpenAPI (Swagger UI) |
| ETC        | Lombok, Hibernate Validator |

---

## 📂 주요 기능

### 👤 사용자 인증
- 회원가입 가능 여부 확인
- 회원가입
- 로그인 (JWT)

### 💼 소득 정보 스크래핑
- 홈택스 기반 사용자 소득 정보 자동 수집  

### 📄 결정세액 조회
- 연도별 결정세액 요약 조회  
---

## 🔐 인증

- JWT 기반 인증 및 인가 처리
- 로그인 시 Access Token 발급
- 인증이 필요한 요청에 아래와 같은 헤더 필요
  `Authorization: Bearer <ACCESS_TOKEN>`
---

## 📑 API 문서

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🏁 실행 방법

### 1️⃣ 프로젝트 클론 ( Git 저장소 )
```bash
git clone https://github.com/eschoeDeveloper/szs-eschoe.git
cd szs-eschoe
```
### 2️⃣ 프로젝트 빌드 및 실행
```bash
cd carrental-api
./gradlew clean build
java -jar build/libs/szs-eschoe-0.0.1-SNAPSHOT.jar
```

### 3️⃣ 접속 확인
```text
API 서버 : http://localhost:8080
Swagger 문서 : http://localhost:8080/swagger-ui.html
H2 콘솔 : http://localhost:8080/h2-console
```

---

### 🧾 라이선스
- 본 프로젝트는 MIT License를 따릅니다.

---

### 🙋 문의
- 작성자: Choe Eui Seung
- 이메일: develop.eschoe@gmail.com
