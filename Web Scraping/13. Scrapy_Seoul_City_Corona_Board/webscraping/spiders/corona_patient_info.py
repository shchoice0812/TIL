# -*- coding: utf-8 -*-
import scrapy
from ..items import PatientItem
import itertools


class CoronaPatientInfoSpider(scrapy.Spider):
    name = 'corona_patient_info'
    start_urls = ['http://www.seoul.go.kr/coronaV/coronaStatus.do']

    # url 분리
    def parse(self, response):
        """
        # 서울 확진자 이동경로 정보 추출
            작성자 : shchoi
            날짜 : 20.03.13

            추출 페이지 : http://www.seoul.go.kr/coronaV/coronaStatus.do
            ## 추출 Field(tr.patient)
                - '연번': serial_id,
                - '환자번호': patient_id,
                - '인적사항': patient_info,
                - '감염경로': infection_route,
                - '확진일': confirmation_date,
                - '거주지': residence,
                - '완쾌여부" : is_rcovered
                - '격리시설' : containment_facilities

            ## 추출 Field(div#patients > div.cont-page-wrap > div > table > tbody > tr:nth-child(even))
                - '동선' : move_line

        """
        patients_list = response.css('tr.patient')
        patients_move_lines = response.css('div#patients > div.cont-page-wrap > div > table > tbody > tr:nth-child(even)')

        for patient_list, patient_move_lines in zip(patients_list, patients_move_lines):
            serial_id = patient_list.css('td:nth-child(1) > p:nth-child(2)::text').extract_first()
            patient_id = patient_list.css('td:nth-child(1) > p:nth-child(1)::text').extract_first()
            patient_info =patient_list.css('td:nth-child(2)::text').extract_first()
            infection_route = patient_list.css('td:nth-child(3)::text').extract_first()
            confirmation_date = patient_list.css('td:nth-child(4)::text').extract_first()
            residence = patient_list.css('td:nth-child(5)::text').extract_first()
            is_recovered = patient_list.css('td:nth-child(5) > span::text').extract_first()
            containment_facilities = patient_list.css('td:nth-child(6)::text').extract_first()
            move_line = patient_move_lines.css('td ul li').extract_first()

            patient_item = PatientItem()
            patient_item['serial_id'] = serial_id
            patient_item['patient_id'] = patient_id
            patient_item['patient_info'] = patient_info
            patient_item['infection_route'] = infection_route
            patient_item['confirmation_date'] = confirmation_date
            patient_item['residence'] = residence
            patient_item['is_recovered'] = is_recovered
            patient_item['containment_facilities'] = containment_facilities
            patient_item['move_line'] = move_line

            yield patient_item