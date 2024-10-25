package tone.datacraft.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class DataFileCreateAndAddRequest {
    private String dataTypeName;
    private MultipartFile file;
}

