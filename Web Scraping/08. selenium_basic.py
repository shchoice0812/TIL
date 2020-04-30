from selenium import webdriver

# webdriver 설정
browser = webdriver.Chrome('./webdriver/chrome/chromedriver.exe')

# 브라우저 엔진에서 파싱되는 시간 대기
browser.implicitly_wait(5)

# 브라우저 사이즈 조정
browser.maximize_window() # set_window_size(1920, 1280)

# 페이지 이동
browser.get('https://www.naver.com')

# 검색창 input 선택
element = browser.find_element_by_css_selector('span.green_window > input#query')

# 검색어 입력
element.send_keys("펭수")

# 검색(Form Submit)
element.submit()

# 페이지 내용
print("Page Contents :" ,browser.page_source)

# 세션 값 출력
print("Session ID :", browser.session_id)

# 타이틀 출력
print("Title :",format(browser.title))

# 현재 URL
print("URL :", browser.current_url)

# 쿠키 정보 확인
print("Cookies :", browser.get_cookies())


# 브라우저 종료
browser.quit()
