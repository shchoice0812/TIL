from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.ui import WebDriverWait
import time
from bs4 import BeautifulSoup
import urllib.request as req
from io import BytesIO
import xlsxwriter

broser = ""

def main():
    ChromeWebdriverOnOFFMode("on")
    ChromeDriverStart()

# Chrome Webdriver window showing(on)/hidden(off) setting
def ChromeWebdriverOnOFFMode(switch):
    chrome_options = Options()
    global browser
    if switch == "on":
        browser = webdriver.Chrome('./webdriver/chrome/chromedriver.exe', options=None)
    elif switch == "off":
        chrome_options = Options()
        chrome_options.add_argument("--headless")
        browser = webdriver.Chrome('./webdriver/chrome/chromedriver.exe', options=chrome_options)
    else:
        raise Exception("You must input the keywords: on/off")

def ChromeDriverStart():
    browser = webdriver.Chrome('./webdriver/chrome/chromedriver.exe')
    browser.implicitly_wait(5)
    browser.set_window_size(1920, 1080)

if __name__ == "__main__":
    main()