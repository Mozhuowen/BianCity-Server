<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %><%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <META content="text/html; charset=utf-8" http-equiv="Content-Type">
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>        
    </head>
    <body>
    <form action="checkTown" method="post">
    <!-- 一行开始 -->
    <s:iterator value="townlist" id="tt">
        <div class="row">
            <div class="col-md-2">
                 <a href="#" class="thumbnail">
                    <img class="img-rounded " src="http://xiaocheng.b0.upaiyun.com/${tt.cover}!small"/>
                </a>
            </div>
            <div class="col-md-9 center">
                <ul class="list-group">
                  <li class="list-group-item">${tt.name}</li>
                  <li class="list-group-item">${tt.geo.province}${tt.geo.city}</li>
                  <li class="list-group-item">${tt.descri}</li>
                </ul>
            </div>
            <div class="col-md-1">
                <div class="checkbox">
                    <label>
                      <input type="checkbox" name="delid" value="${tt.townid}"> 选择
                    </label>
                </div>
        </div>
        </div><!--一行结束-->
     </s:iterator>
     <div class="row">
            <div class="col-md-2 col-md-offset-2">
                <button class="btn btn-primary btn-center" type="submit">屏蔽所选</button>
            </div>
        </div>
     </form><!-- form结束 -->
       
        <!--分页-->
        <div class="row">
            <div class="col-md-10 col-md-offset-2">
                <nav>
                  <ul class="pagination">
                    <li>
                    <s:if test="currPage == 0">
	                      <a href="checkTown?currPage=0" aria-label="Previous">
	                        <span aria-hidden="true">&laquo;</span>
	                      </a>
                      </s:if>
                      <s:else>
                      	<a href="checkTown?currPage=${currPage-1}" aria-label="Previous">
	                        <span aria-hidden="true">&laquo;</span>
	                      </a>
                      </s:else>
                    </li>
                   		 <!-- 分页迭代 -->
                    <s:iterator value="pagelist" id="pp">
                    <s:if test="(#pp-1)==currPage">
                   	 <li class="active">
                   	</s:if>
                   	<s:else>
                   		<li>
                   	</s:else>
                   	 <a href="checkTown?currPage=${pp-1}">${pp}</a></li>
                    </s:iterator>
                    <li>
                      <a href="checkTown?currPage=${currPage+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                  </ul>
                </nav>
            </div>
        </div>
    </body>
</html>