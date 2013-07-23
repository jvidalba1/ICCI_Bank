<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICCI Bank</title>
<link rel="stylesheet" style="text/css" href="ext.css">
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:form id="form1" style="text-align: center">

<h:panelGrid border="1" columns="1" >

		<f:facet name="header">
		<h:outputText value="Welcome #{loginMB.userName}" style="text-align: center"></h:outputText>
			
		</f:facet>
		
		<h:outputLink value="ApproveCard.jsp">Register Card</h:outputLink>
		<h:outputLink value="Payment.jsp">Make Payment</h:outputLink>
		<h:outputLink value="BlockCard.jsp">Block Card</h:outputLink>
		<h:outputLink value="UnblockCard.jsp" >Unblock Card</h:outputLink>
		<h:outputLink value="SchemeReport.jsp">SchemeReport</h:outputLink>
		<h:outputLink value="TransactionStatisticsReport.jsp" >Transaction Statistics Report</h:outputLink>
		<h:outputLink value="BlockedCardStatistics.jsp"> Blocked Statistics</h:outputLink>
		<h:outputLink value="Approved&Pending_RejectedCardReport.jsp">Approved and Rejected-Pending Report</h:outputLink>
		<h:outputLink value="RevenueStatistics.jsp" >Revenue Statistics</h:outputLink>
	</h:panelGrid>
	</h:form>
</center>
</f:view>
</body>
</html>