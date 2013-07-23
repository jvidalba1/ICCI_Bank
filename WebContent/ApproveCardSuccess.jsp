<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Approve Card Success</title>
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px"/>
	<h:panelGrid border="1" columns="1" rendered="#{cardApplicationMB.randomCharacter == true}">
		<center><h:outputLabel value="Card Approved"
			style="text-align:center; color:red; font-size:20px;"></h:outputLabel></center>
		<h:outputLabel value="#{cardApplicationMB.message}"
			style="color:maroon;"></h:outputLabel>
	</h:panelGrid>
	<h1><h:outputLabel value="#{cardApplicationMB.message}" rendered="#{cardApplicationMB.randomCharacter == false}"></h:outputLabel></h1>
	<h:outputLink value="CRHomePage.jsp">Home Page</h:outputLink>
	</center>
</f:view>
</body>
</html>
