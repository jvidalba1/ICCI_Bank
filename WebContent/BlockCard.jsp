<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="WEB-INF/ext.css">
<title>Insert title here</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px">
</center>
<f:view>
<center>
<h:form id="form1">
<h2>Enter Card No:</h2>
<h:outputText value="CardNo "/>
<h:inputText id="txt1"  value="#{blockCardMB.cardNo[0]}" styleClass="cardno" maxlength="4" required="true" requiredMessage="all fields are mandatory"/>
<h:inputText id="txt2"  value="#{blockCardMB.cardNo[1]}" styleClass="cardno" maxlength="4" required="true" requiredMessage="all fields are mandatory"/>
<h:inputText id="txt3"  value="#{blockCardMB.cardNo[2]}" styleClass="cardno" maxlength="4" required="true" requiredMessage="all fields are mandatory"/>
<h:inputText id="txt4"  value="#{blockCardMB.cardNo[3]}" styleClass="cardno" maxlength="4" required="true" requiredMessage="all fields are mandatory"/>
<h:panelGrid border="1" columns="2" >
		<f:facet name="header">
			<h:outputText value="Block Card" style="text-align:center" />
		</f:facet>
		<h:outputText value="Cause for Blocking card"></h:outputText>
		<h:selectOneMenu id="menu1" value="#{blockCardMB.cause}" onchange="submit()" valueChangeListener="#{blockCardMB.checkCause}">
			<f:selectItem itemLabel="-Select-" itemDisabled="true"/>
			<f:selectItems value="#{blockCardMB.causes}"/>
		</h:selectOneMenu>
		<h:outputText value="Reason" style="align-text:center"/>
		<h:inputTextarea value="#{blockCardMB.cause}" disabled="#{blockCardMB.disable}"></h:inputTextarea>
	</h:panelGrid>
	<h:commandButton type="submit" value="Block Card" action="#{blockCardMB.blockCard}"></h:commandButton><br>
	<h:message for="txt1" />
	<h:message for="txt2" />
	<h:message for="txt3" />
	<h:message for="txt4" /><br>
	<h:outputText value="#{blockCardMB.message}"></h:outputText>
	</h:form>
	</center>
</f:view>
</body>
</html>