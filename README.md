# WebWatcherBatch
웹 와쳐 테스트 시나리오 배치 실행을 위한 사이드 프로젝트

## 기능
Katalon Recorder로 생성된 JSON형식의 시나리오 파일을 DB에서 가져와 Spring Batch와 Quartz를 이용해 정해진 시간에 Chrome Driver로 웹 테스팅을 진행하고 결과를 DB에 저장해 준다.
이때 성공의 기준은 정해진 시간 (60초)이내로 시나리오 파일이 작동 가능한가 이다. 실패의 기준은 성공의 기준 외 모든 경우를 말하면 시간 초과, 형식이 어긋난 시나리오 파일, 행동할 수 없는 테스트 케이스 등이 있다. 이 경우 해당 에러 문구를 DB에 함께 저장한다.
