<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <h2 class="hello-title">${message!"Hello World"}</h2>
<form method="POST" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>
        <a href="/listUser">View users</a>
        <a href="/listPhone">View phones</a>
        <a href="/listCompanies">View companies</a>
        <a href="/pdfreport">View pdf</a>
</body>
</html>
