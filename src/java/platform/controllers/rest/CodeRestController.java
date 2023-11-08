package platform.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class CodeRestController {
    private final Path FILE = Paths.get("code.txt");

    private String readFile() {
        try {
            if (!Files.exists(FILE)) {
                Files.createFile(FILE);
            }

            return Files.readString(FILE);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

        return "";
    }

    public void updateFile(String string) {
        try {
            Files.writeString(FILE, string);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    @GetMapping("/api/code")
    public ResponseEntity<Map<String, String>> getCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("code", readFile());

        //LocalDateTime myDateObj = LocalDateTime.now();
        //DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/dd/MM HH:mm:ss");
        //String formattedDate = myDateObj.format(myFormatObj);
        map.put("date", "2020/01/01 12:00:03");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, String> body) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        updateFile(body.get("code"));
        Map<String, String> map = new HashMap<>();

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }
}