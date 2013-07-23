<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Scheme Successfully</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
	<center>
		<h:form id="form1">
			<h:panelGrid border="1">
				<f:facet name="header">
					<h:outputText value="Success Page" style="text-align: center; font-size: 150%;"></h:outputText>
				</f:facet>
				<h:outputText value="Scheme Successfully chosen"></h:outputText>
			</h:panelGrid><br>
			<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
		</h:form>
	</center>
</f:view>
</body>
</html>