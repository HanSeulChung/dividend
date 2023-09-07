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
<li>Windows</li>
<li>Java 11</li>
<li>Gradle 7.2</li>
<li>IntelliJ 2023.1.4</li>

<li> Spring boot 2.5.6 </li>
<li> JDK : temurin-11 </li>
<li> Redis </li>
<li> H2 database 1.4.200 </li>
<li> Junit5 </li>
<li> JPA </li>
<li> Scheduler </li>
<li> jsoup 1.7.2 </li>
<li> jsonwebtoken 0.9.1 </li>
<li> apche.commons 4.3 </li>
<li> Lombok </li>
<li> Swagger 3.0.0 </li>
