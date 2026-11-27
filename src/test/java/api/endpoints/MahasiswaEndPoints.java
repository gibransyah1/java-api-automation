package api.endpoints;

import api.models.MahasiswaRequest;
import api.models.UserRequest;
import api.routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MahasiswaEndPoints {
    public static Response getAllMhs(){
        return given()
                .when()
                .get(Routes.get_all_mhs_url);
    }

    public static Response getMhs(int userId) {
        return given()
                .pathParam("id", userId)
                .when()
                .get(Routes.get_mhs_url);
    }

    public static Response createMhs(MahasiswaRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_mhs_url);
    }

    public static Response updateMhs(MahasiswaRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.update_mhs_url);
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

    public static Response deleteMhs(MahasiswaRequest payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .delete(Routes.delete_mhs_url);
    }
}
