package tone.datacraft.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class DataFileAddRequest {
    private String dataTypeId;
    private MultipartFile file;
}

