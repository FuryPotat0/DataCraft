package tone.datacraft.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tone.datacraft.demo.document.DataTypeDocument;

@Getter
@Setter
@AllArgsConstructor
public class DataTypeDTO {
    private String id;

    private String name;

    public DataTypeDTO(String name) {
        this.name = name;
    }

    public static DataTypeDTO toDto(DataTypeDocument document) {
        return new DataTypeDTO(document.getId(), document.getName());
    }

    public static DataTypeDocument toDocument(DataTypeDTO document) {
        return new DataTypeDocument(document.getName());
    }
}
