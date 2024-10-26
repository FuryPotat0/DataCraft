package tone.datacraft.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tone.datacraft.demo.request.DataFilesRelationRequest;
import tone.datacraft.demo.service.DataFilesRelationService;

@RestController
@RequestMapping("/data-relation")
@RequiredArgsConstructor
public class DataRelationController {
    private final DataFilesRelationService dataFilesRelationService;

    @PostMapping("/add")
    public String addRelation(DataFilesRelationRequest request) {
        return dataFilesRelationService.add(request);
    }
}
