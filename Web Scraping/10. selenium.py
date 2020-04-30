from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as ec
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup
import time
import urllib.request as req
# 이미지 바이트 처리
from io import BytesIO

# 엑셀 처리 임포트
import xlsxwriter

chrome_options = Options()
chrome_options.add_argument("--headless")

browser = webdriver.Chrome('./webdriver/chrome/chromedriver.exe')

browser.implicitly_wait(5)

browser.fullscreen_window()

browser.get('http://prod.danawa.com/list/?cate=112758&15main_11_02')

WebDriverWait(browser, 3).until(
    ec.presence_of_element_located((By.CSS_SELECTOR, '#dlMaker_simple > dd > div.spec_opt_view > button.btn_spec_view.btn_view_more'))).click()

WebDriverWait(browser, 3).until(
    ec.presence_of_element_located((By.CSS_SELECTOR, '#searchMaker702'))).click()

workbook = xlsxwriter.Workbook("C:/crawling_result.xlsx")
workbook = workbook.add_worksheet()

cur_page_num = 1

crawl_page = 10

while cur_page_num < crawl_page + 1:
    soup = BeautifulSoup(browser.page_source, 'html.parser')

    pro_list = soup.select('div.main_prodlist > ul > li')
    print(cur_page_num)

    for elm in pro_list:
        if not elm.find('div', class_='ad_header'):
            print(elm.select('p.prod_name > a')[0].text.strip())
            print(elm.select('p.price_sect > a')[0].text.strip())

    cur_page_num += 1

    WebDriverWait(browser, 3).until(
        ec.presence_of_element_located((By.CSS_SELECTOR, f'div.number_wrap > a:nth-child({cur_page_num})'))
    ).click()

    del soup
    time.sleep(2)
browser.quit()