<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/tvTopBiger.png"/>"/>
    <c:choose>
        <c:when test="${empty film.name}">
            <title>Add</title>
        </c:when>
        <c:otherwise>
            <title>Edit</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<c:url value="/add" var="addUrl"/>
<c:url value="/edit" var="editUrl"/>
<form class="style" action="${empty film.name ? addUrl : editUrl}" name="film" method="POST">
    <c:choose>
        <c:when test="${!empty film.name}">
            <p class="heading">Edit film</p>
            <input type="hidden" name="id" value="${film.id}">
        </c:when>
        <c:otherwise>
            <p class="heading">Add new film</p>
        </c:otherwise>
    </c:choose>
    <p><input type="text" name="name" placeholder="name" value="${film.name}">
    <p><input type="text" name="description" placeholder="description" value="${film.description}">
    <p><input type="text" name="rate" placeholder="rate" value="${film.rate}">
    <p><input type="date" name="release" placeholder="release" value="${film.release}">
    <p><input type="text" name="img" placeholder="img" value="${film.img}">
    <p><input type="text" name="video" placeholder="video" value="${film.video}">

    <p>
        <c:set value="add" var="add"/>
        <c:set value="edit" var="edit"/>
        <input type="submit" value="${empty film.name ? add : edit}">
    </p>
    <p class="heading">${message}</p>
</form>
</body>
</html>
