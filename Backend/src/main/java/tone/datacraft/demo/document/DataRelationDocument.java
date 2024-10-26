package tone.datacraft.demo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data_relation")
@Getter
@Setter
@AllArgsConstructor
public class DataRelationDocument {
    @Id
    private String id;

    private String dataTypeSourceId;

    private String fieldSourceName;

    private String dataTypeDestinationId;

    private String fieldDestinationName;

    public DataRelationDocument(
            String dataTypeSourceId,
            String fieldSourceName,
            String dataTypeDestinationId,
            String fieldDestinationName
            ) {
        this.dataTypeSourceId = dataTypeSourceId;
        this.fieldSourceName = fieldSourceName;
        this.dataTypeDestinationId = dataTypeDestinationId;
        this.fieldDestinationName = fieldDestinationName;
    }
}
