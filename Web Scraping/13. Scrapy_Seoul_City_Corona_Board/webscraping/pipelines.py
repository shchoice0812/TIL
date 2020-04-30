# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

from scrapy.exceptions import DropItem
import json
import pprint

class WebscrapingPipeline(object):
    def process_item(self, item, spider):
        return item

class CoronaPatientInfoPipeline(object):
    def __init__(self):
        self.is_move_line_dup = set()
        self.is_patient_id_dup = set()   

    def process_item(self, item, spider):
        if item['patient_id'] in self.is_patient_id_dup and item['move_line'] in self.is_move_line_dup:
            raise DropItem("Duplicate item found %s" % item)
        else:
            self.is_patient_id_dup.add(item['patient_id'])
            self.is_move_line_dup.add(item['move_line'])
            return item
    