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
<h:form id="form1" rendered="#{blockStatisticsMB.render}">
<h3 style="color:red">Blocked Card Statistics</h3>
<h:panelGrid columns="2" border="1">
<h:outputText value="Select the blocking reason"></h:outputText>
<h:selectManyCheckbox valueChangeListener="#{blockStatisticsMB.getBlockedDetails}" onclick="submit()" id="reason" >
<f:selectItem itemLabel="Payment not done" itemValue="Payment not done"/>
<f:selectItem itemLabel="Theft" itemValue="Theft"/>
<f:selectItem itemLabel="Others" itemValue="Others"/>
</h:selectManyCheckbox>
</h:panelGrid>

<h:outputLabel value="Select one option to display the statistics" rendered="#{blockStatisticsMB.blockedCards==null}"></h:outputLabel><br>
<h:message for="reason" ></h:message>
</h:form>
<br>
<h:form>
<h:dataTable border="1" value="#{blockStatisticsMB.blockedCards}" var="blockcard" binding="#{blockStatisticsMB.cards}" rendered="#{blockStatisticsMB.blockedCards!=null}">
		<h:column id="column1">
			<f:facet name="header">
				<h:outputText value="BlockId"></h:outputText>
			</f:facet>
			<h:outputText value="#{blockcard.blockId}"></h:outputText>
		</h:column>
		<h:column id="column2">
			<f:facet name="header">
				<h:outputText value="Card Number"></h:outputText>
			</f:facet>
			<h:commandLink value="#{blockcard.card}" action="#{blockStatisticsMB.getCardDetails}"></h:commandLink>
		</h:column>
		<h:column id="column3">
			<f:facet name="header">
				<h:outputText value="Date of Block"></h:outputText>
			</f:facet>
			<h:outputText value="#{blockcard.dateOfBlock}">
			<f:convertDateTime dateStyle="short" pattern="dd-MMM-yyyy"/>
			</h:outputText>
		</h:column>
		<h:column id="column4">
			<f:facet name="header">
				<h:outputText value="Reason"></h:outputText>
			</f:facet>
			<h:outputText value="#{blockcard.description}"></h:outputText>
		</h:column>
	</h:dataTable>

</h:form>
<br>
<h:form>
		<h:dataTable border="1" value ="#{blockStatisticsMB.cardTo}" var="card" rendered="#{blockStatisticsMB.cardTo!=null}">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Card Number"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.cardNo}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="Scheme Chosen"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.schemeId}"></h:outputText>
			</h:column>
				<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Balance Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.balanceAmount}"></h:outputText>
			</h:column>
				<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Date of Registration"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.dateOfRegistration}">
				<f:convertDateTime dateStyle="short" pattern="dd-MMM-yyyy"/>
				</h:outputText>
			</h:column>
				<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Customer Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{card.customerId}"></h:outputText>
			</h:column>
		</h:dataTable>
		<h1><h:outputText value="#{blockStatisticsMB.message}"></h:outputText> </h1>
		<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	</h:form>
</center>
</f:view>
</body>
</html>