<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" style="text/css" href="ext.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
 <center>
	 <h:form id="form1">
		<h:panelGrid id="cardGrid" border="1" columns="1" >
			<f:facet name="header">
				<h:outputLabel value="Successful Payment" style="text-align: center"></h:outputLabel>
			</f:facet>
			<h:outputText value="#{paymentMB.message}"></h:outputText>
		</h:panelGrid>
		<br><br>
		<h:outputLink value="CRHomePage.jsp">CRHomePage</h:outputLink>
	 </h:form>
</center>
</f:view>
</body>
</html>