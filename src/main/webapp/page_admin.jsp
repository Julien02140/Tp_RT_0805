<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="base_site.jsp" %>
<body>
    <h1>Liste utilisateurs</h1>
    <ul class="film-list">
        <c:forEach var="user" items="${users}">
            <li class="film-list-item" data-bs-toggle="tooltip" data-bs-placement="top" title="${film.title}">
                <a href="/rest/supprimer_utilisateur?user_pseudo=${user.pseudo}">
                    <button type="button" class="btn btn-primary">supprimer l'utilisateur</button>
                </a>
                <h5>${user.pseudo}</h5>
                <h5>${user.name}</h5>
                <h5>${user.age}</h5>
            </li>
        </c:forEach>
    </ul>
   
    <h1>Ajouter un nouveau film TMDB sur le site</h1>
    <form class="d-flex" method="POST" action="/rest/rechercheFilmTMDB">
        <input type="text" class="form-control me-2" id="recherche_TMDB" name="recherche_TMDB">
        <button type="submit"
        class="btn btn-primary">rechercher</button>
    </form>
</body>
</html>