# 🍋 Lemon Ticket

멜론티켓(Melon Ticket)을 모티브로 한 **공연 티켓 예매 API 서버** 프로젝트입니다.

## 프로젝트 소개

공연/콘서트 티켓을 검색하고 예매할 수 있는 RESTful API 서버입니다.
동시 접속이 몰리는 티켓팅 환경에서의 동시성 제어와 안정적인 예매 처리를 목표로 합니다.

## 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.3 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Build | Gradle |
| API Docs | Swagger (springdoc-openapi) |
| Test | JUnit 5, H2 (테스트용) |

## 시작하기

### 사전 요구사항

- Java 17+
- MySQL
- 환경변수 설정: `DATABASE_USERNAME`, `DATABASE_PASSWORD`

### 빌드 및 실행

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun

# 테스트
./gradlew test
```

실행 후 API 문서는 `/swagger-ui/index.html`에서 확인할 수 있습니다.

## 프로젝트 구조

```
src/main/java/com/flab/lemonticket/
├── LemonTicketApplication.java    # 애플리케이션 진입점
├── controller/                    # REST API 컨트롤러 (예정)
├── service/                       # 비즈니스 로직 (예정)
├── repository/                    # 데이터 접근 계층 (예정)
├── domain/                        # 엔티티/도메인 모델 (예정)
└── dto/                           # 요청/응답 DTO (예정)
```
