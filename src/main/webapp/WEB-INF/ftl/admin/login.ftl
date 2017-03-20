<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css"/>
</head>
<body>
<div id="login">
    <h1>Login</h1>
    ${error!""}
    <form method="post" action="/admin/login">
        <input type="text" required="required" placeholder="用户名" name="name"/>
        <input type="password" required="required" placeholder="密码" name="password"/>
        <button class="but" type="submit">登录</button>
    </form>
</div>
</body>
</html>