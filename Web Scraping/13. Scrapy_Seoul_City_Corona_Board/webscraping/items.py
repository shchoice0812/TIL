# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy

class PatientItem(scrapy.Item):
    serial_id = scrapy.Field()
    patient_id = scrapy.Field()
    patient_info = scrapy.Field()
    infection_route = scrapy.Field()
    confirmation_date = scrapy.Field()
    residence = scrapy.Field()
    is_recovered = scrapy.Field()
    containment_facilities = scrapy.Field()
    move_line = scrapy.Field()

class BoardItem(scrapy.Item):
    board = scrapy.Field()
    corona_confirmed = scrapy.Field()
    recovered = scrapy.Field()
    death = scrapy.Field()
    patient_suspect = scrapy.Field()
    under_examination = scrapy.Field()
    negative = scrapy.Field()
    self_isolation = scrapy.Field()
    monitoring = scrapy.Field()
    monitoring_release = scrapy.Field()