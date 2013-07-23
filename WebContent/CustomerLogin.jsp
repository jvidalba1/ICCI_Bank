<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="WEB-INF/ext.css">
<title>Insert title here</title>
</head>
<body>
<center><img alt="ICCI" src="header.jpg" width="500"
	height="130" style="width: 755px; height: 117px" /></center>
<center><f:view>
	<h:form>
		<h:panelGrid columns="3" border="2" rendered="#{customerMB.message == null}">
			<f:facet name="header">
				<h:outputText value="Customer Login" style="text-align:center" />
			</f:facet>
			<h:outputText value="Customer ID"></h:outputText>
			<h:inputText value="#{customerMB.customerId}"></h:inputText>
			<br>
			<h:outputText value="GeneratedCode"></h:outputText>
			<h:outputLabel value="#{customerMB.randomCode}"></h:outputLabel>
			<br>
			<h:outputText value="Enter the code shown"></h:outputText>
			<h:inputText id="codeshow" value="#{customerMB.enteredCode}"
				required="true" requiredMessage="Please enter the same code"></h:inputText>

			<h:message for="codeshow"></h:message>
			<h:commandButton id="button" value="Enter" type="submit"
				action="#{customerMB.validateCustomer}"></h:commandButton>
			<h:inputHidden value="#{customerMB.randomCode}"></h:inputHidden>
		</h:panelGrid>
		<h:outputLabel></h:outputLabel>
	</h:form>
	<h:outputText value="#{customerMB.message}"></h:outputText>
</f:view></center>
</body>
</html>