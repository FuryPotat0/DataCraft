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
import tone.datacraft.demo.document.DataTypeDocument;
import tone.datacraft.demo.request.DataFileAddRequest;
import tone.datacraft.demo.request.DataFileCreateAndAddRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static tone.datacraft.demo.utils.FileUtils.*;

@Service
@RequiredArgsConstructor
public class DataFileService {
    private final DataTypeService dataTypeService;
    private final MongoTemplate mongoTemplate;

    public void createAndAdd(DataFileCreateAndAddRequest request) {
        String dataTypeId = dataTypeService.create(request.getDataTypeName());
        saveMultipartFile(request.getFile(), dataTypeId);
    }

    public void add(DataFileAddRequest request) {
        Optional<DataTypeDocument> typeDocument = dataTypeService.findById(request.getDataTypeId());
        DataTypeDocument document = typeDocument.orElseThrow(
                () -> new RuntimeException(
                        "Не найден тип документа с id: %s".formatted(request.getDataTypeId())
                )
        );
        saveMultipartFile(request.getFile(), document.getId());
    }

    private void saveMultipartFile(MultipartFile file, String dataTypeId) {
        if (Objects.equals(getExtension(file.getOriginalFilename()), "json")) {
            try {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);
                saveFile(content, dataTypeId);
            }
            catch (IOException e) {
                throw new RuntimeException("Ошибка парсинга json файла");
            }
        }
        else if (Objects.equals(getExtension(file.getOriginalFilename()), "csv")) {
            String content = convertCsvToJson(file);
            saveFile(content, dataTypeId);
        }
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

