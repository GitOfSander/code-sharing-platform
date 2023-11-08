package platform.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class IndexController {
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

    @GetMapping("/code")
    public ResponseEntity<String> getIndex() {
        String content =
                "<!DOCTYPE html>\n" +
                        "\n" +
                        "<html lang=\"en\">\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <title>Code</title>\n" +
                        "        <link rel=\"stylesheet\" href=\"/css/stylesheet.css\" />\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <span id=\"load_date\">2020/01/01 12:00:03</span>\n" +
                        "        <pre id=\"code_snippet\">" + readFile() +  "</pre>\n" +
                        "\n" +
                        "        <script src=\"/js/get.js\"></script>\n" +
                        "    </body>\n" +
                        "</html>";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/code/new")
    public String getNew() {
        return "new";
    }
}
