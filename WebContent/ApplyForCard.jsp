<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Apply For Card</title>
</head>
<body>
<f:view>
	<center>
		<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px"/>
		<h:form id="form1">
			<h:panelGrid border="1" columns="3" rendered="#{cardApplicationMB.renderApply == true}">
				<f:facet name="header">
					<h:outputText value="Apply For Card" style="font-size:25px;"></h:outputText>
				</f:facet>
				<h:outputLabel value="Name"></h:outputLabel>
				<h:inputText id="name" value="#{cardApplicationMB.name}" 
					required="true" requiredMessage="Name cannot be empty"
					validatorMessage="Minimum(3) and Maximum(20) Name Size is Mandatory.">
					<f:validateLength minimum="3" maximum="20"></f:validateLength>
				</h:inputText>
				<h:message for="name"></h:message>
				
				<h:outputLabel value="Address"></h:outputLabel>
				<h:inputText id="address" value="#{cardApplicationMB.address}"
					required="true" requiredMessage="Address cannot be empty"
					validatorMessage="Maximum Address Size is 40">
					<f:validateLength maximum="40"></f:validateLength>
				</h:inputText>
				<h:message for="address"></h:message>
				
				<h:outputLabel value="Email"></h:outputLabel>
				<h:inputText id="email" value="#{cardApplicationMB.email}"
					required="true" requiredMessage="Email cannot be empty">
					<f:validateLength maximum="20"></f:validateLength>
					<f:validator validatorId="validateEmail"/>
				</h:inputText>
				<h:message for="email"></h:message>
				
				<h:outputLabel value="Phone"></h:outputLabel>
				<h:inputText id="iphone" value="#{cardApplicationMB.phone}"
					required="true" requiredMessage="Phone cannot be empty"
					validatorMessage="Only numeric. Maximum Phone Size is 10">
					<f:validateLongRange maximum="9999999999"></f:validateLongRange>
				</h:inputText>
				<h:message for="iphone"></h:message>
				
				<h:outputLabel value="Please Enter the characters given"></h:outputLabel>
				<h:outputLabel></h:outputLabel>
				<h:inputHidden value="#{cardApplicationMB.randomCharacter}"></h:inputHidden>
				
				<h:outputText value="#{cardApplicationMB.randomCharacter}"
					style="color:maroon; text-align:center; font-size:25px;" ></h:outputText>								
				<h:inputText id="characters" value="#{cardApplicationMB.enteredCharacter}"
					required="true" requiredMessage="Characters cannot be empty"></h:inputText>								
				<h:message for="characters"></h:message>
							
			<h:commandButton type="submit" value="Apply"
				action="#{cardApplicationMB.applyCard}"></h:commandButton>
			</h:panelGrid>
			<h1><h:outputLabel value="#{cardApplicationMB.message}"></h:outputLabel></h1>
		</h:form>
		<h:outputLink value="LoginScreen.jsp">Login Screen</h:outputLink>
	</center>
</f:view>
</body>
</html>