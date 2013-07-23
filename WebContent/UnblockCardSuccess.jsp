<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" style="text/css" href="ext.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unblock Card Success</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
	<h:form id="form2">
		<center>
			<h:outputText styleClass="header" value="#{unblockCardMB.message}"></h:outputText>
			<br><br>
			<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
		</center>
	</h:form>
</f:view>
</body>
</html>