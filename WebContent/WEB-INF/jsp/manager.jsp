<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <META content="text/html; charset=utf-8" http-equiv="Content-Type">
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
            
    </head>
    <body>
        <ul class="nav nav-tabs" id="myTab">
          <li role="presentation"><a id="tab1">审核边城</a></li>
          <li role="presentation"><a id="tab2">审核故事</a></li>
          <li role="presentation"><a id="tab3">创建边城</a></li>
          <li role="presentation"><a id="tab4">创建故事</a></li>
        </ul>
        <div class="embed-responsive embed-responsive-16by9">
            <iframe class="embed-responsive-item" src="welcome" id="myframe"></iframe>
        </div>
        <script>
              $('#tab1').on('click', function () {
                var bb2 = document.getElementById("myframe"); 
                bb2.src = "checkTown"; 
              })
              $('#tab2').on('click', function () {
                var bb2 = document.getElementById("myframe"); 
                bb2.src = "checkStory"; 
              })
              $('#tab3').on('click', function () {
                var bb2 = document.getElementById("myframe"); 
                bb2.src = "createTown"; 
              })
              $('#tab4').on('click', function () {
                var bb2 = document.getElementById("myframe"); 
                bb2.src = "createStory"; 
              })
        </script>
    </body>
</html>