<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">

<title>View Card Details</title>
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
		<h1><h:outputText value="#{viewCardDetailsMB.message}"></h:outputText> </h1>
		<h:form id="form1">
			<h:panelGrid border="1" columns="2" rendered="#{viewCardDetailsMB.message == null}">
				<f:facet name="header">
					<h:outputText value="View Card Details" style="text-align: center"></h:outputText>
				</f:facet>
				<h:outputText value="Customer Name"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.customerName}"></h:outputText>
				<h:outputText value="Card Number"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.cardNo}"></h:outputText>
				<h:outputText value="Balance Amount"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.balanceAmount}"></h:outputText>
				<h:outputText value="Scheme Name"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.schemeId}"></h:outputText>
				<h:outputText value="Scheme Amount"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.schemeAmount}"></h:outputText>
				<h:outputText value="Card Amount"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.cardAmount}"></h:outputText>
				<h:outputText value="Rate of Interest"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.rateOfInterest}"></h:outputText>
				<h:outputText value="Last Payment Date"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.lastPaymentDate}">
					<f:convertDateTime pattern="dd-MMM-yyyy" type="date"/>
				</h:outputText>
				<h:outputText value="Card Status"></h:outputText>
				<h:outputText value="#{viewCardDetailsMB.cardStatus}"></h:outputText>
			</h:panelGrid>
		</h:form>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	</center>

</f:view>
</body>
</html>