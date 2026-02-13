# API 빌드 가이드

이 문서 섹션은 터미널 콘솔 환경에서 Apache Maven 3.9.x를 사용하여 Myplatform API 모듈을 빌드하고 실행 가능한 JAR 파일을 생성하는 방법을 안내합니다.

## 1. 사전 요구 사항

*   JDK 21 이상 설치 확인: `java -version`
*   Apache Maven 3.9.x 설치 확인: `mvn -version`
*   프로젝트 루트 디렉토리로 이동

## 2. 빌드 명령어

터미널에서 프로젝트 루트 경로로 이동한 후, 다음 명령어를 입력하여 빌드를 수행합니다.

```bash
mvn clean install -pl api -am -DskipTests
```

## 3. 빌드 결과물 확인

빌드가 성공(BUILD SUCCESS)하면 api/target 디렉토리에 실행 파일이 생성됩니다

```
api/ 
└── target
    ├── api-1.0.0-SNAPSHOT.jar
    └── api-1.0.0-SNAPSHOT.jar.original
```

## 4. 애플리케이션 실행

생성된 JAR 파일을 터미널에서 바로 실행합니다.

```bash
java -Dspring.profiles.active={개발환경} -jar -Dserver.port={웹서비스포트번호} api/target/api-1.0.0-SNAPSHOT.jar
```
<br><br>


# API Swagger UI 사용 가이드

이 문서 섹션은 Myplatform API 서버가 제공하는 Swagger UI (OpenAPI 3)를 통해 API 명세를 열람하는 방법을 안내합니다.

## 1. 접속 방법

API 서버가 정상적으로 실행 중일 때, 웹 브라우저를 열고 다음 주소로 접속합니다.

*   로컬 환경 (기본 포트 8080):
    [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
<br><br><br>


# DB Migrator 가이드

이 문서 섹션은 Laravel 12.x 프레임워크의 Migration 기능을 활용하여 데이터베이스 스키마를 관리하기 위한 Docker 환경 설정 및 사용 방법을 안내합니다.

## 1. 환경 구성

### 디렉토리 구조
```
db-migrator/ 
├── Dockerfile
├── docker-compose.yml
└── migrations/          # 마이그레이션 파일들이 위치하는 곳
```

### Dockerfile
PHP 8.3 CLI 환경을 기반으로 Laravel 구동에 필요한 확장 모듈과 Composer가 설치되어 있습니다.

- Base Image: `php:8.3.29-cli-trixie`
- User: `www-data` (보안을 위해 비-root 사용자 사용)
- Timezone: `Asia/Seoul`

### docker-compose.ym
`myplatform-db-migrator` 컨테이너를 정의합니다.

- Volume Mount:
    - `.env`: 환경 변수 설정 (DB 접속 정보 등)
    - `./migrations`: 호스트의 마이그레이션 파일들을 컨테이너 내부의 `/var/www/html/database/migrations`로 마운트하여 동기화합니다.

## 2. 설치 및 실행

### 1) 컨테이너 빌드 및 실행

`db-migrator` 디렉토리로 이동하여 다음 명령어를 실행합니다.

```bash
docker compose up -d
```

컨테이너가 정상적으로 실행되면 myplatform-db-migrator라는 이름의 컨테이너가 백그라운드에서 대기 상태(tail -f)로 유지됩니다.

### 2) 환경 변수 설정 (.env)

db-migrator 디렉토리 내에 .env 파일을 생성하고 데이터베이스 접속 정보를 설정해야 합니다.
로컬 개발 환경은 다음과 같이 설정되어 있습니다. 개발 환경에 따라 적절한 값으로 수정하여 주세요.

```
DB_CONNECTION=mysql           # 데이터베이스 시스템 종류 
DB_HOST=host.docker.internal  # 데이터베이스 호스트 주소
DB_PORT=3306                  # 데이터베이스 서비스 포트 번호
DB_DATABASE=myplatform        # 데이터베이스 인스턴스 이름
DB_USERNAME=myplatform        # 데이터베이스 접속 계정 이름
DB_PASSWORD=admin12345!@      # 데이터베이스 접속 계정 암호
```

### 3) 마이그레이션 적용

적용을 위해 다음 구문을 실행하여 주십시오.

```bash
docker exec -it myplatform-db-migrator php artisan migrate
```

롤백을 하신다면 다음 구문을 실행하여 주십시오.

```bash
docker exec -it myplatform-db-migrator php artisan migrate:rollback
```

