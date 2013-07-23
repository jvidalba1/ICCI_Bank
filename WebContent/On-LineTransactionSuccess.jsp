<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>OnLine Transaction Success</title>
</head>
<body>
<f:view>
<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
		<h:form id="form1">
			<h:panelGrid border="1" columns="2" style="text-align: center">
				<f:facet name="header">
					<h:outputText value="SuccessFul Transaction"></h:outputText>
				</f:facet>
				<h:outputText value="Transaction Id"></h:outputText>
				<h:outputText value="#{transactionMB.transactionId}"></h:outputText>
				<h:outputText value="Description"></h:outputText>
				<h:outputText value="#{transactionMB.description}"></h:outputText>
				<h:outputText value="Expenditure"></h:outputText>
				<h:outputText value="#{transactionMB.amount}"></h:outputText>
				<h:outputText value="Balance Amount"></h:outputText>
				<h:outputText value="#{transactionMB.balanceAmount}"></h:outputText>
				<h:outputText value="Date Of Transaction"></h:outputText>
				<h:outputText value="#{transactionMB.transactionDate}">
					<f:convertDateTime type="date" pattern="dd-MMM-yy"/>
				</h:outputText>
			</h:panelGrid><br>
			<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
		</h:form>
	</center>
</f:view>
</body>
</html>