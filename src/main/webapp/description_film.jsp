<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="base_site.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Description</title>
    
</head>
<body>
        <div class="film-container">
            <h4>${film.title}</h4>

            <img src="https://image.tmdb.org/t/p/w342/${ film.posterPath }" alt="${ film.title } Poster">

            <div class="film-info">
                <a href="/rest/ajout_videotheque?film_id=${film.id}" class="btn btn-primary">Ajouter le film</a>
                </br>
                </br>
                <p>Date de sortie : ${film.releaseDate}</p>
                <p>Vote moyen: ${ film.moyenne }</p>
                <p>Nombre de votes : ${ film.nbVote }</p>
                <p>Synopsis: ${ film.overview }</p>
            </div>

            <h4>Noter le film</h4>
            <p> Vous pourrez modifier votre note à tout moment</p>
            <form method="POST" action="/ajout_note">
                <label for="rating">Note : </label>
                <input type="number" id="note" name="note" min="1" max="10" required>
                
                <input type="hidden" id="id" name="id" value="${ film.id }">
                <button class="btn btn-primary" type="submit">Soumettre</button>
            </form>
        </div>

</body>
</html>