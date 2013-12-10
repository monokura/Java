<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean"  %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html>
<head>
<title>webchat</title>
</head>
<body>

<h1>webchat</h1>

<hr />

<h2>�����t�H�[��</h2>

<html:form action="/SaveMessage">
<table>
<tr><th>���O</th>
    <td><html:text property="name" style="width:256" /></td></tr>
<tr><th>���b�Z�[�W</th>
    <td><html:text property="message" style="width:256" /></td></tr>
<tr><td colspan="2" align="right">
    <html:reset value="���Z�b�g" />
    <html:submit value="���M" /></td></tr>
</table>
</html:form>

<hr />

<h2>���b�Z�[�W</h2>

<html:link action="/ShowMessages">�X�V</html:link>

<table border="1" cellspacing="0" cellpadding="3">
<tr><th>Date</th>
    <th>Name</th>
    <th>Message</th></tr>
<logic:iterate id="message" name="messages" scope="request">
<tr><td><bean:write name="message" property="date" /></td>
    <td><bean:write name="message" property="name" /></td>
    <td><bean:write name="message" property="message" /></td></tr>
</logic:iterate>
</table>

</body>
</html:html>