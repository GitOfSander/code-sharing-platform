package platform.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.Code;
import platform.repositories.CodeRepository;
import platform.services.CodeService;
import platform.utils.DateTimeHelper;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
public class CodeRestController {
    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Map<String, String>> getCode(@PathVariable("id") long id) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");

            Code code = codeRepository.getById(id);

            Map<String, String> map = new HashMap<>();
            map.put("code", code.getCode());

            String strDate = new DateTimeHelper().dateToString(code.getDate());
            map.put("date", strDate);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(map);
        } catch(EntityNotFoundException e) {
            return (ResponseEntity<Map<String, String>>) ResponseEntity.notFound();
        }
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
            return (ResponseEntity<ArrayList<Map<String, String>>>) ResponseEntity.notFound();
        }
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, String> body) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-Type", "application/json");

            Code code = new Code(new Date(), body.get("code").toString());
            code = codeRepository.save(code);

            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(code.getId()));

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(map);
        } catch(EntityNotFoundException e) {
            return (ResponseEntity<Map<String, String>>) ResponseEntity.notFound();
        }
    }
}