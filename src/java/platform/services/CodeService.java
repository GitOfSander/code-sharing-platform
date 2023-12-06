package platform.services;

import platform.entities.Code;
import platform.repositories.CodeRepository;
import platform.utils.DateTimeHelper;

import java.util.*;

public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public ArrayList<Map<String, String>> formatCodeMap(List<Code> codes) {
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        codes.forEach(i -> {
            Map<String, String> map = new HashMap<>();

            map.put("code", i.getCode());
            map.put("date", dateTimeHelper.dateToString(i.getDate()));

            arrayList.add(map);
        });

        return arrayList;
    }


    public boolean isCodeExpired(Code code) {
        if (code.getViewsRestriction() == 1) {
            return true;
        } else if (code.getViewsRestriction() > 1) {
            code.setViewsRestriction(code.getViewsRestriction() - 1);
            codeRepository.save(code);
        }

        if (code.getTimeRestriction() > 0) {
            DateTimeHelper dateTimeHelper = new DateTimeHelper();
            Date expireDate = dateTimeHelper.addSecondsToDate(code.getTimeRestriction(), code.getDate());
            if (expireDate.getTime() < new Date().getTime()) return true;
        }

        return false;
    }
}
