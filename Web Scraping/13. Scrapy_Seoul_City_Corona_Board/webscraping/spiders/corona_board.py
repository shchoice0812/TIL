# -*- coding: utf-8 -*-
import scrapy
from ..items import BoardItem
import itertools


class CoronaBoardSpider(scrapy.Spider):
    name = 'corona_board'
    start_urls = ['http://www.seoul.go.kr/coronaV/coronaStatus.do?tab=1']

    # url 분리
    def parse(self, response):
        """
        # 서울 코로나 감염현황 정보 추출
            작성자 : shchoi
            날짜 : 20.03.13

            추출 페이지 : http://www.seoul.go.kr/coronaV/coronaStatus.do?tab=1
            ## 추출 Field(div.status-seoul)
                - '확진자': corona_confirmed,
                - '퇴원': recovered,
                - '사망': death,
                - '의심환자': patient_suspect,
                - '검사중': under_examination,
                - '결과음성': negative,
                - '자가격리자" : self_isolation
                - '감시중' : monitoring
                - '감시해제' : monitoring_release
        """

        board = response.css('div.status-seoul')
        
        corona_confirmed = board.css('div.cell-group.first-cell > div.cell.cell1 > div > p.counter::text').extract_first()
        recovered = board.css('div.cell-group.first-cell > div.cell.cell5 > div > p.counter::text').extract_first()
        death = board.css('div.cell-group.first-cell > div.cell.cell6 > div > p.counter::text').extract_first()
        patient_suspect = board.css('div.cell-group.second-cell > div.cell.cell2 > div.num.num2 > p.counter::text').extract_first()
        under_examination = board.css('div.cell-group.second-cell > div.cell.cell2 > div.num.num3 > p.counter::text').extract_first()
        negative = board.css('div.cell-group.second-cell > div.cell.cell2 > div.num.num4 > p.counter::text').extract_first()
        self_isolation = board.css('div.cell-group.second-cell > div.cell.cell3 > div.num.num5 > p.counter::text').extract_first()
        monitoring = board.css('div.cell-group.second-cell > div.cell.cell3 > div.num.num6 > p.counter::text').extract_first()
        monitoring_release = board.css('div.cell-group.second-cell > div.cell.cell3 > div.num.num7 > p.counter::text').extract_first()

        board_item = BoardItem()
        board_item['corona_confirmed'] = corona_confirmed
        board_item['recovered'] = recovered
        board_item['death'] = death
        board_item['patient_suspect'] = patient_suspect
        board_item['under_examination'] = under_examination
        board_item['negative'] = negative
        board_item['self_isolation'] = self_isolation
        board_item['monitoring'] = monitoring
        board_item['monitoring_release'] = monitoring_release

        yield board_item