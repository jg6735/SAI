![image](https://user-images.githubusercontent.com/92859179/204535789-34844f48-0107-4e35-94e1-a2fa6c722c7d.png)


# 웹서비스 소개

SAI는 언제 어디서든 면접 준비가 가능한 모의 면접 컨설팅 서비스입니다.

SAI는 삼성 청년 SW 아카데미의 취업지원센터와 교육생의 연계를 강화하기위해 만들어졌습니다.

SAI는 나혼자 면접 연습 서비스에서 기출 면접 질문, 직접 질문 추가 등을 통해 원하는 질문에 대해 연습이 가능하며 TTS (Text-To-Speech) 기능을 통해 더욱 현실감 있는 면접 환경을 제공합니다.

또한, 나혼자 면접 연습 서비스를 마친 후, 질문 별 영상을 각각 저장하여 제공하며, STT(Speech-To-Text), 음성 높낮이 분석, 자세 분석 (Teachable Machine), 표정 분석(Face API)와 같이 진행한 면접에 대한 분석을 제공합니다.

이와 더불어, 진행한 모의면접을 취업 컨설턴트에게 피드백 요청을 보낼 수 있으며, 취업 컨설턴트는 교육생의 모의 면접 영상에 대해 질문별로 피드백을 남길 수 있습니다.

SAI는 컨설턴트와 1:1 면접 서비스를 통해 취업 컨설턴트와 실시간 화상 1:1 모의면접 기능을 제공합니다.

# 핵심기능

#### 일정관리 
```
컨설턴트는 개인일정을 등록하고 확인 할 수 있고, 교육생은 컨설턴트의 일정으로 인해 불가능한 시간을 확인 할 수 있습니다.
교육생은 해당 시간을 피해 원하는 시간에 모의 면접을 신청할 수 있으며 개인 일정을 등록하여 관리할 수 있습니다.
```

#### 나 혼자 연습
```
컨설턴트와 시간 조율이 쉽지 않기에 나 혼자 연습 기능을 제공합니다.
인성, 직무 별 사전 질문들을 제공하고 개인 별 원하는 질문을 등록하고 원하는 질문만 선택하여 면접연습을 진행 할 수 있습니다. 
혼자 연습이지만 TTS를 통해 질문 스크립트를 읽어주어 실제와 비슷한 환경으로 면접을 진행할 수 있습니다. 
진행한 면접 영상과 음성은 저장되고 컨설턴트에게 피드백 신청을 할 수 있습니다.
```

#### 컨설턴트 1대1 모의 면접 
```
Openvidu를 활용한 컨설턴트 교육생 1대1 모의 면접 기능을 제공합니다.
```

#### 면접 피드백
```
질문 별 답변영상 확인을 통한 컨설턴트 피드백 
Google Speech API STT기능을 이용한 답변 스크립트 생성 
오디오 데이터 시각화
Teachable Machine을 사용한 자세 흔들림 분석 
Face API를 이용한 표정분석
총평과 같은 면접 피드백을 제공합니다.
```

## [📃 개발일지 Wiki](https://lab.ssafy.com/s07-webmobile2-sub2/S07P12C206/-/wikis/home)

## :sparkles: 팀 구성 

<table>
  <tr>
    <td align="center" width="150px">
      <a href="https://github.com/cih831" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204535987-e1d4bf7c-5256-4703-b243-207d42f720a0.png" alt="최인호 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="#" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204536083-0b0193ec-5a7d-4ebb-b842-a9c54e8cf525.png" alt="김지수 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="#" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204536166-f730c1ea-615b-477b-ae7f-3482c643a1bc.png" alt="윤형준 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="https://github.com/jg6735" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204536204-bb3882a5-06df-47f7-9762-f70b0c540503.png" alt="지근 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="https://github.com/simjaeseo" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204536221-5532b8a8-005e-4cac-b6f1-dc07fb564f6b.png" alt="심재서 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="https://github.com/Givem2thekey" target="_blank">
        <img src="https://user-images.githubusercontent.com/92859179/204536225-8da949e6-94f2-48d0-941c-43f81901af40.png" alt="조덕희 프로필" />
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/cih831" target="_blank">
        최인호<br />(Front-end & 팀장)
      </a>
    </td>
    <td align="center">
      <a href="#" target="_blank">
        김지수<br />(Front-end)
      </a>
    </td>
    <td align="center">
      <a href="#" target="_blank">
        윤형준<br />(Front-end)
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/jg6735" target="_blank">
        지근<br />(Back-end)
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/simjaeseo" target="_blank">
        심재서<br />(Back-end)
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Givem2thekey">
        조덕희<br />(Back-end)
      </a>
    </td>
  </tr>
</table>

<br />

<br />

|  이름  |        역할        | <div align="center">개발 내용</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| :----: | :----------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 최인호 | Front-end<br />팀장 | - 메인 홈 페이지 개발<br /> - 일정 관리 페이지 개발<br /> - TTS, decibal graph 등 음성 관련 기능 적용<br /> - Teachablemachine, tensorflow 등 영상 분석 관련 기능 적용<br /> - Vuex를 활용한 상태관리<br /> - 개인 모의 면접 대기실 성능 개선 및 UI 수정<br /> - 모의면접 분석 페이지 UI/UX 개선                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| 김지수 |      Front-end      | - 회원기능개발<br />- 개인 모의면접 기능 개발 <br />- 세부분석 페이지 개발 <br />- 피그마 설계 및 디자인 <br />- 서비스 디자인 및 UI/UX 개발 |
| 지근 |     Back-end      | - 회원 도메인 설계 및 개발<br />- 회원 기능 API 개발<br />- 스프링 시큐리티, JWT 인증을 통한 회원가입 / 로그인 구현<br />- 면접 영상 도메인 설계 및 개발<br />- 면접 정보 및 피드백 관련 API 개발<br />- 회원, 일정, 면접 영상 JPA 개발, 리팩토링<br />- Nginx 웹서버 구축 및 배포<br />- Nginx SSL 적용 |
| 심재서 |     Back-end      | - DB 설계<br />- 일정 도메인 설계 및 개발<br />- 면접 저장 도메인 설계 및 개발<br />- Openvidu 서버 배포<br />- jave 라이브러리를 활용한 mp4 면접파일을 flac 음성파일로 변환<br />- AWS S3, Google Cloud Storage를 활용하여 면접 영상 파일, 음성 파일 저장 기능 구현<br />- Google Speech API를 활용하여 저장된 음성 파일을 스크립트로 변환 (STT 기능)<br />- Spring Boot에 SSL 적용                                                                                                                                                                                                                          |
| 조덕희 |     Back-end      | - 면접 질문 도메인 설계 및 개발<br />- Teachable Machine 통한 자세 인식 모델 학습 및 구현<br />- 면접 질문 데이터 수집<br />- 산출물 관리 (ERD 정리 및 시연시나리오 작성)


## 기술 스택
#### FE
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
<br>

#### BE
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white"> <img src="https://img.shields.io/badge/jpa-6DB33F?style=for-the-badge&logo=jpa&logoColor=white">
<br>

#### Infra
<img src="https://img.shields.io/badge/Nginx-009639C?style=for-the-badge&logo=Nginx&logoColor=white"> <img src="https://img.shields.io/badge/Aws-569A31?style=for-the-badge&logo=amazons3&logoColor=white"> <img src="https://img.shields.io/badge/google-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white">
<br>

#### IDE
<img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white"> <img src="https://img.shields.io/badge/Inteelij-000000?style=for-the-badge&logo=Intellijidea&logoColor=white">
<br>

#### Tool
<img src="https://img.shields.io/badge/POSTMAN-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"> <img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white"> <img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=JIRAsoftware&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
<br>
