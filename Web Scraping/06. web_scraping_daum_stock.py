'''
    해당 코드는 다음 주식 웹페이지 일부 내용(종목 변화)을 웹 스크레이핑 하기 위한 코드입니다.
    웹 스크래이핑 시 좀 더 신경썼던 부분은
    1. 스크래이핑 부분이 Ajax로 구성되어 있어서 api를 구성하는 별도의 url을 찾아야 하는 것
    2. 다음에서 ajax로 구성된 url을 호출 시 단순 접속을 막아 놓았기 때문에
       별도의 처리(헤더 정보(User-Agent, referer) 선언)가 필요했다는 점입니다.

    상세내용 참조 : https://velog.io/@shchoice/Ajax-%EC%A0%81%EC%9A%A9%EB%90%9C-%EC%9B%B9-%ED%8E%98%EC%9D%B4%EC%A7%80-%EC%8A%A4%ED%81%AC%EB%9E%98%EC%9D%B4%ED%95%91Daum-%EC%A3%BC%EC%8B%9D
'''

from urllib.request import urlopen, Request
from fake_useragent import UserAgent
import json

# fake_useragent 모듈을 통한 User-Agent 정보 생성
useragent = UserAgent()
# print(useragent.chrome)
# print(useragent.ie)
# print(useragent.safari)
# print(useragent.random)

# 헤더 선언 및 referer, User-Agent 전송
headers = {
    'referer' : 'https://finance.daum.net/domestic/sectors',
    'User-Agent' : useragent.chrome
}

# 주식 데이터 요청 URL
url = 'https://finance.daum.net/api/sectors/?includedStockLimit=2&page=1&perPage=30&fieldName=changeRate&order=desc&market=KOSPI&change=RISE&includeStocks=true&pagination=true'

# 주식 데이터 요청
response = urlopen(Request(url, headers=headers)).read().decode('utf-8')

# 응답 데이터 str 타입을 json 포맷으로 변환 및 data 저장
rank_json = json.loads(response)['data']
# print(rank_json)

print(f"날짜 : {rank_json[0]['date']}, 마켓: {rank_json[0]['market']}")
for rank in rank_json:
    print(f"종목 : {rank['sectorName']}, 상승/하강: {rank['change']}, 등락율 : {round(rank['changeRate']*100, 3)}")