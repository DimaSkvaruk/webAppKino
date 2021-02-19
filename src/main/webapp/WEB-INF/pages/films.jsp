<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>iKino</title>
    <link rel="shortcut icon" type="image/x-icon" href="resources/img/tvTopBiger.png">
    <%--<link rel="stylesheet" href="res/style.css" media="all">--%>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="header">
    <a href="<c:url value="/"/>">
        <img src="resources/img/plenka.png">
        <h1>iKino.UA</h1>
    </a>
</div>

<div class="topMenu">
    <a href="<c:url value="/bestFilms"/>">BEST</a>
    <a href="<c:url value="/newFilms"/>">NEW</a>
    <%--<a href="/ComingSoonServlet">COMING COON</a>--%>

    <a href="<c:url value="/add"/>">ADD</a>
    <a href="<c:url value="/"/>" style="float: right">Home</a>
    <a href="<c:url value="/about"/>" style="float: right">About</a>
    <a href="<c:url value="/about"/> " style="float: right">Contact us</a>
</div>


<div class="content">
    <div class="card">
        <c:forEach var="film" items="${filmsList}" varStatus="i">
            <h3>${film.name}</h3>
            <div class="inside">
                    <%--<div class="title">--%>
                        <div id="logo"><img src="${film.img}" alt="logo"></div>
                        <div class="video">${film.video}</div>
                        <div class="left-side">
                            <div class="rating" style="background-color: chartreuse">${film.rate} </div>
                            <div class="release" style="background-color: #068bac">${film.release}</div>
                        </div>
                        <div class="description" style="text-align: center">
                            <div id="profilm">Description</div>
                            <div class="infotext" itemprop="description" style="position: relative;">
                                <div class="contentWrapper">${film.description}</div>
                            </div>
                        </div>
                        <%--<div class="action">--%>
                            <%--<a href="/edit/${film.id}">--%>
                                <%--<span class="icon icon-edit">edit</span>--%>
                            <%--</a>--%>
                            <%--<a href="/delete/${film.id}">--%>
                                <%--<span class="icon icon-delete">delete</span>--%>
                            <%--</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
            </div>
        </c:forEach>
    </div>
    <div class="pageNum" style="text-align: center">
        <div class="inside" style="text-align: center">
                <c:if test="${pagesCount > 1}">
                    <c:set value="disabled" var="disabled"/>
                    <c:set value="" var="active"/>
                    <c:url value="/" var="url">
                        <c:param name="page" value="1"/>
                    </c:url>
                    <a class="${page == 1 ? disabled : active}" href="${url}">
                        &nbsp<span class="icon icon-first"><<</span>&nbsp
                    </a>
                    <c:url value="/" var="url">
                        <c:param name="page" value="${page - 1}"/>
                    </c:url>
                    <a class="${page == 1 ? disabled : active}" href="${url}">
                        &nbsp<span class="icon icon-prev"><</span>&nbsp
                    </a>

                    <c:if test="${pagesCount <= 5}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${pagesCount}"/>
                    </c:if>
                    <c:if test="${pagesCount > 5}">
                        <c:choose>
                            <c:when test="${page < 3}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="5"/>
                            </c:when>
                            <c:when test="${page > pagesCount - 2}">
                                <c:set var="begin" value="${pagesCount - 4}"/>
                                <c:set var="end" value="${pagesCount}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="begin" value="${page - 2}"/>
                                <c:set var="end" value="${page + 2}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                        <c:url value="/" var="url">
                            <c:param name="page" value="${i.index}"/>
                        </c:url>
                        <c:set value="current-page" var="current"/>
                        <c:set value="" var="perspective"/>
                        <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                    </c:forEach>

                    <c:url value="/" var="url">
                        <c:param name="page" value="${page + 1}"/>
                    </c:url>
                    <a class="${page == pagesCount ? disabled : active}" href="${url}">
                        &nbsp<span class="icon icon-next">></span>&nbsp
                    </a>
                    <c:url value="/" var="url">
                        <c:param name="page" value="${pagesCount}"/>
                    </c:url>
                    <a class="${page == pagesCount ? disabled : active}" href="${url}">
                        &nbsp<span class="icon icon-last">>></span>&nbsp
                    </a>
                </c:if>
        </div>
    </div>
</div>


<div class="footer">
    <div class="links">
        <aside>
            <a href="<c:url value="/"/> ">Home</a>
            <a href="<c:url value="/about"/>">About</a>
            <a href="<c:url value="/about"/>">Contact us</a>
        </aside>

    </div>
</div>

</body>
</html>