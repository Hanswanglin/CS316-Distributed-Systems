from flask import Flask, request, render_template, url_for, jsonify, make_response
from Link_db import Link_db

app = Flask(__name__, static_url_path='/static')

# 主页定向到登录页
@app.route('/', methods=['POST', 'GET'])
def index():
    return render_template('login.html')

# 登录页
@app.route('/login/', methods=['POST'])  # 只接受POST请求
def login():
    if request.method == 'GET':
        return render_template('login.html')
    try:
        roles = request.form['roles']
    except:
        roles = 'default'  # 没有选着角色
    username = request.form['username']
    pwd = request.form['password']

    db = Link_db()
    if roles == 'Admin':  # 数据库查询验证id和密码
        sql = "select * from administrator_info where admin_id = \"" + username + "\" and admin_password = \"" + \
              pwd + "\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            admin_info = admin_info_get()
            return admin_info

    elif roles == 'Teacher':  # 数据库查询验证id和密码
        sql = "select * from teacher_info where teacher_id = \"" + username + "\" and teacher_password = \"" + \
              pwd + "\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            return render_template('teacher.html')

    elif roles == 'Student':  # 数据库查询验证id和密码
        sql = "select * from teacher_info where student_id = \""+ username +"\" and student_password = \"" + \
              pwd +"\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            return render_template('student.html')

    elif roles == 'default':
        return render_template('login.html', message='Please select a role')

# 管理员
@app.route('/admin', methods=['POST', 'GET'])
def admin_info_get():
    db = Link_db()
    sql1 = "SELECT * from student_info"
    result1 = db.select(sql1)
    student_info = {'id': result1[0][0], 'password': result1[0][1], 'name': result1[0][2], 'num': result1[0][3],
                    'privilege': result1[0][4]}

    sql2 = "SELECT * from teacher_info"
    result2 = db.select(sql2)
    teacher_info = {'id': result2[0][0], 'password': result2[0][1], 'name': result2[0][2], 'num': result2[0][3],
                    'privilege': result2[0][4]}

    admin_info = make_response(render_template('admin.html'), teacher_info = teacher_info, student_info = student_info)
    return admin_info

if __name__ == '__main__':
    app.debug = True
    app.run()
