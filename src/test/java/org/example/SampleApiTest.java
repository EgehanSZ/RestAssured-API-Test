package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleApiTest {

    @BeforeClass
    public static void setup() {
        // Temel URI tanımı
        RestAssured.baseURI = "http://localhost:3000";
    }

    // -------------------- GET İsteği Testi -------------------- //
    @Test
    public void testGetPostById() {
        String validId = "681d349ce90079ab5440875f"; // Buraya veritabanındaki gerçek bir _id yazılmalı

        Response response =
                given().
                        baseUri("http://localhost:3000").
                        port(3000). // Projen hangi portta çalışıyorsa burayı ayarla
                        contentType(ContentType.JSON).
                        when().
                        get("/listings/" + validId).
                        then().
                        assertThat().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        body("_id", equalTo(validId)). // ObjectId eşleşmesi için kontrol
                        body("baslik", notNullValue()). // Örnek bir alan
                        extract().response();

        long responseTime = response.time();
        String responseBody = response.getBody().asPrettyString();

        System.out.println("GET /listings/" + validId + " isteği süresi: " + responseTime + " ms");
        System.out.println("Yanıt:\n" + responseBody);

        assert responseTime < 1000 : "GET isteği 1 saniyeden uzun sürdü!";
        assert !responseBody.isEmpty() : "Yanıt boş geldi!";
    }

    // -------------------- POST İsteği Testi -------------------- //
    @Test
    public void testCreatePost() {
        String requestBody = """
        {
          "kullaniciId": "38443790974",
          "sifre": "ege"
        
        }
        """;

        Response response =
                given().
                        baseUri("http://localhost:3000").
                        port(3000). // Projen hangi portta çalışıyorsa burayı güncelle
                        contentType(ContentType.JSON).
                        body(requestBody).
                        when().
                        post("/login").
                        then().
                        assertThat().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        body("message", equalTo("Giriş Başarılı")).
                        body("token", notNullValue()).
                        extract().response();

        long responseTime = response.time();
        String responseBody = response.getBody().asPrettyString();

        System.out.println("POST /login isteği süresi: " + responseTime + " ms");
        System.out.println("Yanıt:\n" + responseBody);

        assert responseTime < 1000 : "İstek süresi 1 saniyeyi geçti!";
        assert !responseBody.isEmpty() : "Gelen yanıt boş!";
    }

}
