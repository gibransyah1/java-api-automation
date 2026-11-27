package api.endpoints;

import api.models.UserRequest;
import api.routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static Response getAllUser(){
        return given()
                .when()
                .get(Routes.get_all_user_url);
    }

    public static Response getUser(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get(Routes.get_user_url);
    }

    public static Response createUser(UserRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_user_url);
    }

    public static Response updateUser(int userId, UserRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", userId)
                .body(payload)
                .when()
                .put(Routes.update_user_url);
    }

    public static Response patchUser(int userId, UserRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", userId)
                .body(payload)
                .when()
                .patch(Routes.update_user_url);
    }

    public static Response deleteUser(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .delete(Routes.delete_user_url);
    }
}
