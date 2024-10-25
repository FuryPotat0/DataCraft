package tone.datacraft.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tone.datacraft.demo.request.DataFileAddRequest;
import tone.datacraft.demo.request.DataFileCreateAndAddRequest;

@Service
@RequiredArgsConstructor
public class DataFileService {
    private final DataTypeService dataTypeService;

    public String createAndAdd(DataFileCreateAndAddRequest request) {
        //TODO parse file

        return dataTypeService.create(request.getDataTypeName());
    }

    public void add(DataFileAddRequest request) {

    }
}

