<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Большой теннис</title>
    <link href="<%=request.getContextPath()%>/style/style.css" rel="stylesheet">
</head>
<header>
    <div class="container bc-lightgray">
        <div class="table-header p-20">
            <span class="h1">Большой теннис</span>
        </div>
    </div>
</header>
<section>
    <div class="container">
        <div class="main">
            <a class="start-link" href="new_match">Начать новый матч</a>
            <a class="start-link" href="matches">Завершенные матчи</a>
        </div>
    </div>
</section>
</html>
