import requests
import json

"""
    해당 코드에서는 requests 모듈에 대한 이해를 위한 코드입니다.
    1. session 연결 및 response data 출력
    2. cookie 정보 전송 및 응답 춮력
    3. header 정보 전송
    4. json 데이터 요청 및 데이터 출력
    5. REST API() 기본적인 CRUD 사용법
    6. request timeout 설정
"""

# requests Session을 사용할 때는 session 비활성화(sess.close) 실수를
# 막기위해 with문으로 작성하는 것이 보다 현명하다.
with requests.Session() as sess:
    # 1. 기본적인 requests 기능
    url = 'http://httpbin.org'
    res = sess.get(url)
    # 수신 상태 체크, HTTPError 발생 시 종료
    res.raise_for_status() 
    print(res.text)
    print(res.status_code)
    print(res.ok)


    # 2-1 . 쿠키 return 
    url = 'http://httpbin.org/cookies'
    res = sess.get(url, cookies={'UserId': 'shchoice'})
    print(res.text)

    # 2-2. 쿠키 set
    url = 'http://httpbin.org/cookies/set'
    res = sess.get(url, cookies={'username' : 'shchoice'})
    print(res.text)

    # 2-3 보다 형식적인 쿠키 설정
    # 쿠키 설정
    jar = requests.cookies.RequestsCookieJar()
    # 쿠키 삽입
    jar.set('name', 'honggildong', domain='httpbin.org', path='/cookies')
    # 요청
    url = 'http://httpbin.org/cookies'
    res = sess.get(url, cookies=jar)
    print(res.text)


    # 3. header 정보 전송
    headers ={
        'referer': 'https://www.google.com',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36'
    }
    url = 'http://httpbin.org/get'
    res = sess.get(url, headers=headers)
    print(res.text)
    

    # 4. json 데이터 요청
    ## 5개의 데이터를 json으로 읽어올 경우 처리
    url = 'http://httpbin.org/stream/5'
    res = sess.get(url, stream=True)
    print("encoding 이전 포맷 :", res.encoding)
    # 한글 정보 등 깨짐 방지
    if res.encoding is None:
        res.encoding = 'utf-8'
    print("encoding 이후 포맷 :", res.encoding)

    print("헤더 정보 :", res.headers)
    print("본문 정보 :", res.text)

    # 데이터 출력
    for line in res.iter_lines(decode_unicode=True):
        print(line)
        print(type(line)) # str 타입으로 결과 반환
        # str 타입을 json형식으로 맞추기 위해 dictionay 자료형으로 변환
        dic = json.loads(line)
        print("str -> dictionart 자료형 변경 확인 :", type(dic))
        
        for key, value in dic.items():
            print(f"key : {key}, value : {value}")
    
    ## 1개의 데이터를 갖은 json으로 읽을 경우
    res = sess.get('http://httpbin.org/stream/1')
    if res.encoding is None:
        res.encoding = 'utf-8'
    print("헤더 정보: ", res.headers)
    print("본문 정보: ", res.text)
    print("타입: ", type(res))
    print("타입: ", type(res.encoding))
    print("Json 변환: ", res.json())
    print("Key: ", res.json().keys())
    print("Value: ", res.json().values())
    print("인코딩 정보: ", res.encoding)
    print("바이너리 정보: ", res.content)
    

    # 5. REST API() 기본적인 CRUD 사용법
    ## Read인 GET은 계속 사용해왔음으로 스킵
    ## Post
    url = 'http://httpbin.org/post'
    payload = {'userID' : 'sss', 'password' : 'password'}
    res = sess.post(url, data = payload)
    print("본문 정보: ", res.text)
    print("헤더 정보: ", res.headers)

    ## PUT
    url = 'http://httpbin.org/put'
    payload = {'userID' : 'sss', 'password' : '123456'}
    res = sess.put(url, data = payload)
    print("본문 정보: ", res.text)
    print("헤더 정보: ", res.headers)

    
    ## Delete
    url = 'http://httpbin.org/delete'
    res = sess.delete(url)
    print("본문 정보: ", res.text)
    print("헤더 정보: ", res.headers)

    # 6. request timeout 설정
    res = sess.get('https://naver.com', timeout=5)
    print(res.text)