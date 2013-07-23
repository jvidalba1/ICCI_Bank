<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue Statistics</title>
<link rel="stylesheet" style="text/css" href="ext.css">
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
	<h:form id="form1">
		<h:panelGrid border="1" columns="2">
			<f:facet name="header">
				<h:outputText value="Revenue Statistics" style="text-align: center"></h:outputText>
			</f:facet>
			<h:outputText value="From Date"></h:outputText>
			<h:inputText value="#{revenueStatisticsMB.fromDate}">
				<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
			</h:inputText>
			<h:outputText value="To Date"></h:outputText>
			<h:inputText value="#{revenueStatisticsMB.toDate}">
				<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
			</h:inputText>
			<h:commandButton value="Get Details!" type="submit" action="#{revenueStatisticsMB.getDetails}"></h:commandButton>
		</h:panelGrid>
		<br><br>
		<h:panelGrid border="1" columns="2" rendered="#{revenueStatisticsMB.values != null}">
			<h:outputText value="Profit"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.profit}"></h:outputText>
			<h:outputText value="Revenue"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.revenue}"></h:outputText>
			<h:outputText value="Expenditure"></h:outputText>
			<h:outputText value="#{revenueStatisticsMB.expediture}"></h:outputText>
		</h:panelGrid>
	</h:form>
	<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</center>
</f:view>
</body>
</html>