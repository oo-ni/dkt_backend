# 🧩 문제 해결 방법

## 파일 구성
```bash
├── main
│   ├── Main.java
│   ├── LogFileReader.java
│   ├── LogFileWriter.java
│   ├── LogParser.java
│   ├── ParsedData.java
│   ├── LogAnalyzer.java
│   └── AnalyzedData.java
└── test
    └── TestLogParser.java
``` 

## 프로세스

### 1. 로그 파일 읽기
```
LogReader 클래스는 로그 파일에서 각 줄을 읽어와 리스트로 반환합니다.
BufferedReader를 사용하여 데이터 처리 효율성을 높였으며, 읽은 데이터를 ArrayList에 저장합니다.
```

### 2. 로그 데이터 파싱
```
LogParser 클래스는 로그 데이터를 대괄호로 분할하고 필요한 정보(상태 코드, URL, 웹브라우저)를 추출합니다.
split, replace등의 문자열 메서드와 인덱싱을 통해 분석에 필요한 API Key, API Service ID, Browser를 추출합니다.
조건에 따라 상태 코드가 200인 경우에만 추출한 정보로 ParsedData 객체를 생성하고 리스트에 추가합니다.

ParsedData 클래스는 apiKey, apiServiceId, browser 필드를 가지며,
각 필드 값에 접근하기 위한 getter 메서드를 생성합니다.
```

### 3. 분석 결과 생성
```
LogAnalyzer 클래스는 파싱된 데이터를 분석하여 통계를 계산합니다.
getOrDefault메서드를 활용해 API 키, 서비스 ID, 브라우저 별 사용 횟수를 계산하고 HashMap에 저장합니다.

- 최다 호출 API Key (mostCalledApiKey)
EntrySet Stream을 생성합니다.
ComparingValue를 사용해 value를 기준으로 최대값을 판별하고, key값과 매핑합니다.

- 상위 3개 API Service ID (topApiServiceIds)
EntrySet Stream을 생성합니다.
ComparingValue를 사용해 value를 기준으로 내림차순 정렬을 시행하고, 상위 3개의 값만 추출합니다.

- 웹 브라우저 별 사용 비율 (sortedBrowserRatios)
Map에서 value만 꺼내와서 Stream을 생성하고, sum으로 전체 호출 횟수를 구합니다.
EntrySet Stream을 생성합니다.
key를 매핑해서 그에 대한 백분위값("~%")을 생성하고 Map으로 Stream을 수집합니다.
위의 Stream을 Entryset으로 가져와서 내림차순 정렬을 시행합니다.

각 분석 결과 정보로 AnalyzedData 객체를 생성합니다.

ParsedData 클래스는 mostCalledApiKey, topApiServiceIds, browserRatios 필드를 가지며,
각 필드 값에 접근하기 위한 getter 메서드를 생성합니다.
```

### 4. 파일 기록
```
LogWriter 클래스는 로그 파일에서 각 줄을 읽어와 리스트로 반환합니다.
BufferedWriter를 사용하여 getter로 불러온 분석 결과를 파일에 입력합니다.

마지막으로 Main 클래스에서 로그 파일 읽기, 데이터 파싱, 결과 분석, 분석 결과 출력의 전체 프로세스를 수행합니다.
```

## 코드 개선 사항
- Data 클래스를 캡슐화, 추상화하여 구현을 숨기고 의존성을 줄였습니다.
- Reader, Writer, Parser, Analyzer 클래스를 기능에 따라 모듈화하여 재사용성과 확장성을 높였고, 테스트 효율성을 높였습니다.