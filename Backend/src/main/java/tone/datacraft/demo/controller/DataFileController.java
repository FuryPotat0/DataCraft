package tone.datacraft.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tone.datacraft.demo.request.DataFileAddRequest;
import tone.datacraft.demo.request.DataFileCreateAndAddRequest;
import tone.datacraft.demo.service.DataFileService;

@RestController
@RequestMapping("/data-file")
@RequiredArgsConstructor
public class DataFileController {
    private final DataFileService dataFileService;

    @PostMapping("/create-and-add")
    public void createAndAdd(DataFileCreateAndAddRequest request) {
        dataFileService.createAndAdd(request);
    }

    @PostMapping("/add")
    public void add(DataFileAddRequest request) {
        dataFileService.add(request);
    }
}

