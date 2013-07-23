<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="change.js" ></script>
<link rel="stylesheet" style="text/css" href="ext.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unblock Card</title>
</head>
<body>
<center><img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px"></center>
<f:view>
<center>
<h1><h:outputText value="#{unblockCardMB.message}"></h:outputText> </h1>
<h:form id="form1" rendered="#{unblockCardMB.message == null}">
	<h:panelGrid id="panel1" border="1" columns="5" cellpadding="1" cellspacing="2">
		<f:facet name="header">
			<h:outputText value="Unblock Card" style="text-align: center"></h:outputText>
		</f:facet>
		<h:outputText value="CardNo"></h:outputText>
		
		<!-- here goes the action event with the FROMDATE field -->
		<h:inputText id="uno" value="#{unblockCardMB.cardPartNo1}" required="true" requiredMessage="Must enter number (field1)" converter="javax.faces.Long" converterMessage="Enter only integer number (field1)"  
					 validatorMessage="Must be 4 digits (field1)" maxlength="4" styleClass="cardno" onkeyup="fn_jump1(this)">
			<f:validateLength minimum="4" maximum="4"/>	
		</h:inputText>
		<h:inputText id="dos" value="#{unblockCardMB.cardPartNo2}" required="true" requiredMessage="Must enter number (field2)" converter="javax.faces.Long" converterMessage="Enter only integer number (field2)"  
					 validatorMessage="Must be 4 digits (field2)" maxlength="4" styleClass="cardno" onkeyup="fn_jump2(this)(this)">
			<f:validateLength minimum="4" maximum="4" />	
		</h:inputText>
		<h:inputText id="tres" value="#{unblockCardMB.cardPartNo3}" required="true" requiredMessage="Must enter number (field3)" converter="javax.faces.Long" converterMessage="Enter only integer number (field3)"  
					 validatorMessage="Must be 4 digits (field3)" maxlength="4" styleClass="cardno" onkeyup="fn_jump3(this)(this)">
			<f:validateLength minimum="4" maximum="4" />	
		</h:inputText>
		<h:inputText id="cuatro" value="#{unblockCardMB.cardPartNo4}" required="true" requiredMessage="Must enter number (field4)" converter="javax.faces.Long" converterMessage="Enter only integer number (field4)"  
					 validatorMessage="Must be 4 digits (field4)" maxlength="4" styleClass="cardno">
			<f:validateLength minimum="4" maximum="4" />	
		</h:inputText>		
	
		<!-- here goes the action event with the Get Details button -->
		<h:commandButton value="Get Details" type="submit" action="#{unblockCardMB.unblockCard}"/>
		
	</h:panelGrid>
	<h:message for="unblockTxt1" ></h:message><br>
	<h:message for="unblockTxt2" ></h:message><br>
	<h:message for="unblockTxt3" ></h:message><br>
	<h:message for="unblockTxt4" ></h:message><br><br><br>
	<h:outputText value="#{unblockCardMB.message}"></h:outputText><br>
	<h:outputLink value="CRHomePage.jsp">Home</h:outputLink>
	
</h:form>
</center>
</f:view>
</body>
</html>