package platform.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import platform.repositories.Code;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CodeRestController {
    private static ArrayList<Code> codes = new ArrayList<>();

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Map<String, String>> getCode(@PathVariable("id") int id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("code", codes.get(id).getCode());
        map.put("date", codes.get(id).getDate());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Code>> getLatest() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        List<Code> latestCodes = (List<Code>) codes.clone();
        Collections.reverse(latestCodes);
        latestCodes = latestCodes.stream().limit(10).collect(Collectors.toList());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(latestCodes);
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, String> body) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/dd/MM HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        int length = codes.size();
        codes.add(new Code(formattedDate, body.get("code").toString()));

        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(length));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(map);
    }

    @GetMapping("/code/{id}")
    public String getIndex(@ModelAttribute("model") ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("code", codes.get(id).getCode());
        model.addAttribute("date", codes.get(id).getDate());

        return "index";
    }

    @GetMapping("/code/latest")
    public String getIndex(@ModelAttribute("model") ModelMap model) {
        List<Code> latestCodes = (List<Code>) codes.clone();
        Collections.reverse(latestCodes);
        latestCodes = latestCodes.stream().limit(10).collect(Collectors.toList());

        model.addAttribute("codes", latestCodes);

        return "latest";
    }
}