<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>OnLine Transaction</title>
</head>
<body>
<f:view>
<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">	
	<h1><h:outputText value="#{transactionMB.errorMessage}"></h:outputText> </h1>
	<h:form id="form1" rendered="#{transactionMB.errorMessage == null}">	
			<h:panelGrid border="1" columns="2" style="text-align: center">
				<f:facet name="header">
					<h:outputText value="Make An OnLine Transaction" style="font-size: 18px; font-weight: bolder"></h:outputText>
				</f:facet>
				<h:outputText value="Description"></h:outputText>
				<h:inputText id="description" value="#{transactionMB.description}" required="true" requiredMessage="Insert a Description">
				</h:inputText>
				<h:outputText value="Amount"></h:outputText>
				<h:inputText id="amount" value="#{transactionMB.amount}" required="true" requiredMessage="Insert an Amount" validatorMessage="Insert a Valid Amount. Should be between 0 and 9999999.99">
					<f:validateDoubleRange minimum="0" maximum="9999999.99" ></f:validateDoubleRange>
				</h:inputText>
				<h:commandButton type="submit" value="Pay Online" action="#{transactionMB.saveTransaction}"></h:commandButton>
			</h:panelGrid>
			<h:outputText value="#{transactionMB.errorMessage}"></h:outputText><br>
		</h:form>
		<h:messages></h:messages><br>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	</center>
</f:view>
</body>
</html>