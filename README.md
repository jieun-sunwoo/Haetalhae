# 📱 해탈해 (Haetalhae) - 불교 생활 도우미 앱

> 졸업 프로젝트 최종 결과물 (Android + Spring Boot)

---

## 📌 개요

**해탈해(Haetalhae)** 앱은 한국 불교 신자, 특히 고령층 신도들이 더 쉽고 편리하게 종교 생활을 이어갈 수 있도록 도와주는 모바일 애플리케이션입니다.  
불자들의 일상적인 기도, 사찰 방문, 커뮤니티 참여를 지원하며, 종교적 디지털 소외를 줄이는 것을 목표로 합니다.

---

## 📁 프로젝트 구성

이 레포지토리는 Android 앱과 Spring Boot 백엔드를 포함하고 있으며, 다음과 같이 구성됩니다:

Haetalhae-Final/

├── app/ # Android 앱 (Kotlin + Jetpack Compose)

├── templebackend/ # Spring Boot 백엔드 서버

├── README.md # 현재 문서

├── .gitignore

└── 기타 설정 파일


---

## 🛠️ 빌드 및 실행 방법

### 1. Android 앱 (앱 폴더: `app/`)

- **Android Studio Bumblebee 이상**에서 열기
- `google-services.json`을 `app/`에 추가 (Firebase 설정 후 다운로드)
- Gradle Sync → 실행

**주요 기능**
- 전국 조계종 사찰 지도 (Google Maps Compose)
- 사찰 상세 페이지 (사진, 역사, 공지사항)
- 커뮤니티 (게시판, 댓글 기능)
- 인기 경전/즐겨찾기/낭독 기능

### 2. Spring Boot 백엔드 (`templebackend/`)

- **IntelliJ IDEA 또는 VS Code**로 열기
- `application.yml`에 MySQL DB 설정 입력
- Gradle 빌드 후 실행 (자동 REST API 서버 구동)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/templedb
    username: root
    password: [your_password]
