<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('USER')">
<center>
<h2>User DashBoard</h2>
<br><a href="/logout">logout</a>
</center>
</sec:authorize>