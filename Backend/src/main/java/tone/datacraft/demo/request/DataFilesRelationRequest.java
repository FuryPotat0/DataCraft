package tone.datacraft.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataFilesRelationRequest {
    private String dataTypeSourceId;

    private String fieldSourceName;

    private String dataTypeDestinationId;

    private String fieldDestinationName;
}
