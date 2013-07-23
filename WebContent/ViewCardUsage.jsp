<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="extScheme&CardUsage.css">
<title>View Card Usage</title>
</head>
<body>
	<center>
		<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
	</center>
<f:view>
	<center>
		<h:form id="form1" rendered="#{cardUsageMB.permission}">
			Note: You can view the last 6 transactions only
			<h:panelGrid border="1" columns="2" style="width: 384px">
				<f:facet name="header" >
					<h:outputText id="viewCardUsageTitle" value="View Card Usage" style="text-align: center; font-size: 150%;"></h:outputText>	
				</f:facet>
				<h:selectOneRadio id="yearCheckBox" valueChangeListener="#{cardUsageMB.populateMonths}" onclick="submit()" style="width: 343px">
					<f:selectItem id="yearCheckBox1" itemLabel="This Year" itemValue="current"/>
					<f:selectItem id="yearCheckBox2" itemLabel="Previous Year" itemValue="previous"/>
				</h:selectOneRadio>
				
				</h:panelGrid>
		</h:form>
		<h:form id="form3" rendered="#{cardUsageMB.permission}">
				<h:panelGrid border="1" columns="3">
					<h:outputLabel value="Select The Month"></h:outputLabel>
					<h:selectOneMenu id="selMonth" value="#{cardUsageMB.monthSelectedValue}" onclick="reval()" required="true" requiredMessage="Mandatory field">
						<f:selectItem itemLabel="--SELECT--"/>
						<f:selectItems value="#{cardUsageMB.displayList}"/>
					</h:selectOneMenu>
					<h:message for="selMonth"></h:message>
				</h:panelGrid>
				
				<h:commandButton rendered="true" value="Get Transactions" type="submit" action="#{cardUsageMB.populateTable}"></h:commandButton>
		</h:form>
		
		<h:form id="form2" rendered="#{cardUsageMB.permission}">
			<h:dataTable border="1" columnClasses="c2" style="width: 763px; text-align: center" value="#{cardUsageMB.transactionList}" var="row">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputText styleClass="header" value="TransactionId"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.transactionId}"></h:outputText>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText styleClass="header" value="Date of Transaction"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.dateOfTransaction}" converter="javax.faces.DateTime">
						<f:convertDateTime pattern="dd-MMM-yyyy" dateStyle="short" type="date"/>
					</h:outputText>
				</h:column>
				<h:column id="column3">
					<f:facet name="header">
						<h:outputText styleClass="header" value="Description"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.description}"></h:outputText>
				</h:column>
				<h:column id="column4">
					<f:facet name="header">
						<h:outputText styleClass="header" value="Amount"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.amount}"></h:outputText>
				</h:column>
			</h:dataTable>			
		</h:form>
		<h1><h:outputText value="#{cardUsageMB.message}"></h:outputText></h1>
		<h:outputLink value="CustomerHomePage.jsp">Home</h:outputLink>
	</center>
</f:view>
</body>
</html>