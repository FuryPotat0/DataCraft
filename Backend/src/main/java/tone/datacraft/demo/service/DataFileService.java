package tone.datacraft.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tone.datacraft.demo.document.DataTypeDocument;
import tone.datacraft.demo.request.DataFileAddRequest;
import tone.datacraft.demo.request.DataFileCreateAndAddRequest;
import tone.datacraft.demo.utils.FileUtils;

import java.util.Optional;

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
        String fileContent = FileUtils.getFileContent(file);
        saveFile(fileContent, dataTypeId);
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

