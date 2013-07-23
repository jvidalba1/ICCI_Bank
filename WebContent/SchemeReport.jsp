<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Scheme Report</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:form id="form1" rendered="#{schemeReportMB.render}">
<h:panelGrid border="1" columns="2" >
		
		<h:outputText value="SchemeId"></h:outputText>
		<h:selectOneMenu id="scheId" required="true" requiredMessage="You have to select one SchemeId" valueChangeListener="#{schemeReportMB.getCustomersForScheme}" onchange="submit()">
		<f:selectItem itemLabel="-select-" itemDisabled="true"/>
		<f:selectItem itemLabel="A" itemValue="A"/>
		<f:selectItem itemLabel="B" itemValue="B"/>
		<f:selectItem itemLabel="C" itemValue="C"/>
		<f:selectItem itemLabel="D" itemValue="D"/>
		<f:selectItem itemLabel="E" itemValue="E"/>
		</h:selectOneMenu>
		</h:panelGrid>
		<h:message for="scheId"></h:message>
		<br>
		<h:commandButton type="submit" value="Submit" onclick="window.open('SchemeDetails.jsp', '_blank', 'dependent=yes, menubar=no, toolbar=no'); return false;"></h:commandButton><br>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>
	<h1><h:outputLabel value="#{schemeReportMB.message}"></h:outputLabel> </h1>
</center>
</f:view>
</body>
</html>