<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="eu.kielczewski.example.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
</head>
<body>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <li><a href="/login">Log in</a></li>
    </#if>
    <#if currentUser??>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        </li>
        <li><a href="/user/${currentUser.id}">View myself</a></li>
    </#if>
    <#if currentUser?? && currentUser.role == "BOOKING_MANAGER">
        <li><a href="/user/create">Create a new user</a></li>
        <li><a href="/users">View all users</a></li>
        <li><a href="/listPhone">View phones</a></li>
        <li><a href="/listCompanies">View companies</a></li>
        <li><a href="/pdfreport">View pdf</a></li>
        <form method="POST" action="/upload" enctype="multipart/form-data">
            <input type="file" name="file" /><br/><br/>
            <input type="submit" value="Submit" />
        </form>
    </#if>
    </ul>
</nav>
</body>
</html>