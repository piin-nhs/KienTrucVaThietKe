package iuh.fit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Transactional
    public String savePerfume(PerfumeRequestDTO dto) {
        PerfumeCore core = new PerfumeCore();
        core.setName(dto.getName());
        core.setBrand(dto.getBrand());
        core.setPrice(dto.getPrice());

        PerfumeDetail detail = new PerfumeDetail();
        detail.setDescription(dto.getDescription());
        detail.setIngredients(dto.getIngredients());

        core.setDetail(detail);
        detail.setPerfumeCore(core);

        perfumeRepository.save(core);
        return "Đã lưu thành công sản phẩm: " + core.getName();
    }

    public List<PerfumeCore> getHomePagePerfumes() {
        return perfumeRepository.findAll();
    }

    public PerfumeDetail getPerfumeDetail(Long id) {
        PerfumeCore core = perfumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        return core.getDetail();
    }
}