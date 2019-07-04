### spring batch

- spring batch는 job 단위로 모든 정보와 메타데이터를 총괄한 job repository를 중심으로 작동(순차적)
- 각 job은 하나 이상의 순차적인 스텝으로 구성된다.


### job

- jobInstance : job 실행을 식별하는 instance
- jobExecution : 같은 jobInstance가 실행되는 런타임 컨텍스트
- stepExecution :초기 job에 속한 각 스텝의 jobexection에는 stepExecution이 존재
