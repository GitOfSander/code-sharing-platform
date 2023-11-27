<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create</title>
        <link rel="stylesheet" href="/css/stylesheet.css" />

        <script src="/js/send.js"></script>
    </head>

    <body>
        <label for="views_restriction">View restriction</label>
        <input id="views_restriction" type="text"/>
        <label for="time_restriction">Time restriction</label>
        <input id="time_restriction" type="text"/>
        <label for="code_snippet">Code snippet</label>
        <textarea id="code_snippet"></textarea>
        <button id="send_snippet" type="submit" onclick="send(this)">Submit</button>
    </body>
</html>