<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ADMIN')">
<center>
<h2>Admin DashBoard</h2>
<br><a href="/logout">logout</a>
</center>
</sec:authorize>