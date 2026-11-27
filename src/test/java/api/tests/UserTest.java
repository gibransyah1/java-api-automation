package api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Api Testing Demo")
@Feature("User Management API")
public class UserTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test(priority = 1)
    @Description("Mencoba membaca semua data user")
    public void testGetAllUsers(){
        given()
                .when()
                .get("/api/users")
                .then()
//                .log().body()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)))
                .body("data.id", hasItems(1,2,3));
    }
    @Test(priority = 2)
    @Description("Mencoba membaca data user dengan id 2")
    public void testGetSingleUser(){

        given()
                .when()
                .get("/api/users/2")
                .then() //dimulainya assertion/pengecekan
//                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test(priority = 3)
    @Description("Verifikasi berhasil menambah data user baru")
    public void testCreateUser() {
        // Variabel Input Data
        String nameInput = "Gibran";
        String jobInput = "QA Automation Engineer";

        // Menyusun Request Body menggunakan Map
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", nameInput);
        requestBody.put("job", jobInput);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody) // Rest Assured & Jackson otomatis konversi Map ini ke format JSON
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo(nameInput))
                .body("job", equalTo(jobInput))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test(priority = 4)
    @Description("Verifikasi berhasil memperbarui seluruh data user (PUT)")
    public void testUpdateUserPut() {
        // Variabel Input Data
        String updatedName = "Gibran Updated";
        String updatedJob = "Senior QA Automation";

        // Menyusun Request Body menggunakan Map
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", updatedName);
        requestBody.put("job", updatedJob);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedName))
                .body("job", equalTo(updatedJob))
                .body("updatedAt", notNullValue());
    }

    @Test(priority = 5)
    @Description("Verifikasi berhasil menghapus data user")
    public void testDeleteUser() {
        given()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }


}
