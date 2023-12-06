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
import java.util.UUID;

@Controller
public class IndexController {
    private final CodeRepository codeRepository;
    private final CodeService codeService;

    IndexController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
        this.codeService = new CodeService(this.codeRepository);
    }

    @GetMapping("/code/new")
    public String getNew() {
        return "new";
    }

    @GetMapping("/code/latest")
    public String getIndex(@ModelAttribute("model") ModelMap model) {
        try {
            List<Code> codes = codeRepository.findLast10NotRestricted();
            model.addAttribute("codes", codeService.formatCodeMap(codes));

            return "latest";
        } catch(EntityNotFoundException e) {
            return "404";
        }
    }

    @GetMapping("/code/{id}")
    public String getIndex(@ModelAttribute("model") ModelMap model, @PathVariable("id") UUID id) {
        Optional<Code> optCode = codeRepository.findById(id);

        if(optCode.isEmpty()) return "404";

        Code code = optCode.get();

        if (codeService.isCodeExpired(code)) return "404";

        model.addAttribute("code", code.getCode());

        String strDate = new DateTimeHelper().dateToString(code.getDate());
        model.addAttribute("date", strDate);

        if (code.getViewsRestriction() != 0) model.addAttribute("viewsRestriction", code.getViewsRestriction() - 1);
        if (code.getTimeRestriction() != 0) model.addAttribute("timeRestriction", String.valueOf(code.getTimeRestriction()));

        return "index";
    }
}
