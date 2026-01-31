package iuh.fit.controller;

import iuh.fit.adapter.JsonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/send-json")
    public String sendJson(@RequestBody String json) throws Exception {
        return jsonService.sendJson(json);
    }
}

