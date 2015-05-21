<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <META content="text/html; charset=utf-8" http-equiv="Content-Type">
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>        
    </head>
    <body>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
               <form action="tologin" method="post">
                    <div class="form-group">
                        <label>username</label>
                        <input name="username" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>password</label>
                        <input name="password" type="password" class="form-control">
                    </div>
                      <button type="submit" class="btn btn-default">登录</button>
                </form>
            </div>
        </div>
    </body>
</html>