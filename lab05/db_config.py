#!/usr/bin/env python
#encoding:utf8

import os
import sqlite3



# 创建数据库文件
db_file = os.path.join(os.path.dirname(__file__), 'test.db')
if os.path.isfile(db_file):
    os.remove(db_file)

# 初始化数据
conn = sqlite3.connect(db_file)

