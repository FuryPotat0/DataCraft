package tone.datacraft.demo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data_type")
@Getter
@Setter
@AllArgsConstructor
public class DataTypeDocument {
    @Id
    private String id;

    private String name;

    public DataTypeDocument(String name) {
        this.name = name;
    }
}

