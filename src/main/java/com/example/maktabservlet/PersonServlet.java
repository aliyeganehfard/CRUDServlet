package com.example.maktabservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "PersonServlet", value = "/person")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var jsonString = new String(request.getInputStream().readAllBytes(),StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        var person = objectMapper.readValue(jsonString,PersonEntity.class);
        var repo = new Repository();
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(repo.findById(person.getId()));
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Clients will send us data in json format like {"firstName": "Milad", "lastName":"Amery"}
        // request.getInputStream().readAllBytes(); --> You Can read Http request body (inputStream) ONLY ONCE
        var jsonString = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        PersonEntity person = objectMapper.readValue(jsonString, PersonEntity.class);
        System.out.println(person);

        var repo = new Repository();
        repo.save(person);
        // We will read this String and convert it to a Person object.
        // Next we will save it to Database
        // after that we send HTTP_STATUS OK ( 200 ) to client to approve that person is saved.
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var jsonString = new String(req.getInputStream().readAllBytes(),StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        PersonEntity person = objectMapper.readValue(jsonString,PersonEntity.class);
        System.out.println(person);
        var repo = new Repository();
        repo.update(person);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var jsonString = new String(req.getInputStream().readAllBytes(),StandardCharsets.UTF_8);
        var objectMapper = new ObjectMapper();
        var person = objectMapper.readValue(jsonString,PersonEntity.class);
        var repo = new Repository();
        repo.delete(person);
    }
}
