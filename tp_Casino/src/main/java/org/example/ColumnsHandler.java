package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ColumnsHandler {

    //Chemin à changer
    private static final String FILE_PATH = "C:\\Users\\ouadi\\OneDrive\\Bureau\\tp_Casino\\src\\main\\java\\org\\example\\colonnes.json";

        public List<List<String>> deserializeColumns() {
            try (FileReader reader = new FileReader(FILE_PATH)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, List.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }



