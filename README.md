# dividend

## 프로젝트 엔티티
MemberEntity : username, password, 권한이 저장된 엔티티

CompanyEntity: 회사 이름, 회사 ticker가 저장된 엔티티

DividendEntity : 회사 이름, 배당금 날짜, 배당금이 저장된 엔티티

## 제공하는 기능(API)

### 회원가입 및 로그인 관련 기능
- 비밀번호는 암호화된 상태로 database에 저장됩니다.
- 로그인후 토큰 유효 시간은 1시간 입니다.

<ol>
<li>회원 가입 기능</li>
  parameter > 사용자이름, 비밀번호, 접근 권한(READ, WRITE만 구현)
  
<li>로그인 기능</li>
	parameter > 사용자 이름, 비밀번호
</ol>

### 회사와 배당금 관련 기능
- 각 페이지는 사용자의 권한에 따라 제한됩니다.
- READ 권한은 회사, 배당금을 추가할 수 없습니다. 
- 회사를 검색할 때 자동완성 기능을 사용할 수 있습니다.
- Redis 서버의 캐시 사용으로 더 빠른 데이터 접근이 가능합니다.
- 회사 및 배당금 삭제 시 캐시도 삭제됩니다.

<ol>
<li>회사 및 배당금 정보 추가</li>

<li>전체 회사 조회</li>

<li>회사 검색 기능</li>
	자동완성 기능 구현

<li>저장된 회사 삭제</li>

<li>회사명으로 배당금 조회</li>

</ol>

실행 후 http://localhost:8080/swagger-ui/index.html# url을 이용하면 API에 따른 더 많은 정보를 확인할 수 있습니다.

## 활용 기술
<div align=center>
	
![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
![Java 11](https://img.shields.io/badge/Java_11-007396?style=for-the-badge&logo=java&logoColor=white)
![Gradle 7.2](https://img.shields.io/badge/Gradle_7.2-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![IntelliJ IDEA 2023.1.4](https://img.shields.io/badge/IntelliJ_IDEA_2023.1.4-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Spring Boot 2.5.6](https://img.shields.io/badge/Spring_Boot_2.5.6-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![JDK - Temurin 11](https://img.shields.io/badge/JDK-Temurin_11-007396?style=for-the-badge&logo=java&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![H2 Database 1.4.200](https://img.shields.io/badge/H2_Database_1.4.200-004080?style=for-the-badge&logo=h2&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=jpa&logoColor=white)
![Scheduler](https://img.shields.io/badge/Scheduler-424242?style=for-the-badge)
![Jsoup 1.7.2](https://img.shields.io/badge/Jsoup_1.7.2-7139C1?style=for-the-badge)
![Json Web Token (jsonwebtoken) 0.9.1](https://img.shields.io/badge/Json_Web_Token_(jsonwebtoken)_0.9.1-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![Apache Commons 4.3](https://img.shields.io/badge/Apache_Commons_4.3-DB592F?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-BC4520?style=for-the-badge&logo=lombok&logoColor=white)
![Swagger 3.0.0](https://img.shields.io/badge/Swagger_3.0.0-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

 
</div>
