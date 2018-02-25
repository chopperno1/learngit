<%@page pageEncoding="utf-8"%>
<img src="images/logo.png" alt="logo" class="left"/>
<!-- EL的默认取值范围:
	page,request,session,application
	EL也可以从cookie中获取数据,语法:
	cookie.参数名.value -->
<%-- <span>${cookie.adminCode.value }</span> --%>
<span>${adminCode }</span>
<a href="#">[退出]</a>














