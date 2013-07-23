<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" style="text/css" href="ext.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve_Pending_RejectedCardReport Page</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h1><h:outputText value="#{cardStatusMB.message}"></h:outputText></h1>
	<h:form id="form1" rendered="#{cardStatusMB.message == null}">
		<h:panelGrid border="1" columns="3" >
			<f:facet name="header">
				<h:outputText value="Approved & Pending/Rejected Card Report" style="text-align: center"></h:outputText>
			</f:facet>
			<h:outputText value="From Date"></h:outputText>
			
			<!-- here goes the action event with the FROMDATE field -->
			<h:inputText id="fromDateTxt" value="#{cardStatusMB.fromDate}" required="true" requiredMessage="Enter a date" converterMessage="Enter correct format Date (dd-MMM-yyyy)">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
			</h:inputText>
			<h:message for="fromDateTxt"></h:message>
			
			<h:outputText value="To Date" ></h:outputText>
			
			<!-- here goes the binding with the TODATE field-->
			<h:inputText id="toDateTxt" value="#{cardStatusMB.toDate}" required="true" requiredMessage="Enter a date" converterMessage="Enter correct format Date (dd-MMM-yyyy)">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
			</h:inputText>
			<h:message for="toDateTxt"></h:message>
			<h:commandButton value="Reset" type="reset" ></h:commandButton>
						
			<h:commandButton value="Get Details" action="#{cardStatusMB.statusDetails}" style="text-align: center"/>
			<h:outputLabel/>
			</h:panelGrid>
			<h:outputText value="#{cardStatusMB.message}"></h:outputText><br><br>
			<h:outputText value="The Percentage of Application Approve is: " rendered="#{cardStatusMB.grup2}"/>
			<u><h:commandLink value="#{cardStatusMB.approvedPercent}%" action="#{cardStatusMB.getCardNumbers}" rendered="#{cardStatusMB.grup2}"/></u><br>
	
			
				<h:dataTable value="#{cardStatusMB.cardNoList}" var="row" binding="#{cardStatusMB.cardData}" rendered="#{cardStatusMB.grup3}">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="CardNo"></h:outputText>
						</f:facet>
						<h:commandLink value="#{row}" action="#{cardStatusMB.acceptedReport}" onclick="submit()"></h:commandLink>
					</h:column>
				</h:dataTable>
				<br>
			<h:outputText value="The Percentage of Application Rejected is: " rendered="#{cardStatusMB.grup2}"/>
			<u><h:commandLink value="#{cardStatusMB.rejectedPercent}%"  action="#{cardStatusMB.rejectedReport}" rendered="#{cardStatusMB.grup2}"/></u><br>
			<br><br>
			<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
		</h:form>
</center>
</f:view>
</body>
</html>