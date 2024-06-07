package org;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import java.text.Normalizer;
import java.util.Base64;


public class api_TMDB {
    private static final String api_key_TMDB = "8770fea03d8b0d550c4b50be1656d5cb";

    public api_TMDB(){

    }

    public void ajouter_film_xml(Film ajout_film){
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_Films.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("films.xml");
            // OutputStream outputStream = new FileOutputStream("films.xml");
            // Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            // BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Liste_Films films;

            if (file.exists()) {
                films = (Liste_Films) unmarshaller.unmarshal(file);
            } else {
                films = new Liste_Films();
            }

            films.getListeFilms().add(ajout_film);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(films, new FileWriter(file));

            System.out.println("Film ajoute");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du film");
        }
    }

    public void ajouter_genre_xml(Genre ajout_genre){
        try {
            JAXBContext context = JAXBContext.newInstance(Liste_genres.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("genres.xml");
            // OutputStream outputStream = new FileOutputStream("films.xml");
            // Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            // BufferedWriter bufferedWriter = new BufferedWriter(writer);

            Liste_genres genres;

            if (file.exists()) {
                genres = (Liste_genres) unmarshaller.unmarshal(file);
            } else {
                genres = new Liste_genres();
            }

            genres.getListeGenres().add(ajout_genre);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(genres, new FileWriter(file));

            System.out.println("Genre ajoute");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du genre");
        }
    }

    public void getFilm(){
        String base_url = "https://api.themoviedb.org/3/discover/movie?api_key=";
        String language = "fr";
        int minVoteAverage = 7;
        int minVoteCount = 100;
        String complete_url;

        try{
            for(int i=1;i<5;i++){
                complete_url = base_url + api_key_TMDB + "&language=" + language + "&vote_average.gte=" + minVoteAverage + "&vote_count.gte=" + minVoteCount + "&page=" + i;
                
                URL url = new URL(complete_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                
                    // Parse JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray results = jsonResponse.getJSONArray("results");
                    for (int j = 0; j < results.length(); j++) {
                        JSONObject movie = results.getJSONObject(j);
                        String title = movie.getString("original_title");

                        //problème d'encodage utf-8 donc on convertit en base64
                        String title64 = Base64.getEncoder().encodeToString(title.getBytes());
                        byte[] decodedBytes = Base64.getDecoder().decode(title64);
                        String title_decode = new String(decodedBytes);


                        String release_date = movie.getString("release_date");
                        int vote_average = movie.getInt("vote_average");
                        int vote_count = movie.getInt("vote_count");
                        int id = movie.getInt("id");

                        String moyenne = Integer.toString(vote_average);
                        String nb_vote = Integer.toString(vote_count);
                        String id_string = Integer.toString(id);

                        String overview = movie.getString("overview");
                        String poster_path = movie.getString("poster_path");
                        String backdrop_path = movie.getString("backdrop_path");


                        //problème d'encodage utf-8 donc on convertit en base64
                        String overview64 = Base64.getEncoder().encodeToString(overview.getBytes());
                        byte[] decodedBytes_overview = Base64.getDecoder().decode(overview64);
                        String overview_decode = new String(decodedBytes_overview);

                        System.out.println("Titre du film : " + title_decode);
                        System.out.println("release_date : " + release_date);
                        System.out.println("moyenne : " + vote_average);
                        System.out.println("nb vote : " + vote_count);
                        System.out.println("id : " + id);
                        System.out.println("overview : " + overview_decode);
                        
                        //decode à chaque fois le titre et overview
                        Film film = new Film(title64, release_date,moyenne,nb_vote, id_string, overview64,poster_path,backdrop_path);
                        ajouter_film_xml(film);
                        System.out.println("Ajout du film dans le fichier xml");

                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get_genre_film(){
        String string_url = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + api_key_TMDB + "&language=fr-FR";
        try{
            URL url = new URL(string_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray genres = jsonResponse.getJSONArray("genres");
                for (int j = 0; j < genres.length(); j++) {
                    JSONObject genre = genres.getJSONObject(j);
                    int id = genre.getInt("id");
                    String name = genre.getString("name");

                    String id_string = Integer.toString(id);

                    //problème d'encodage utf-8 donc on convertit en base64
                    String name64 = Base64.getEncoder().encodeToString(name.getBytes());
                    byte[] decodedBytes = Base64.getDecoder().decode(name64);
                    String name_decode = new String(decodedBytes);

                    System.out.println("id : " + id_string);
                    System.out.println("name : " + name_decode);

                    Genre genre_object = new Genre(id_string,name64);
                    ajouter_genre_xml(genre_object);
                    System.out.println("Ajout des genres de l'api TMDB");

                }


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}