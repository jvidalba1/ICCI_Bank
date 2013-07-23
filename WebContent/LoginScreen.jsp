<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="text/css" href="WEB-INF/ext.css">
<title>Login</title>
</head>
<body>
<center>
<img alt="ICCI" src="header.jpg" width="500" height="130" style="width: 755px; height: 117px"/>
</center>
<center>
<f:view>
<h:form>
<h:panelGrid columns="3">
<f:facet name="header">
	<h:outputText value="User Login" />
</f:facet>
<h:outputText value="Enter the UserName"></h:outputText>
<h:inputText id="name" value="#{loginMB.userName}" required="true" requiredMessage="UserName cannot be empty"></h:inputText>
<h:message for="name" />
<h:outputText value="Enter the password"></h:outputText>
<h:inputSecret id="password" value="#{loginMB.password}" required="true" requiredMessage="Password cannot be empty"></h:inputSecret>
<h:message for="password"></h:message>
<h:commandButton id="button" value="Login" type="submit" action="#{loginMB.validateLogin}"></h:commandButton>
<br>
</h:panelGrid>
<h:outputLabel value="#{loginMB.message}"></h:outputLabel><br><br>
<h:outputLink value="ApplyForCard.jsp">Apply for Card</h:outputLink>
</h:form>
</f:view>
</center>
</body>
</html>