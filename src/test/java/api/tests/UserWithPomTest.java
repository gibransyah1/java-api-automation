package api.tests;

import api.endpoints.MahasiswaEndPoints;
import api.models.MahasiswaRequest;
import api.utils.ExcelUtils;
import api.utils.JsonUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

@Epic("API Testing Framework")
@Feature("User Management Service")
public class UserWithPomTest {

    // DataProvider membaca file JSON
    @DataProvider(name = "jsonUserData")
    public Object[][] provideJsonData() throws IOException {
        String jsonPath = "src/test/resources/mahasiswa_data.json";
        return JsonUtils.getJsonData(jsonPath);
    }

    // Mengambil data secara dinamis dari file Excel
    @DataProvider(name = "excelUserData")
    public Object[][] getDataFromExcel() throws IOException {
        // Sesuaikan jalur path file Excel Anda di proyek
        String excelPath = "src/test/resources/testdata.xlsx";
        return ExcelUtils.getExcelData(excelPath, "Sheet1");
    }

    // Menyediakan sumber data (DataProvider) pengganti looping manual
    @DataProvider(name = "multiUserData")
    public Object[][] getTestData() {
        return new Object[][] {
                {"055142331", "Gibran", "gibranotherside1@gmail.com", "Jl. nakul1"},
                {"055142332", "Gibran 2", "gibranotherside2@gmail.com", "Jl. nakul2"},
                {"055142333", "Gibran 3", "gibranotherside3@gmail.com", "Jl. nakul3"}
        };
    }

//    @Test(priority = 1)
//    @Description("Verifikasi berhasil mendapatkan semua data user")
//    public void testGetAllUser(){
//        Response response = UserEndpoints.getAllUser();
//        response.then()
//                .statusCode(200)
//                .body("data", hasSize(greaterThan(0)))
//                .body("data.id", hasItems(1,2,3));
//    }

    @Test(priority = 1)
    @Description("Verifikasi berhasil mendapatkan semua data mahasiswa")
    public void testGetAllMhs(){
        Response response = MahasiswaEndPoints.getAllMhs();
        response.then()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)));
//                .body("data.id", hasItems(1,2,3));
    }

//    @Test(priority = 2)
//    @Description("Verifikasi berhasil mendapatkan detail data user")
//    public void testGetUser() {
//        Response response = UserEndpoints.getUser(2);
//
//        response.then()
//                .statusCode(200)
//                .body("data.id", equalTo(2))
//                .body("data.first_name", equalTo("Janet"));
//    }

    @Test(priority = 2)
    @Description("Verifikasi berhasil mendapatkan detail data user")
    public void testGetMhs() {
        Response response = MahasiswaEndPoints.getMhs(1);

        response.then()
                .statusCode(200);
//                .body("data.id", equalTo(2))
//                .body("data.first_name", equalTo("Janet"));
    }

//    @Test(priority = 3)
//    @Description("Verifikasi berhasil menambah data user baru")
//    public void testCreateUser() {
//        UserRequest userPayload = new UserRequest("Gibran", "QA Automation Engineer");
//
//        Response response = UserEndpoints.createUser(userPayload);
//
//        response.then()
//                .statusCode(201)
//                .body("name", equalTo(userPayload.getName()))
//                .body("job", equalTo(userPayload.getJob()))
//                .body("id", notNullValue());
//    }

    @Test(priority = 3)
    @Description("Verifikasi berhasil menambah data mahasiswa baru")
    public void testCreateMhs() {
        MahasiswaRequest mahasiswaPayload = new MahasiswaRequest("055142330", "Nazwa Yuss", "Nazwa@gmail.com", "Jl. Nakul");

        Response response = MahasiswaEndPoints.createMhs(mahasiswaPayload);

        response.then()
                .statusCode(201);
//                .body("data.nim", equalTo(mahasiswaPayload.getNim()))
//                .body("data.nama", equalTo(mahasiswaPayload.getNama()))
//                .body("id", notNullValue());
    }

//    @Test(priority = 4)
//    @Description("Verifikasi berhasil memperbarui seluruh data user (PUT)")
//    public void testUpdateUser() {
//        UserRequest userPayload = new UserRequest("Gibran Updated", "Senior QA Engineer");
//
//        Response response = UserEndpoints.updateUser(2, userPayload);
//
//        response.then()
//                .statusCode(200)
//                .body("name", equalTo(userPayload.getName()))
//                .body("job", equalTo(userPayload.getJob()));
//    }

    @Test(priority = 4)
    @Description("Verifikasi berhasil memperbarui seluruh data user (PUT)")
    public void testUpdateMhs() {
        MahasiswaRequest mahasiswaPayload = new MahasiswaRequest(1,"055142329","Gibran Updated", "gibranotherside@gmail.com", "Jl. nakul");

        Response response = MahasiswaEndPoints.updateMhs(mahasiswaPayload);

        response.then()
                .statusCode(200);
//                .body("name", equalTo(userPayload.getName()))
//                .body("job", equalTo(userPayload.getJob()));
    }

//    @Test(priority = 5)
//    @Description("Verifikasi berhasil menghapus data user")
//    public void testDeleteUser() {
//        Response response = UserEndpoints.deleteUser(2);
//
//        response.then()
//                .statusCode(204);
//    }

    @Test(priority = 5)
    @Description("Verifikasi berhasil menghapus data user")
    public void testDeleteMhs() {
        MahasiswaRequest mahasiswaPayload = new MahasiswaRequest(1);
        Response response = MahasiswaEndPoints.deleteMhs(mahasiswaPayload);

        response.then()
                .statusCode(204);
    }

    @Test(priority = 6)
    @Description("Simulasi Data-Driven Testing menggunakan perulangan dan kondisi untuk multiple user")
    public void testCreateMultipleUsersWithLoop() {

        // 1. Simulasi Kumpulan Data (Bisa dibayangkan ini data dari Excel / Database)
        // Format: [Nama, Pekerjaan]
        Object[][] dataUsers = {
                {"055142331","Gibran 1", "gibranotherside1@gmail.com", "Jl. nakul1"},
                {"055142332","Gibran 2", "gibranotherside2@gmail.com", "Jl. nakul2"},
                {"055142333","Gibran 3", "gibranotherside3@gmail.com", "Jl. nakul3"}
        };

        // 2. Perulangan (Looping) untuk mengeksekusi tiap baris data secara bergantian
        for (int i = 0; i < dataUsers.length; i++) {
            String currentNim = (String) dataUsers[i][0];
            String currentNama = (String) dataUsers[i][1];
            String currentEmail = (String) dataUsers[i][2];
            String currentAlamat = (String) dataUsers[i][3];

            System.out.println("Sedang mengetes untuk user: " + currentNama);

            // Buat payload dengan data dinamis dari perulangan
            MahasiswaRequest mahasiswaPayload = new MahasiswaRequest(currentNim ,currentNama, currentEmail, currentAlamat);

            // Eksekusi API POST
            Response response = MahasiswaEndPoints.createMhs(mahasiswaPayload);

            // 3. Kondisi (Conditional Logic) untuk validasi dinamis
            if (currentNama.equals("Gibran")) {
                // Skenario khusus jika nama tertentu
                response.then()
                        .statusCode(201);
//                        .body("name", equalTo(currentName))
//                        .body("job", equalTo(currentJob));
            } else {
                // Skenario umum untuk data lainnya
                response.then()
                        .statusCode(201);
//                        .body("name", equalTo(currentName));
            }
        }
    }

    // TestNG otomatis menjalankan method ini 3 kali sesuai jumlah baris data di atas
    @Test(priority = 7, dataProvider = "multiUserData")
    @Description("Simulasi Data-Driven Testing menggunakan DataProvider TestNG untuk multiple user")
    public void testCreateMultipleUsers(String currentNim, String currentNama, String currentEmail, String currentAlamat) {

        System.out.println("Sedang mengetes untuk user: " + currentNama + " (NIM: " + currentNim + ")");

        // Buat payload dengan data dinamis dari parameter TestNG
        MahasiswaRequest mahasiswaPayload = new MahasiswaRequest(currentNim, currentNama, currentEmail, currentAlamat);

        // Eksekusi API POST
        Response response = MahasiswaEndPoints.createMhs(mahasiswaPayload);

        // 3. Kondisi (Conditional Logic) untuk validasi dinamis berdasarkan nama
        if (currentNama.equals("Gibran")) {
            // Skenario khusus jika nama "Gibran" (persis)
            response.then()
                    .statusCode(201);
            // Tambahkan assertion body jika diperlukan, contoh:
            // .body("nama", equalTo(currentNama));
        } else {
            // Skenario umum untuk data lainnya
            response.then()
                    .statusCode(201);
            // .body("nama", equalTo(currentNama));
        }
    }

    @Test(priority = 8, dataProvider = "excelUserData")
    @Description("Otomasi Create User menggunakan data dari Excel secara berulang")
    public void testCreateUserFromExcel(String nim, String nama, String email, String alamat) {
        System.out.println("Menjalankan test untuk: " + nim + " - " + nama + " - " + email + " - " + alamat);

        // Buat payload dari data Excel
        MahasiswaRequest mahasiswaPayload = new MahasiswaRequest(nim, nama, email, alamat);

        // Eksekusi API
        Response response = MahasiswaEndPoints.createMhs(mahasiswaPayload);

        // Validasi response
        response.then()
                .statusCode(201);
//                .body("name", equalTo(name))
//                .body("job", equalTo(job))
//                .body("id", notNullValue());
    }

    @Test(priority = 9, dataProvider = "jsonUserData")
    @Description("Otomasi Create User menggunakan data dari file JSON secara berulang")
    public void testCreateUserFromJson(MahasiswaRequest mahasiswaPayload) {

        System.out.println("Sedang mengetes untuk user: " + mahasiswaPayload.getNama() + " (NIM: " + mahasiswaPayload.getNim() + ")");

        // Eksekusi API POST
        Response response = MahasiswaEndPoints.createMhs(mahasiswaPayload);

        // Validasi Kondisional / Standard
        if (mahasiswaPayload.getNama().equals("Gibran")) {
            response.then().statusCode(201);
        } else {
            response.then().statusCode(201);
        }
    }

}
