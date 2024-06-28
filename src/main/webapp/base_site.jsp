<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <nav class="navbar navbar-expand-sm navbar-light custom-bg">
        <a href="#" 
            class="navbar-brand mb-0 h1">
            MyMovie
        </a>
        <div class="collapse navbar-collapse"
            id="navbar-nav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a href="/rest/films_populaires" class="nav-link">Home</a>
                </li>
                <li class="nav-item active">
                    <a href="/rest/videotheque" class="nav-link">Ma videotheque</a>
                </li>
                <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                  <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      genres
                      </a>
                      <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=28">Action</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=12">Aventure</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=16">Animation</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=35">Comédie</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=80">Crime</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=99">Documentaire</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=18">Drame</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=10751">Familial</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=14">Fantastique</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=36">Histoire</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=27">Horreur</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=10402">Musique</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=9648">Mystère</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=10749">Romance</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=878">Science-Fiction</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=10770">Téléfilm</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=53">Thriller</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=10752">Guerre</a></li>
                        <li><a class="dropdown-item" href="/rest/recherche_genre?genre=37">Western</a></li>
                      </ul>
                    <li class="nav-item" id="admin-link" style="display: none;">
                      <a href="../page_admin.html" class="nav-link">Page Admin</a>
                    </li>
                  </ul>
                </div>
                <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                    <ul class="navbar-nav">
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Mon compte
                        </a>
                        <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarDarkDropdownMenuLink">
                          <li><a class="dropdown-item" href="/rest/deconnexion">Déconnexion</a></li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                <c:if test="${isAdmin == true}">
                  <!-- Display the additional button for the admin -->
                  <li class="nav-item active">
                    <a href="/rest/admin" class="nav-link">Admin</a>
                  </li>
                </c:if>
            </ul> 
        </div>
        <form class="d-flex" method="GET" action="/rest/rechercher_film_mot">
            <input type="text" class="form-control me-2" id="mot" name="mot">
            <button type="submit"
            class="btn btn-primary">rechercher</button>
        </form>
      </nav>
</body>
</html>