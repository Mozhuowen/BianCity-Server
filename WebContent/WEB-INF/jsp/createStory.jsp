<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <META content="text/html; charset=utf-8" http-equiv="Content-Type">
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
		  <script src="http://123.57.132.31:6060/spark-md5.min.js"></script>
        <script src="http://123.57.132.31:6060/async.js"></script>
        <script src="http://123.57.132.31:6060/upyun-mu.js"></script>      
    </head>
    <body>
    		<div class="row">
            <div class="col-md-4 col-md-offset-4">
                <img id="upimg" class="img-rounded" src=""/>
            </div>
        </div>
        <!--图片上传-->
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form action="">
                    <input type="file" name="file" id="file">
                    <a id="submit" class="btn btn-primary btn-center">UPLOAD</a>
                </form>
            </div>
        </div>
        <!--内容填写-->
        <div class="row">
            <div class="col-md-2 col-md-offset-4">
                <form id="submitform" action="createStory" method="post">
                    <div class="form-group">
                        <label>用户ID</label>
                        <input type="text" name="userid" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>边城ID</label>
                        <input type="text" name="townid" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>标题</label>
                        <input type="text" name="title" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>内容</label>
                        <input type="text" name="content" class="form-control" placeholder="Text input">
                    </div>
                    <input type="hidden" name="covername"/>
                      <button type="submit" class="btn btn-default">提交创建</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-4">
            		${hint}
            </div>
        </div>
        <!--JS-->
        <script>
            document.getElementById('submit').onclick = function() {
                var ext = '.' + document.getElementById('file').files[0].name.split('.').pop();

                var config = {
                    bucket: 'xiaocheng',
                    expiration: parseInt((new Date().getTime() + 3600000) / 1000),

                    // 尽量不要使用直接传表单 API 的方式，以防泄露造成安全隐患
                    form_api_secret: '8giBNZVp2lo8f9c7gf6Q8Wk8BQw='
                };

                var instance = new Sand(config);
                var options = {
                    'notify_url': 'http://upyun.com'
                };

                instance.setOptions(options);
                instance.upload('/test' + parseInt((new Date().getTime() + 3600000) / 1000) + ext);
            };


            // demo stuff
            function addLog(data) {
                var elem = document.createElement("ul");
                for (var key in data) {
                    if(key === 'path') {
                        elem.innerHTML += '<li><strong>' + key + ':</strong>' + '<a target="_blank"  href="http://xiaocheng.b0.upaiyun.com' + data[key] + '">' + data[key] + '</a>' +                           '</li>';
                    } else {
                        elem.innerHTML += '<li><strong>' + key + ':</strong>' + data[key] + '</li>';
                    }

                }
                log.appendChild(elem);
            }

                document.addEventListener('uploaded', function(e) {
                    var img = document.getElementById("upimg");
                    var formx = document.getElementById("submitform").elements["covername"];
                    var data = e.detail;
                    for (var key in data) {
                        if(key === 'path') {
                            var imgname = ''+data[key].substring(1);
                            var imgpath = 'http://xiaocheng.b0.upaiyun.com/' + imgname + '!small';
                            img.src = imgpath;
                            formx.value = imgname;
                        } else {
//                            alert(key + ' '+ data[key]);
                        }
                    }
                });
            </script>
    </body>
</html>