<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Customer Home Page</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:form id="form1">
<h:panelGrid border="1" columns="1" >
		<f:facet name="header">
			<h:outputText value="Welcome " style="text-align: center"></h:outputText>
			<h:outputText value="#{customerMB.name}"></h:outputText>
		</f:facet>
		<h:outputLink value="ViewCardDetails.jsp">View Card details</h:outputLink>
		<h:outputLink value="ViewCardUsage.jsp">View Card Usage</h:outputLink>
		<h:outputLink value="On-LineTransaction.jsp">Online Transaction</h:outputLink>
	</h:panelGrid>
	</h:form>
</center>
</f:view>
</body>
</html>