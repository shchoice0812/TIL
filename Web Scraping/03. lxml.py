"""
    네이버 웹툰의 요일별 웹툰 가져오는 웹 스크래이핑을 위한 코드입니다.
    해당 코드에서 하고자했던 것은 다음과 같습니다.
    1. lxml의 기본적인 사용법,
    2. xpath select의 기본적인 사용법을 통한 웹 스크래이핑입니다.

    상세내용 참조 : https://velog.io/@shchoice/lxml%EC%9D%84-%ED%86%B5%ED%95%9C-%EC%9B%B9-%EC%8A%A4%ED%81%AC%EB%A0%88%EC%9D%B4%ED%95%91
"""


import requests
import lxml.html

def main():
    # 스크래이핑 대상 URL
    response = requests.get("https://comic.naver.com/webtoon/weekday.nhn")
    
    # 요일별 웹툰 리스트 할당 
    webtoon = scrape_webtoon(response)
    
    week = ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"]
    for i in range(len(webtoon)):
        print(week[i], "요일별 웹툰 : ", webtoon[i],"\n")


def scrape_webtoon(response):
    # 태그 정보 문자열 저장
    root = lxml.html.fromstring(response.content)
    
    Mon=[]
    Tue=[]
    Wed=[]
    Thu=[]
    Fri=[]
    Sat=[]
    Sun=[]
    
    # a 태그 href 요소에 요일구분 하는 값 존재로 파싱 
    webtoon_name = root.xpath(f'.//div[@class="col_inner"]/ul/li/a')
    for i in range(len(webtoon_name)):
        href = webtoon_name[i].get('href')
        if href[len(href)-3:len(href)] == 'mon':
            Mon.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'tue':
            Tue.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'wed':
            Wed.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'thu':
            Thu.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'fri':
            Fri.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'sat':
            Sat.append(webtoon_name[i].text_content())
        elif href[len(href)-3:len(href)] == 'sun':
            Sun.append(webtoon_name[i].text_content())
        else:
            print("There are some problems for scraping")

    weekend = [Mon, Tue, Wed, Thu, Fri, Sat, Sun]

    return weekend


if __name__ == "__main__":
    main()