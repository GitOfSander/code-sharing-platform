<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Code</title>
        <link rel="stylesheet" href="/css/stylesheet.css" />
        <link rel="stylesheet"
               href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
        <span id="load_date">${ model["date"] }</span>
        <pre id="code_snippet">
            <code>${ model["code"] }</code>
        </pre>
    </body>
</html>