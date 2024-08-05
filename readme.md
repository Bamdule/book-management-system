
## 참고
https://daisy-vermicelli-585.notion.site/4c853522427d4e4ab1414734e0872efb

## 기능 요구사항

- [x]  도서는 하나 이상의 카테고리에 속할 수 있다.
- [x]  도서는 지은이, 제목의 정보를 가지고 있다.
- [x]  신규 도서는 항상 카테고리가 필요하다.
- [x]  도서는 훼손 또는 분실 등의 이유로 대여가 중단 될 수 있다.
- [x]  도서는 카테고리가 변경될 수 있다.
- [x]  카테고리 별로 도서를 검색 할 수 있다.
- [x]  지은이와 제목으로 도서를 검색 할 수 있다.

## 프로젝트 스펙
- Spring boot 3.3.0
- JDK 17
- JPA
- H2
- JUnit5 & Mockito
- lombok
- logback
- QueryDsl
- OpenAPI Specification
- multi module 형태로 설계 되었으며 크게 `core:core-api`, `core:core-domain`, `storage:db-jpa` 모듈로 구성된다
  - core
    - core-api
    - core-domain
  - storage
    - db-jpa
## 메소드 규칙 정의
* public static A create(B b)
  * B 객체로 A객체를 생성 정적 팩토리 메소드 
  * id 값이 없음
* public static A of(B b)
    *  B 객체를 A객체로 변환할 때 사용하는 정적 팩토리 메소드 
* public A to()
    *  특정 객체로 A 객체를 생성할 때 사용

## 실행 방법

### **build**

- 터미널 실행 후 프로젝트 상단으로 이동한 다음 아래와 같이 빌드 명령어를 실행한다

```json
./gradlew :core:core-api:build
```

### **jar 실행**

- java 17이 설치되어 있어야합니다.
- 아래와 같이 core/core-api/build/libs에 위치한 `book-management-system-api.jar`를 실행

```json
cd {libs 위치}
java "-Dfile.encoding=UTF-8" -jar book-management-system-api.jar --spring.profiles.active=local
```

### 기본 데이터 삽입

- spring profiles를 local 속성으로 실행할 경우 schema.sql, data.sql이 실행되면서 기본 스키마 및 기본 데이터가 삽입된다.