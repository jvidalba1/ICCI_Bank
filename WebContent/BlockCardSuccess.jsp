<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="WEB-INF/ext.css">
<title>Insert title here</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:panelGrid >
<h1 align="center" style="text-align:center">Cards Blocked</h1>
<h:outputText value="Card with number: #{blockCardMB.cardPersisted} successfully blocked. BlockId: #{blockCardMB.blockId}"></h:outputText>
</h:panelGrid>
<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
</center>
</f:view>
</body>
</html>