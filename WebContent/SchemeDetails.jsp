<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Insert title here</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:form id="form1">
<h:panelGrid border="1" columns="4" >
		
		<h:outputText value="CustomerId"></h:outputText>
		<h:selectOneMenu id="lista1"   valueChangeListener="#{schemeReportMB.getCustomerAndCardDetails}" onchange="submit()">
		<f:selectItem itemLabel="-select-" itemDisabled="true"/>
		<f:selectItems id="list1" value="#{schemeReportMB.customerIdList}"/>
		</h:selectOneMenu>
		<h:outputText value="CardNo"></h:outputText>
		<h:selectOneMenu id="lista2"  valueChangeListener="#{schemeReportMB.getCustomerAndCardDetails}" onchange="submit()">
			<f:selectItem itemLabel="-select-" itemDisabled="true"/>
		<f:selectItems value="#{schemeReportMB.cardNoList}"/>
		</h:selectOneMenu>
	</h:panelGrid>
	<h:message for="lista1"></h:message>
	<h:message for="lista2"></h:message>
	</h:form>
	<h:form id="form2">
		<h:dataTable border="1" value="#{schemeReportMB.customerTo}" var="row" rendered="#{schemeReportMB.customerTo!=null && schemeReportMB.cardTo==null}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="CustomerId"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.customerId}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Name"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.name}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Address"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.address}"></h:outputText>
			</h:column>
		
		</h:dataTable>
		<br>
		<h:dataTable border="1" value="#{schemeReportMB.cardTo}" var="row2" rendered="#{schemeReportMB.cardTo!=null}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="CardNo"></h:outputText>
				</f:facet>
				<h:outputText value="#{row2.cardNo}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{row2.cardAmount}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="CustomerId"></h:outputText>
				</f:facet>
				<h:outputText value="#{row2.customerId}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Date of Registration"></h:outputText>
				</f:facet>
				<h:outputText value="#{row2.dateOfRegistration}">
				<f:convertDateTime dateStyle="short" pattern="dd-MMM-yyyy"/>
				</h:outputText>
			</h:column>
		</h:dataTable>
		
		<h:outputLink  onclick="window.close()">Close</h:outputLink>

	</h:form>
</center>
</f:view>
</body>
</html>