"""
    해당 코드는 웹에서의 임의의 이미지에 접근하고 로컬 PC에 저장을 하기위한 코드입니다.
    urllib 모듈의 urlopen 함수를 사용하였습니다.
    
    상세내용 참조 : https://velog.io/@shchoice/urllib.request-urlretrieve-urlopen
"""

from urllib.request import urlopen
from urllib.error import HTTPError, URLError

def urlopen_download(url_list, path, filename):
    for i, url in enumerate(url_list):
        try:
            # response 정보 읽기
            response = urlopen(url)
            contents = response.read()

            # 성공 시 response 상태정보 확인
            print("------------------------------------------")
            print(f"{i+1}번째 Header Info :")
            print(response.info())
            print("------------------------------------------")
            print(f"HTTP Status Code :{response.getcode()}")
            print()

            # 파일 저장
            file_name = path + filename + str(i+1) + ".jpg"
            with open(file_name, 'wb') as f:
                f.write(contents)
                print("File Download Complete!")
                print(f"{i+1}번째 file dowload path: {file_name}")
        
        # HTTP Error 발생 시 예외처리 
        except HTTPError as e:
            print("\n------------------------------------------")
            print(f"{i+1}번째 이미지")
            print(e)
            print('HTTPError Code : ', e.code)

        # URL Error 발생 시 예외처리 
        except URLError as e:
            print("\n------------------------------------------")
            print(f"{i+1}번째 이미지")
            print("The Server Could not be found!")
            print(e)
            print('URL Error Reason : ', e.reason)
         
        else:
            print("It Worked!\n")


# 이미지 주소 복사
image_url01 = "https://i.ytimg.com/vi/g1gKPb3z3do/maxresdefault.jpg"
image_url02 = "https://s3-ap-northeast-2.amazonaws.com/mobiinsidecontent/kr/wp-content/uploads/2019/11/01162420/5.jpeg"
## 잘못된 URL 경로를 통한 예외처리 확인(HTTP ERROR)
wrong_url01 = "https://s3-ap-northeast-2.amazonaws.com/mobiinsidecontent/kr/wp-content/uploads/2019/11/01162420/5/worng_url.jpeg"
## 잘못된 URL 경로를 통한 예외처리 확인(URL ERROR)
wrong_url02 = "https://s3-ap-northeast-2.amazona_worng_url.com/mobiinsidecontent/kr/wp-content/uploads/2019/11/01162420/5.jpeg"

url_list = [image_url01, image_url02, wrong_url01, wrong_url02]

# 저장 파일 경로
path = "C:\\Temp\\"

# 저장 파일 이름
filename = "펭수 이미지"

# urlretrieve를 통한 이미지 다운로드
urlopen_download(url_list, path, filename)