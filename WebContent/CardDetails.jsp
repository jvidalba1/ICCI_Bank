<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<script type="text/javascript" src="change.js"></script>
<title>Card Details</title>
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
	<h:form id="form1">
		<h1><h:outputText value="#{cardMB.message}"></h:outputText> </h1>
		<h:panelGrid border="1" columns="5" rendered="#{cardMB.message == null}">
			<f:facet name="header">
				<h:outputText value="Card Details" style="text-align: center"></h:outputText>
			</f:facet>
			<h:outputText value="Enter your card No"></h:outputText>
			<h:inputText value="#{cardMB.cardNo1}" required="true" requiredMessage="All fields are Mandatory, enter a correct cardNo and pin" id="uno" maxlength="4" styleClass="cardno" onkeyup="fn_jump1(this)">
				<f:validateLength maximum="4" minimum="4"></f:validateLength>
			</h:inputText>
			<h:inputText value="#{cardMB.cardNo2}" required="true" requiredMessage="All fields are Mandatory, enter a correct cardNo and pin" id="dos" maxlength="4" styleClass="cardno" onkeyup="fn_jump2(this)">
				<f:validateLength maximum="4" minimum="4"></f:validateLength>
			</h:inputText>
			<h:inputText value="#{cardMB.cardNo3}" required="true" requiredMessage="All fields are Mandatory, enter a correct cardNo and pin" id="tres" maxlength="4" styleClass="cardno" onkeyup="fn_jump3(this)">
				<f:validateLength maximum="4" minimum="4"></f:validateLength>
			</h:inputText>
			<h:inputText value="#{cardMB.cardNo4}" required="true" requiredMessage="All fields are Mandatory, enter a correct cardNo and pin" id="cuatro" maxlength="4" styleClass="cardno">
				<f:validateLength maximum="4" minimum="4"></f:validateLength>
			</h:inputText>
			<h:outputText value="Enter your pin"></h:outputText>
			<h:inputText value="#{cardMB.pin}" required="true" requiredMessage="All fields are Mandatory, enter a correct cardNo and pin" id="txtpin" maxlength="4"styleClass="cardno">
				<f:validateLength maximum="4" minimum="4"></f:validateLength>
			</h:inputText>
			<h:outputText></h:outputText><h:outputText></h:outputText><h:outputText></h:outputText>
			<h:commandButton type="submit" action="#{cardMB.checkCardDetails}" value="Submit"></h:commandButton>
		</h:panelGrid>
		<h:message for="uno" />
		<h:message for="dos"/>
		<h:message for="tres"/>
		<h:message for="cuatro"/>
		<h:message for="txtpin"/>
	</h:form>
	</center>

</f:view>
</body>
</html>