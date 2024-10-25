package tone.datacraft.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tone.datacraft.demo.document.DataTypeDocument;
import tone.datacraft.demo.dto.DataTypeDTO;
import tone.datacraft.demo.repository.DataTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTypeService {
    private final DataTypeRepository dataTypeRepository;

    public String create(String name) {
        if (dataTypeRepository.existsByName(name)) {
            throw new RuntimeException("Тип документа %s уже существует в коллекции");
        }
        return dataTypeRepository.save(new DataTypeDocument(name)).getId();
    }

    public List<DataTypeDTO> getAllDataTypes() {
        List<DataTypeDocument> dataTypeDocuments = dataTypeRepository.findAll();
        List<DataTypeDTO> dataTypeDTOS = new ArrayList<>(dataTypeDocuments.size());
        for (DataTypeDocument document : dataTypeDocuments) {
            dataTypeDTOS.add(DataTypeDTO.toDto(document));
        }
        return dataTypeDTOS;
    }
}

