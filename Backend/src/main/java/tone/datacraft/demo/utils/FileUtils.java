package tone.datacraft.demo.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileUtils {
    public static String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public static String convertCsvToJson(MultipartFile csvFile){
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try {
            List<Map<?, ?>> list;
            try (MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader()
                    .forType(Map.class)
                    .with(csvSchema)
                    .readValues(csvFile.getBytes())) {
                list = mappingIterator.readAll();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            // You can also map csv content to you own pojo
            String jsonPretty = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(list);
            return jsonPretty;

            // Do something with json string
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка парсинга csv файла");
        }
    }
}
