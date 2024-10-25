package tone.datacraft.demo.service;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import tone.datacraft.demo.repository.DataTypeRepository;

@Service
@RequiredArgsConstructor
public class DataTypeService {
    private final DataTypeRepository dataTypeRepository;

    public Long create(String name) {
        //todo
    }

    //todo getAll DataTypeDTO
}

