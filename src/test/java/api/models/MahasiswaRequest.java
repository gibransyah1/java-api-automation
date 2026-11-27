package api.models;

public class MahasiswaRequest {
    private int id;
    private String nim, nama, email, alamat;

    // Constructor
    public MahasiswaRequest(){}

    public MahasiswaRequest(String nim, String nama, String email, String alamat){
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
    }
    public MahasiswaRequest(int id, String nim, String nama, String email, String alamat){
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
    }
    public MahasiswaRequest(int id){
        this.id = id;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId() {this.id = id; }
    public String getNim() { return nim; }
    public void setNim(String nim) {this.nim = nim; }
    public String getNama() { return nama; }
    public void setNama(String name) { this.nama = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }


}
