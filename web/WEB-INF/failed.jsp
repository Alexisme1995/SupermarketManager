<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作失败</title>
<link href="CSS/word.css" rel="stylesheet" type="text/css" />
<script>
	var i = 5;
	function toUrl() {
		i--;
		document.getElementById("second").innerHTML =  i ;
		if (i == 0) {
			window.location = "login.jsp"; //跳转的页面
		} else if (i < 1) {
			i = 0;
		}
	}
	setInterval(toUrl, 1000);
</script>
</head>

<body>
	<div>
		<div
			style="margin-left: auto; margin-right: auto; margin-top: 200px; width: 660px; height: 200px; border: #F3F3F3 3px solid">
			<div style="margin-top: 45px; margin-left: 50px">
				<div style="float: left">
					<div style="clear: left"></div>
					<div>
						<div style="float: left">
							<font class="normalBlack">对不起，<s:property value="errorTitle" /> ，<s:property value="errorContext" /> ，该页面将在
							</font>
						</div>
						<div style="float: left" id="second" name="second">
							<font class="normalBlack">5</font>
						</div>
						<div style="float: left">
							<font class="normalBlack">秒后转入登录前的页面</font>
						</div>
						<div style="clear: left"></div>
					</div>
					<div style="margin-top: 10px;">
						<a href="login.jsp" class="aBlueWord" style="font-size: 15px;">如果您的浏览器没有自动跳转，请点此链接</a>
					</div>
				</div>
				<div style="clear: left"></div>
			</div>
		</div>
	</div>
</body>
</html>
