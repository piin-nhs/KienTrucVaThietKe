package iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @PostMapping("/add")
    public ResponseEntity<String> addPerfume(@RequestBody PerfumeRequestDTO dto) {
        return ResponseEntity.ok(perfumeService.savePerfume(dto));
    }

    @GetMapping("/home")
    public ResponseEntity<List<PerfumeCore>> getHome() {
        return ResponseEntity.ok(perfumeService.getHomePagePerfumes());
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<PerfumeDetail> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(perfumeService.getPerfumeDetail(id));
    }
}