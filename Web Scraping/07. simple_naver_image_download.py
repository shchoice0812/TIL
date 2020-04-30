'''
    해당 코드는 네이버에서 이미지를 웹 스크레이핑 해오는 코드 입니다.
'''

import os
import urllib.request as req
import urllib.parse as rep
from fake_useragent import UserAgent
from bs4 import BeautifulSoup

def main():
    SetHeaders()
    # Naver 이미지에서 이미지 다운로드
    search = "펭수"
    save_path = "C:\image_download"

    url = SetURL(search)
    imgs = Image_Search(url)
    SaveImage(imgs, save_path,  search)

# Header 정보 삽입
def SetHeaders():
    opener = req.build_opener()
    opener.addheaders = [('User-Agent', UserAgent().chrome)]
    req.install_opener(opener)

# 포털 사별 이미지 검색 URL값 초기화(set)
def SetURL(search):
    quote = rep.quote_plus(search)
    base = "https://search.naver.com/search.naver?where=image&sm=tab_jum&query="
    url = base + quote

    return url

# 포털사 별 이미지 검색
def Image_Search(url):
    html = req.urlopen(url)
    soup = BeautifulSoup(html, 'html.parser')    
    imgs = soup.select('div.img_area._item > a.thumb._thumb > img')

    return imgs

# 로컬 파일시스템 내 이미지 저장
def SaveImage(imgs, save_path, filename):
    # 폴더 유무 확인(없을 시 생성)
    try:
        if not os.path.isdir(save_path):
            os.mkdir(os.path.join(save_path))
    except OSError as e:
        print("Folder Creation Failed")
        print(f"Folder Name : {e.filename}")

        raise RuntimeError("Folder Creation Failed")
    else:
        print("Find Folder. Ready to Save")
    
    # 생성 폴더에 이미지 저장
    for i, imgs in enumerate(imgs, 1):
        fullFileName = os.path.join(save_path, filename + str(i) + '.png')
        req.urlretrieve(imgs['data-source'], fullFileName)
    print("Succesfully Saved")


if __name__ == "__main__":
    main()