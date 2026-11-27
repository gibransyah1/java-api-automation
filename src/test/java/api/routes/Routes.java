package api.routes;

public class Routes {

    // API eksternal
    public static String base_url = "https://reqres.in";

    // API Express.js
    // Bisa diubah melalui parameter Maven -DbaseUrl
    public static String base_url_mhs =
            System.getProperty(
                    "baseUrl",
                    "http://localhost:8081"
            );

    // User Module Endpoints
    public static String get_all_user_url = base_url + "/api/users";
    public static String get_user_url = base_url + "/api/users/{id}";
    public static String post_user_url = base_url + "/api/users";
    public static String update_user_url = base_url + "/api/users/{id}";
    public static String delete_user_url = base_url + "/api/users/{id}";

    // Mahasiswa Module Endpoints
    public static String get_all_mhs_url = base_url_mhs + "/mahasiswa";
    public static String get_mhs_url = base_url_mhs + "/mahasiswa/{id}";
    public static String post_mhs_url = base_url_mhs + "/mahasiswa/add";
    public static String update_mhs_url = base_url_mhs + "/mahasiswa/edit";
    public static String delete_mhs_url = base_url_mhs + "/mahasiswa/delete";
}
