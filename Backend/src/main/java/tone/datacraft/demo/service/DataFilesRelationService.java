package tone.datacraft.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tone.datacraft.demo.document.DataRelationDocument;
import tone.datacraft.demo.repository.DataRelationRepository;
import tone.datacraft.demo.request.DataFilesRelationRequest;

@Service
@RequiredArgsConstructor
public class DataFilesRelationService {
    private final DataRelationRepository dataRelationRepository;

    public String save(DataFilesRelationRequest request) {
        //TODO exists check

        DataRelationDocument dataRelationDocument = new DataRelationDocument(
                request.getDataTypeSourceId(),
                request.getFieldSourceName(),
                request.getDataTypeDestinationId(),
                request.getFieldDestinationName()
        );

        return dataRelationRepository.save(dataRelationDocument).getId();
    }
}
