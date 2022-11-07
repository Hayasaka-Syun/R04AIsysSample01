<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SentimentResult</title>
</head>
<%
	float message = 
		(float) request.getAttribute("message");

	float message2 = 
		(float) request.getAttribute("message2");
		
	float message3 = 
		(float) request.getAttribute("message3");
	
	Optional<String> string = 
		Optional.ofNullable((String) request.getAttribute("string"));

%>

<body>
<H1>Sentiment</H1>
<H3>文章：<%= string.orElse("ERROR") %></H3>
<H3>negative =<%= message %></H3>
<H3>neutral =<%= message2 %></H3>
<H3>positive =<%= message3%></H3>
</body>
</html>