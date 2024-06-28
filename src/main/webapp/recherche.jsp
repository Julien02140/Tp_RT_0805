<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@include file="base_site.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceuil</title>
</head>
<body>

    <p>film(s) correspondant(s) </p>
    <ul class="film-list">
        <!-- Boucle sur la liste des films populaires -->
        <c:forEach var="film" items="${films}">
            <li class="film-list-item" data-bs-toggle="tooltip" data-bs-placement="top" title="${film.title}">
                <h5>${film.title}</h5>
                <a href="/rest/description_film?film_id=${film.id}">
                    <img src="https://image.tmdb.org/t/p/w342/${film.posterPath}" alt="${film.title} Poster">
                </a>
            </li>
        </c:forEach>
    </ul>

</body>
</html>