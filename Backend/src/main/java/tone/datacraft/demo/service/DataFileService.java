package tone.datacraft.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tone.datacraft.demo.request.DataFileAddRequest;
import tone.datacraft.demo.request.DataFileCreateAndAddRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DataFileService {
    private final DataTypeService dataTypeService;
    private final MongoTemplate mongoTemplate;

    public void createAndAdd(DataFileCreateAndAddRequest request) {
        String dataTypeId = dataTypeService.create(request.getDataTypeName());
        if (Objects.equals(getExtension(request.getFile().getOriginalFilename()), "json")) {
            try {
                String content = new String(request.getFile().getBytes(), StandardCharsets.UTF_8);
                saveFile(content, dataTypeId);
            }
            catch (IOException e) {
                throw new RuntimeException("Ошибка парсинга json файла");
            }
        }
        else if (Objects.equals(getExtension(request.getFile().getOriginalFilename()), "csv")) {
            String content = convertCsvToJson(request.getFile());
            saveFile(content, dataTypeId);
        }
    }

    private String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public String convertCsvToJson(MultipartFile csvFile){
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

    public void add(DataFileAddRequest request) {
        //todo добавить добавление
    }

    private void saveFile(String jsonString, String dataTypeId) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Парсим JSON в JsonNode
            JsonNode jsonArray = objectMapper.readTree(jsonString);

            // Создаем список для имен
            Document doc;
            // Итерация по элементам массива
            for (JsonNode node : jsonArray) {
                //DBObject dbObject = (DBObject) JSON.parse(node.asText());
                doc = Document.parse(node.toString());
                doc.put("dataTypeId", dataTypeId);
                mongoTemplate.insert(doc, "data_value");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

