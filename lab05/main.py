from flask import Flask, request, render_template, url_for, jsonify
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
        sql = "select * from administrator_info where admin_id = \"" + username + "\" and admin_password = \"" + pwd + "\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            return render_template('admin.html')

    elif roles == 'Teacher':  # 数据库查询验证id和密码
        sql = "select * from teacher_info where teacher_id = \"" + username + "\" and teacher_password = \"" + pwd + "\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            return render_template('teacher.html')

    elif roles == 'Student':  # 数据库查询验证id和密码
        sql = "select * from teacher_info where student_id = \""+ username +"\" and student_password = \"" + pwd +"\""
        result = db.select(sql)
        if (len(result) == 0):
            return render_template('login.html', message='your username or password is error, please login again!')
        else:
            return render_template('student.html')

    elif roles == 'default':
        return render_template('login.html', message='Please select a role')

# 管理员
@app.route('/manager/stu_info', methods=['GET'])
def student_info_get():
    if request.method == 'GET':
        db = Link_db()
        sql = "SELECT * from student_info"
        result = db.select(sql)
        print(result)
        data = {'id': result[0][0], 'password': result[0][1], 'name': result[0][2], 'num': result[0][3], 'privilege': result[0][4]}

        return jsonify(data)

@app.route('/manager/tea_info', methods=['POST'])
def tea_info_get():
    if request.method == 'POST':
        db = Link_db()
        sql = "SELECT * from teacher_info"
        result = db.select(sql)
        data = {'id': result[0][0], 'password': result[1][0], 'name': result[2][0], 'num': result[3][0],
                'privilege': result[4][0]}

        return jsonify(data)

if __name__ == '__main__':
    app.debug = True
    app.run()
