package platform.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.Code;
import platform.repositories.CodeRepository;
import platform.services.CodeService;
import platform.utils.DateTimeHelper;

import jakarta.persistence.EntityNotFoundException;
import java.util.*;

@RestController
public class CodeRestController {
    private final CodeRepository codeRepository;

    CodeRestController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Map<String, String>> getCode(@PathVariable("id") long id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Optional<Code> code = codeRepository.findById(id);

        if(code.isEmpty()) return ResponseEntity.notFound().build();

        Map<String, String> map = new HashMap<>();
        map.put("code", code.get().getCode());

        String strDate = new DateTimeHelper().dateToString(code.get().getDate());
        map.put("date", strDate);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<ArrayList<Map<String, String>>> getLatest() {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");

            List<Code> codes = codeRepository.findFirst10ByOrderByDateDesc();
            ArrayList<Map<String, String>> latestCodes = new CodeService().formatCodeMap(codes);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(latestCodes);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, String> body) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");

            Code code = new Code(new Date(), body.get("code"));
            code = codeRepository.save(code);

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(code.getId()));

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(map);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}