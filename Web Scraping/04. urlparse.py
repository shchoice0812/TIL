"""
    해당 코드는 https://api.ipify.org (REST API를 호출 public IP 주소 반환 웹 서비스) 웹 페이지를 통해
    urllib 모듈의 urlpare와 urlencode에 대한 이해를 위해 작성하였습니다.
    
    상세내용 참조 : https://velog.io/@shchoice/urllib.request-urlretrieve-urlopen
"""

from urllib.request import urlopen
from urllib.parse import urlparse, urlencode

# 1. urllib.parse.urlparse를 통한 구문분석
print("------------------------------------------")
print(f"parse : {urlparse('https://api.ipify.org?id=honggildong')}")
print("------------------------------------------")


# 2. Get 방식으로 통신을 하고 응답을 받는 기본 원리 코드
# Get 방식 parameter 생성 및 urlencode를 통한 파라미터 인코딩
values = {
    'format': 'json'
}
print(f'Before urlencode : {values}')
params = urlencode(values)
print(f"After urlencode : {params}")
print("------------------------------------------")

# 요청 URL 생성
API = "https://api.ipify.org" ## REST API를 호출 시 어떠한 public IP 주소를 사용하는지 확인 가능한 웹 서비스
url = API + "?" + params
print("요청 URL : ", url)

# 응답(읽은) 데이터를 파이썬 실행환경의 메모리에 저장
response = urlopen(url)
data = response.read()

print("Before decode response :", data)
text = data.decode("utf-8")
print("After decode response :", text)