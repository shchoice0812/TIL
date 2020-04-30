"""
    해당 코드는 웹에서의 임의의 이미지에 접근하고 로컬 PC에 저장을 하기위한 코드입니다.
    urlretrieve 함수의 이해를 위해urllib 모듈의 urlretrieve 함수를 사용하였습니다.

    상세내용 참조 : https://velog.io/@shchoice/urllib.request-urlretrieve-urlopen
"""

from urllib.request import urlretrieve

def urlretrieve_download(url_list, path, filename):
    for i, url in enumerate(url_list):
        try:
            # 해당 URL에 송신 및 response 성공 시 파일 저장 
            file_name, header = urlretrieve(url, path + filename + str(i+1) + ".jpg")

        # 잘못된 URL 입력 시 예외 처리
        except Exception as e:
            print("----------------------------------------------------")
            print(f"{i+1}번째 이미지 Download failed!")
            print(e)

        else:
            # 성공 시 response 상태정보 확인
            print("----------------------------------------------------")
            print(f"{i+1}번째 file dowload path: {file_name}")
            print("----------------------------------------------------")
            print(f"{i+1}번째 Header Info :")
            print(header)

# 이미지 주소 복사
image_url01 = "https://i.ytimg.com/vi/g1gKPb3z3do/maxresdefault.jpg"
image_url02 = "https://s3-ap-northeast-2.amazonaws.com/mobiinsidecontent/kr/wp-content/uploads/2019/11/01162420/5.jpeg"
## 잘못된 URL 경로를 통한 예외처리 확인(HTTP ERROR)
wrong_url = "https://i.ytimg.com/vi/g1gKPb3z3do/maxresdefault_worng_url.jpg"

url_list = [image_url01, image_url02, wrong_url]

# 저장 파일 경로
path = "C:\\Temp\\"

# 저장 파일 이름
filename = "펭수 이미지"

# urlretrieve를 통한 이미지 다운로드
urlretrieve_download(url_list, path, filename)