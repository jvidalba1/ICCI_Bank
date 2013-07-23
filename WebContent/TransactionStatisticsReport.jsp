<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="ext.css">
<title>Transaction Statistics</title>
</head>
<body>
<f:view>
	<center>
	<img alt="ICCI" src="header.jpg" width="500" height="130"
		style="width: 755px; height: 117px">
		<h1><h:outputText value="#{transactionStatisticsMB.message}"></h:outputText></h1>
	</center>
	<h:form id="form1" rendered="#{transactionStatisticsMB.message == null}">
		<center>
			<h1 style="color: red;">Transaction Statistics</h1>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="From Date"></h:outputText>
			<h:inputText id="from" required="true"
				value="#{transactionStatisticsMB.fromDate}"
				requiredMessage="Insert From Date"
				converterMessage="Insert A valid Date in From Date Field">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:outputText value="To Date"></h:outputText>
			<h:inputText id="to" required="true"
				value="#{transactionStatisticsMB.toDate}"
				requiredMessage="Insert To Date"
				converterMessage="Insert A valid Date in To Date Field">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:inputText>
			<h:commandButton value="Submit" type="submit"
				action="#{transactionStatisticsMB.getStatistics}"></h:commandButton>
			<h:commandButton value="Reset" type="reset"></h:commandButton>
		</h:panelGrid>
		<h:outputText value="#{transactionStatisticsMB.message}"></h:outputText>
		</center>
	</h:form>
	<center>
		<h:messages></h:messages><br>
	</center>
	<br>
	<center><h:panelGrid border="1" columns="2">
		<h:form id="form3"
			rendered="#{transactionStatisticsMB.customers !=null && transactionStatisticsMB.count !=null}">
			<h:dataTable binding="#{transactionStatisticsMB.names}" border="1"
				value="#{transactionStatisticsMB.customers}" var="customer"
				style="text-align: center;width: 214px; vertical-align: middle">
				<h:column id="CustName">
					<f:facet name="header">
						<h:outputText value="Customer Name"></h:outputText>
					</f:facet>
					<h:commandLink action="#{transactionStatisticsMB.getCustomer}">
						<h:outputText id="name" value="#{customer.name}" />
					</h:commandLink>
				</h:column>
			</h:dataTable>
		</h:form>
		<h:form id="form4"
			rendered="#{transactionStatisticsMB.customers !=null && transactionStatisticsMB.count !=null}">
			<h:dataTable border="1" value="#{transactionStatisticsMB.count}"
				var="number" style="text-align: center; width: 214px" binding="#{transactionStatisticsMB.trans}">
				<h:column id="noTrans">
					<f:facet name="header">
						<h:outputText value="Number Of Transactions"></h:outputText>
					</f:facet>
					<h:commandLink value="#{number}"
						action="#{transactionStatisticsMB.getTransactions}"></h:commandLink>
				</h:column>
			</h:dataTable>
		</h:form>
	</h:panelGrid><br>
	</center>

	<center><h:panelGrid border="1" columns="6"
		rendered="#{transactionStatisticsMB.customerDetails !=null}">
		<h:outputText value="Customer Name" style="font-weight: bolder"></h:outputText>
		<h:outputText value="Customer Id" style="font-weight: bolder"></h:outputText>
		<h:outputText value="Address" style="font-weight: bolder"></h:outputText>
		<h:outputText value="Phone Number" style="font-weight: bolder"></h:outputText>
		<h:outputText value="E-Mail" style="font-weight: bolder"></h:outputText>
		<h:outputText value="UserName" style="font-weight: bolder"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.name}"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.customerId}"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.address}"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.phone}"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.EMail}"></h:outputText>
		<h:outputText value="#{transactionStatisticsMB.customerDetails.userName}"></h:outputText>
	</h:panelGrid></center>
	<center><h:dataTable border="1"
		value="#{transactionStatisticsMB.transaction}" var="trans"
		rendered="#{transactionStatisticsMB.transaction !=null}">
		<f:facet name="header">
			<h:outputText value="#{transactionStatisticsMB.cardNo}">Card Number: </h:outputText>
		</f:facet>
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText value="Transaction Id"></h:outputText>
			</f:facet>
			<h:outputText value="#{trans.transactionId}"></h:outputText>
		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText value="Date Of Transaction"></h:outputText>
			</f:facet>
			<h:outputText value="#{trans.dateOfTransaction}">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
			</h:outputText>
		</h:column>
		<h:column id="column3">
			<f:facet name="header">
				<h:outputText value="Description"></h:outputText>
			</f:facet>
			<h:outputText value="#{trans.description}"></h:outputText>
		</h:column>
		<h:column id="column4">
			<f:facet name="header">
				<h:outputText value="Amount"></h:outputText>
			</f:facet>
			<h:outputText value="#{trans.amount}"></h:outputText>
		</h:column>
		<h:column id="column5">
			<f:facet name="header">
				<h:outputText value="Balance Amount"></h:outputText>
			</f:facet>
			<h:outputText value="#{trans.balanceAmount}"></h:outputText>
		</h:column>
	</h:dataTable></center>
	<br>
	<center>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</center>
</f:view>
</body>
</html>