<#-- @ftlvariable name="user" type="com.epam.model.PhoneCompany" -->
<#-- @ftlvariable name="form" type="com.epam.model.NumberChangeForm" -->
<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add number</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Add phone number</h1>


<form role="numberChangeForm" name="numberChangeForm" action="" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    Phone number:<br>
                <input type="text" name="phoneNumber" value="${numberChangeForm.phoneNumber}">
                <br><br>
    <form:label path="company">Company</form:label>
    <select name="companyName" id="companyName" required>
            <#list companies as item>
                <option>${item.name} </option>
            </#list>
    </select>

    <input type="submit" value="Submit" />
</form>

</body>
</html>