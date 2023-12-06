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
    private final CodeService codeService;

    CodeRestController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
        this.codeService = new CodeService(this.codeRepository);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Map<String, String>> getCode(@PathVariable("id") UUID id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Optional<Code> optCode = codeRepository.findById(id);

        if (optCode.isEmpty()) return ResponseEntity.notFound().build();

        Code code = optCode.get();

        if (codeService.isCodeExpired(code)) return ResponseEntity.notFound().build();

        Map<String, String> map = new HashMap<>();
        map.put("code", code.getCode());

        String strDate = new DateTimeHelper().dateToString(code.getDate());
        map.put("date", strDate);

        if (code.getViewsRestriction() != 0) map.put("views", String.valueOf(code.getViewsRestriction() - 1));
        if (code.getTimeRestriction() != 0) map.put("time", String.valueOf(code.getTimeRestriction()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<ArrayList<Map<String, String>>> getLatest() {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");

            List<Code> codes = codeRepository.findLast10NotRestricted();
            ArrayList<Map<String, String>> latestCodes = codeService.formatCodeMap(codes);

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

            int timeRestriction = (body.get("time_restriction") != "" ? Integer.parseInt(body.get("time_restriction")) : 0 );
            int viewsRestriction = (body.get("views_restriction") != "" ? Integer.parseInt(body.get("views_restriction")) + 1 : 0 );

            Code code = new Code(new Date(), timeRestriction, viewsRestriction, body.get("code"));
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