<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Choose Scheme</title>
</head>
<body>
	<center>
		<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
	</center>
<f:view>
		<center>
			<h:form id="form1" rendered="#{schemeMB.permission}">
				<h:panelGrid border="1" columns="2" >
					<f:facet name="header">
						<h:outputText id="chooseSchemeTitle" value="Choose a scheme" style="text-align: center; font-size: 150%;"></h:outputText>
					</f:facet>
					<h:outputText value="SchemeId"></h:outputText>
					<h:selectOneMenu id="selSchemeId" value="#{schemeMB.schemeId}" valueChangeListener="#{schemeMB.display}" style="color: black" onchange="submit()">
						<f:selectItem itemLabel="A" itemValue="A"/>
						<f:selectItem itemLabel="B" itemValue="B"/>
						<f:selectItem itemLabel="C" itemValue="C"/>
						<f:selectItem itemLabel="D" itemValue="D"/>
						<f:selectItem itemLabel="E" itemValue="E"/>
					</h:selectOneMenu>
				</h:panelGrid>
				<br>
				<h:panelGrid styleClass="panelGrid" border="1" columns="2" >
					<h:outputLabel styleClass="panelGridText" value="SchemeId"></h:outputLabel>
					<h:outputText styleClass="panelGridText" value="#{schemeMB.schemeId}"></h:outputText>
					<h:outputLabel styleClass="panelGridText" value="SchemeAmount"></h:outputLabel>
					<h:outputText styleClass="panelGridText" value="#{schemeMB.schemeAmount}"></h:outputText>
					<h:outputLabel styleClass="panelGridText" value="MinimumAmount"></h:outputLabel>
					<h:outputText styleClass="panelGridText" value="#{schemeMB.minimumAmount}"></h:outputText>
					<h:outputLabel styleClass="panelGridText" value="InterestRate"></h:outputLabel>
					<h:outputText styleClass="panelGridText" value="#{schemeMB.interestRate}"></h:outputText>
				</h:panelGrid>
				<h:commandButton value="Choose" type="submit" action="success"></h:commandButton>				
			</h:form>
			<h1><h:outputLabel value="#{schemeMB.message}"></h:outputLabel></h1>
		</center>
</f:view>
</body>
</html>