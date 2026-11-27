package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import api.models.MahasiswaRequest;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    public static Object[][] getJsonData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Membaca file JSON dan memetakannya ke array dari MahasiswaRequest
        MahasiswaRequest[] requests = objectMapper.readValue(new File(filePath), MahasiswaRequest[].class);

        Object[][] data = new Object[requests.length][1];
        for (int i = 0; i < requests.length; i++) {
            data[i][0] = requests[i];
        }
        return data;
    }
}