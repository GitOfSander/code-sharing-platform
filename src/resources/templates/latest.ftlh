<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Latest</title>
        <link rel="stylesheet" href="/css/stylesheet.css" />
    </head>
    <body>
        <#list model["codes"] as code>
            <span id="load_date">${ code.date }</span>
            <pre id="code_snippet">${ code.code }</pre>
        </#list>
    </body>
</html>