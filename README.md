# Study-AlarmPushServer
개인 스터디 프로젝트 - 알람 푸쉬 연동 서버

- 기능 명세서
    - 회원가입
        - email, nickname은 중복 방지
    - 로그인
        - 토큰 반환
    - 알람 등록
        - 사용자 id당 최대 50건 등록가능
        - 닉네임, 메시지, 알람등록 시간, 이미지 (optional)
    - 알람 변경
        - 등록한 알람 일련번호(pk)와 닉네임 일치 시, 변경 가능
    - 알~~람 조회 1건~~
        - ~~등록한 알람 일련번호 (primary key) 기준 조회~~
    - 알람 조회 all
        - 사용자 id 기반 조회
    - 알람 삭제 1건
        - 사용자 id 및 일련번호(pk) 기준 삭제
    - 알람 삭제 all
        - 사용자 id 기반 삭제
- DB 설계
    <img width="619" alt="스크린샷 2023-10-08 오후 3 56 39" src="https://github.com/fktk8956/Study-AlarmPushServer/assets/41321260/f1732dd1-9480-49cf-b5b2-038c05bae6cd">

- Batch
    - 30분 간격으로 수행
    - 현재까지 쌓여있는 알람을 일괄 전송
        - slack push
- 기술 스택
    - Language : JAVA17, SpringBoot 3.x?, Spring Data JPA
    - DB : postgreSQL
    - Network : HTTP 1.1
    - Safety : JWT
- Etc
    - Github 사용해보기
        - id,pwd, ip 등 민감한 정보가 push되지 않도록 주의하자
    - TDD를 적극 반영하여 API 테스트코드 작성하기
    - [profile 설정을 통해서 local과 운영 DB를 분리해보기](https://zzang9ha.tistory.com/348)
    - 처음에는 알람을 문구만 등록하도록 구현하고, 추후에 이미지 파일도 함께 등록하도록 기능 추가 예정
        - 신규 db 컬럼이 추가되었을때, DB서버에 직접 insert해주는 등 고민해보기
    - local로 구현이 완료되면 aws에 올릴 수 있는 방법 공부하기
        - local의 DB를 aws에 마이그레이션도 고려해야함
    - 추후에는 슬랙 외의 메시지 push 시스템도 연동해보자
- Reference
    - [Slack 알림 구현 참고](https://develop-writing.tistory.com/142)
    - **[AWS 서버 구축하기](https://hermeslog.tistory.com/671)**
    - [JWT](https://thalals.tistory.com/436)
- 진행 일정
    - 2023-10-09 (일). 회원가입/로그인 서비스 구현 및 테스트
    - JWT 토큰을 통한 로그인 로직 구현 및 테스트
