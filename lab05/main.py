from flask import Flask, request, render_template, url_for

app = Flask(__name__, static_url_path='/static')

# 主页定向到登录页
@app.route('/', methods=['POST', 'GET'])
def index():
    return render_template('login.html')

# 登录页
@app.route('/login/', methods=['POST', 'GET'])  # 只接受POST请求
def login():
    if request.method == 'GET':
        return render_template('login.html')
    try:
        roles = request.form['roles']
    except:
        roles = 'default'
    username = request.form['username']
    pwd = request.form['password']

    if roles == 'Admin':  # 数据库查询验证id和密码
        return render_template('admin.html')
    elif roles == 'Teacher':  # 数据库查询验证id和密码
        return render_template('teacher.html')
    elif roles == 'Student':  # 数据库查询验证id和密码
        return render_template('student.html')
    elif roles == 'default':
        return render_template('login.html', message='Please select a role')


if __name__ == '__main__':
    app.debug = True
    app.run()
