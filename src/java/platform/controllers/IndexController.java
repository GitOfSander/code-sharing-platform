package platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import platform.entities.Code;
import platform.repositories.CodeRepository;
import platform.services.CodeService;
import platform.utils.DateTimeHelper;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    private final CodeRepository codeRepository;

    IndexController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/code/new")
    public String getNew() {
        return "new";
    }

    @GetMapping("/code/latest")
    public String getIndex(@ModelAttribute("model") ModelMap model) {
        try {
            List<Code> codes = codeRepository.findFirst10ByOrderByDateDesc();
            model.addAttribute("codes", new CodeService().formatCodeMap(codes));

            return "latest";
        } catch(EntityNotFoundException e) {
            return "404";
        }
    }

    @GetMapping("/code/{id}")
    public String getIndex(@ModelAttribute("model") ModelMap model, @PathVariable("id") long id) {
        Optional<Code> code = codeRepository.findById(id);

        if(code.isEmpty()) return "404";

        model.addAttribute("code", code.get().getCode());

        String strDate = new DateTimeHelper().dateToString(code.get().getDate());
        model.addAttribute("date", strDate);

        return "index";
    }
}
