package platform.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CodeRestController {
    @GetMapping("/api/code")
    public ResponseEntity<Map<String, String>> getCodeInJson() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("code", "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }
}