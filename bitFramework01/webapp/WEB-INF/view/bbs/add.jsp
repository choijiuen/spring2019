<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>�Է�������</h1>
	<form action="insert.bit">
		<div>
			<label for="sub">����</label>
			<input type="text" name="sub" id="sub"/>
		</div>
		
		<div>
			<label for="name">�۾���</label>
			<input type="text" name="name" id="name"/>
		</div>
		
		<div>
			<textarea rows="" cols="" name="content"></textarea>
		</div>
		
		<div>
			<button type="submit">�Է�</button>
			<button type="reset">���</button>
			<button type="button" onclick="history.back();">�ڷ�</button>
		</div>
	</form>
</body>
</html>