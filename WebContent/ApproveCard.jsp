<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Approve Card</title>
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px"/>
	<h:form id="form1">
	<h3><h:outputLabel value="Approve Card" style="color:red"></h:outputLabel></h3>
		<h:dataTable border="1" value="#{cardApplicationMB.cardTOList}" var="cardApp">
			<h:column id="column1"  rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Application Id"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{cardApp.applicationId}"></h:outputLabel>
			</h:column>
			<h:column id="column2" rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Customer Name"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{cardApp.name}"></h:outputLabel>
			</h:column>
			<h:column id="column3" rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Address"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{cardApp.address}"></h:outputLabel>
			</h:column>
			<h:column id="column4" rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Email"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{cardApp.email}"></h:outputLabel>
			</h:column>
			<h:column id="column5" rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Phone"></h:outputText>
				</f:facet>
				<h:outputLabel value="#{cardApp.phone}"></h:outputLabel>
			</h:column>
			<h:column id="column6" rendered="#{cardApplicationMB.renderApply}" >
				<f:facet name="header">
					<h:outputText value="Select Application"></h:outputText>
				</f:facet>
				<center><h:selectBooleanCheckbox value="#{cardApp.applicationSelected}">
					</h:selectBooleanCheckbox></center>
			</h:column>
		</h:dataTable>
		<h:commandButton type="submit" value="Approve"
			action="#{cardApplicationMB.approveCard}" rendered="#{cardApplicationMB.renderApply}" >
		</h:commandButton>
		<br><br>
		<h1><h:outputText value="#{cardApplicationMB.message}"></h:outputText></h1>
	</h:form>
	
	<h:outputLink value="CRHomePage.jsp"
		rendered="#{cardApplicationMB.renderApply}">Home</h:outputLink>
	</center>
</f:view>
</body>
</html>