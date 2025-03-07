<p align="center">
  <img src="https://github.com/C-KOMACHI/INUsed/blob/master/public/Logo.png" alt="Logo" width="500">
</p>



<div align=center>

  <br/><h2>🛒에브리 타임은 장터게시판은 그만! 우리 학교 학생들끼리의 중고거래🛒</h2>
  학생들끼리의 거래를 돕는 **인천대 중고거래 서비스** **INUsed** 입니다. </br>

<br>

### **🤷‍♂️***사용자 친화적이지 않은 에브리 타임의 장터게시판*
*→ 가격, 판매 중인지, 판매자의 정보, 물품등을 열람할 수 없음* </br>

<p align="center">
  <img src="https://github.com/user-attachments/assets/daa2b01d-3a8f-42d6-a814-262afc09c784" alt="image" width="200">
</p>

<br>

### 🚗너무 넓은 설정 범위의 당근 마켓
*→ 너무 설정 범위가 넓어 학생들끼리 안심 거래가 불가능*

<p align="center">
  <img src="https://github.com/user-attachments/assets/daa2b01d-3a8f-42d6-a814-262afc09c784" alt="image" width="200">
</p>

<br><br>


<strong><span style="font-size: 50px;">학교 내 빠른 직거래가 가능하며</span></strong> <br>
<strong><span style="font-size: 50px;">UI와 기능이 중고거래에 적합한 <span style="color: #ff5733;">INUsed</span> 서비스 제공</span></strong>




</div>

## 👪 팀원 구성

<div align=center>

### **Back-end**
| [문재경(팀장)](https://github.com/MoonJaeGyeong) | [최지우](https://github.com/yusica09) |
|:-:|:-:|
| <img src="https://avatars.githubusercontent.com/u/108010440?v=4" width="150" height="150"/> | <img src="https://avatars.githubusercontent.com/u/144703345?v=4" width="150" height="150"/> 
| **실시간 채팅, 문의, 메일, 공지, <br>리뷰, 유저, S3 이미지, 신고** | **게시글, 찜** |

</div>



## 🛒 기능 소개

**메인 화면**

<img src="https://github.com/user-attachments/assets/39cc81e9-734d-424e-a9ec-ffd8eab557ba" width="20%">

- **판매 리스트**: 유저들이 판매하고 있는 제품들의 리스트를 불러 옵니다.
- **판매 등록**: 유저가 판매하고 싶은 제품을 등록합니다.

<br/>

**카테고리&관심**

  <img src="https://github.com/user-attachments/assets/7951bd92-bd6b-4c45-b7ab-1aa30d7b8d4e" width="20%"> <img src="https://github.com/user-attachments/assets/d8dc9d85-39e0-4695-9451-e6932b801978" width="20%">

- **카테고리**: 상품을 카테고리 별로 조회할 수 있습니다.
- **관심** : 관심 버튼을 눌러 따로 눈여겨본 상품들을 조회할 수 있습니다.

 <br/>  

**채팅**

  <img src="https://github.com/user-attachments/assets/8d816da9-5905-41cb-987b-e1bc2e62d198" width="20%">

  - **채팅** : 거래를 원하는 유저와 채팅을 해 약속을 잡을 수 있습니다.

 <br/>

**공지사항&사용자 신고**

  <img src="https://github.com/user-attachments/assets/eb2c7419-6066-4367-8025-c3644635aaa4" width="20%"> <img src="https://github.com/user-attachments/assets/3edee989-6a64-43b8-b9f6-eddd14ae897d" width="20%">

- **공지사항**: 사용자들이 주의해야 할 부분에 대한 공지를 조회할 수 있습니다.
- **사용자 신고**: 비매너 혹은 주의가 필요한 사용자를 신고할 수 있습니다.

 <br/>  
 
## 기획서

## **📅 개발 기간 및 작업 관리**

### **⏰ 개발 기간**

- 전체 개발 기간 : 2024-03-02 ~ 2024-05-15

### **👥 UserFlow**

<img src="https://github.com/user-attachments/assets/8719c46e-f4c8-4ba7-829f-1626ff707d75" align="center" width="70%">


### **📊 작업 관리**

- 오프라인 회의 및 디스코드를 통한 온라인 회의를 통하여 서로의 의견을 조율하였습니다.
- 화면 흐름도, 피그마, 유스케이스 등을 문서로 작성하여 이를 토대로 프로젝트의 이해도를 서로 끌어올렸습니다.

### 화면 흐름도


### 유스케이스



---

## 1. 개발 환경

- 버전 및 이슈관리 : Github
- 협업 툴 : Discord, Notion, Jira
- 문서화 : Swagger, Notion, README.md
- 테스트 : Postman
- 디자인 : [Figma](https://www.figma.com/design/IFrA9u3gonvlARjXyq0zcp/INUsed-design?node-id=0-1&p=f&t=iNRLjEYQYChKDIbQ-0)

| **Software**          | **Version / Spec**                              |
|-----------------------|------------------------------------------------|
| **Java**              | Java SE 17                                      |
| **Spring Boot**       | 3.2.1                                          |
| **Spring Boot Modules** | Data JPA, Web, Validation, Security, Mail, Redis, WebSocket |
| **Lombok**            | 1.18.20                                        |
| **Database**          | MariaDB, MongoDB                               |
| **Redis**             | Spring Boot Starter Data Redis                 |
| **WebSocket**         | Spring Boot Starter WebSocket                  |
| **Security**          | Spring Security                               |
| **JWT**               | JJWT 0.11.5                                    |
| **AWS**              | AWS SDK Core & S3 (1.12.100)                    |
| **Jackson**           | Jackson Datatype JSR310, Jackson Databind      |
| **UI Framework**      | Bootstrap 4.3.1, Vue 2.5.16                    |
| **Web Libraries**     | Axios 0.17.1, SockJS 1.1.2, Stomp WebSocket 2.3.3-1 |
| **Serialization**     | Gson 2.8.0                                     |
| **Documentation**     | Swagger (SpringDoc OpenAPI UI 2.2.0)           |
| **Build Tools**       | Gradle                                         |

---

## 2. Entity Relationship Diagram

---

## 3. 프로젝트 패키지 구조

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂c_comachi
 ┃ ┃ ┃ ┃ ┗ 📂inused
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂chat
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂inquiry
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂keyword
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂implement
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notice
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂post
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂report
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂review
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂s3
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂users
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂util
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂wish
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂global
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┣ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┣ 📂templates
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┗ 📂c_comachi
 ┃ ┃ ┃ ┃ ┗ 📂inused
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂users
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂global
```

---

## 4. 역할 분담

### 😎 문재경

- **실시간 채팅**
  - WebSocket 과 Redis Pub/Sub 을 이용한 실시간 채팅 기능 구현
  - 채팅, 채팅방 관련 기능 구현
  - MongoDB 를 이용한 채팅 기록 저장
- **문의**
  - 문의 관련 API 구현
- **멤버**
  - 멤버 관련 API 구현
  - Security 를 통한 유저 권한 체크 및 인증 구현
- **설정**
  -  Swagger 설정
  -  공통 응답 메서드 구현

<br>

### 🙃 최지우

- **게시글**
  - 게시글 CRUD 구현
  - 타인 게시글 조회 구현

- **찜**
  - 찜 목록에 게시글 추가 기능 구현

<br>


---

## 5. API 명세서

###  [Swagger](https://inused.store/swagger-ui/index.html)

