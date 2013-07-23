<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<script type="text/javascript" src="change.js"></script>
<title>Payment</title>
</head>
<body>
<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
	<h1><h:outputText value="#{paymentMB.message}"></h:outputText></h1>
	<h:form id="form1">
		<h:panelGrid id="cardGrid" border="1" columns="2" >
			<f:facet name="header">
				<h:outputLabel value="Enter Card No" style="text-align: center"></h:outputLabel>
			</f:facet>
			<h:outputLabel value="Card No:"></h:outputLabel>
			<h:panelGroup id="cardGroup">
				<h:inputText id="uno"  value="#{paymentMB.cardNo[0]}" styleClass="cardno" style="width: 116px" onkeyup="fn_jump1(this)" validatorMessage="Numeric Field. 4 Numbers are required" required="true" requiredMessage="Mandatory Field" maxlength="4">
					<f:validateLongRange maximum="9999" minimum="0000"></f:validateLongRange>
				</h:inputText>
				<h:inputText id="dos"  value="#{paymentMB.cardNo[1]}" styleClass="cardno" style="width: 116px" onkeyup="fn_jump2(this)" validatorMessage="Numeric Field. 4 Numbers are required" required="true" requiredMessage="Mandatory Field" maxlength="4">
					<f:validateLongRange maximum="9999" minimum="0000"></f:validateLongRange>
				</h:inputText>
				<h:inputText id="tres"  value="#{paymentMB.cardNo[2]}" styleClass="cardno"style="width: 116px" onkeyup="fn_jump3(this)" validatorMessage="Numeric Field. 4 Numbers are required" required="true" requiredMessage="Mandatory Field" maxlength="4">
					<f:validateLongRange maximum="9999" minimum="0000"></f:validateLongRange>
				</h:inputText>
				<h:inputText id="cuatro"  value="#{paymentMB.cardNo[3]}" styleClass="cardno" style="width: 116px" validatorMessage="Numeric Field. 4 Numbers are required" required="true" requiredMessage="Mandatory Field" maxlength="4">
					<f:validateLongRange maximum="9999" minimum="0000"></f:validateLongRange>
				</h:inputText>
			</h:panelGroup>
		</h:panelGrid>
		<h:commandButton value="Ok" type="submit" action="#{paymentMB.getPaymentInformation}"></h:commandButton>
		<br><br>
	</h:form>
	<h:form id="formf1" rendered="#{paymentMB.disable}">
		<h:panelGrid id="cardGrid2" border="1" columns="2">
			<f:facet name="header">
				<h:outputText value="Make a Payment:" style="text-align: center"></h:outputText>
			</f:facet>
			<h:outputLabel id="custId" value="CustomerId"></h:outputLabel>
			<h:outputText id="acustId" value="#{paymentMB.customerId}"></h:outputText>
			<h:outputLabel id="custName" value="CustomerName"></h:outputLabel>
			<h:outputText id="acustName" value="#{paymentMB.customerName}"></h:outputText>
			<h:outputLabel id="balAmount" value="Balance Amount"></h:outputLabel>
			<h:outputText id="abalAmount" value="#{paymentMB.balanceAmount}"></h:outputText>
			<h:outputLabel id="lPaymDate" value="Last Payment Date"></h:outputLabel>
			<h:outputText id="alPaymDate" value="#{paymentMB.lastPaymentDate}">
				<f:convertDateTime pattern="dd-MMM-yyyy" type="date"/>
			</h:outputText>
			<h:outputLabel id="cardStatus" value="Card Status"></h:outputLabel>
			<h:outputText id="acardStatus" value="#{paymentMB.cardStatus}"></h:outputText>
			<h:outputLabel id="sPaymType" value="Select Payment Type"></h:outputLabel>
			<h:selectOneRadio id="asPaymType" value="#{paymentMB.paymentType}" required="true" requiredMessage="Select a payment type">
				<f:selectItem itemLabel="Full" itemValue="F"/>
				<f:selectItem itemLabel="Minimum" itemValue="M"/>
			</h:selectOneRadio>
			<h:outputLabel value="  "></h:outputLabel>
			<h:commandButton value="Get Amount Details" type="submit" action="#{paymentMB.calculateBalance}"></h:commandButton>
			<h:outputLabel id="amountPaid" value="Amount Paid" rendered="#{paymentMB.disable2}"></h:outputLabel>
			<h:outputText id="aamountPaid" value="#{paymentMB.amountPaid}" rendered="#{paymentMB.disable2}"></h:outputText>
			<h:outputLabel id="balance" value="Balance(inclusive of tax)" rendered="#{paymentMB.disable2}"></h:outputLabel>
			<h:outputText id="abalance" value="#{paymentMB.balance}" rendered="#{paymentMB.disable2}"></h:outputText>
		</h:panelGrid>
		<h:commandButton type="submit" value="Make Payment" rendered="#{paymentMB.balanceAmount !=0}" action="#{paymentMB.makePayment}"/>
		<br><br>
		<h:outputLink value="CRHomePage.jsp">CRHomePage</h:outputLink>
	</h:form>
	<h:messages/><br>
	
</center>
</f:view>
</body>
</html>