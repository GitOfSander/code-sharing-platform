package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import platform.entities.Code;
import platform.repositories.CodeRepository;
import platform.services.CodeService;
import platform.utils.DateTimeHelper;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CodeRepository codeRepository;

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
        try {
            Code code = codeRepository.getById(id);

            model.addAttribute("code", code.getCode());

            String strDate = new DateTimeHelper().dateToString(code.getDate());
            model.addAttribute("date", strDate);

            return "index";
        } catch(EntityNotFoundException e) {
            return "404";
        }
    }
}
