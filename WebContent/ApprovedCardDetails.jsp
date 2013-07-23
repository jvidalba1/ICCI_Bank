<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" style="text/css" href="ext.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approved Card Details Page</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px;" />
</center>
<f:view>
	<center>
	<h:form id="form1">		
		<h:dataTable  value="#{cardStatusMB.cardApprovedList}" id="table2" border="1" columnClasses="c2" var="row" >
			<f:facet name="header">
				<h:outputText id="title1" value="Approved & Pending/Rejected Card Report" style="text-align: center" ></h:outputText>
			</f:facet>
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText styleClass="header" value="Customer Name"></h:outputText>
				</f:facet>
				
				<h:outputText value="#{row.customerName}"></h:outputText>			
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText styleClass="header" value="Scheme Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.schemeAmt}"/>
			</h:column>
			<h:column id="column3">
				<f:facet  name="header">
					<h:outputText styleClass="header" value="Payment Type"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.paymentType}"/> 
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText styleClass="header" value="Date of Block (If applicable)" rendered="#{cardStatusMB.flag }">
					</h:outputText>
				</f:facet>
				<h:outputText value="#{row.dateOfBlock}" >
					<f:converter converterId="simpleDateFormat" />
				</h:outputText>
			</h:column>
		</h:dataTable>
		<br><br>
		<h:outputLink value="Approved&Pending_RejectedCardReport.jsp">Back</h:outputLink><br><br>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>
	</center>
</f:view>
</body>
</html>