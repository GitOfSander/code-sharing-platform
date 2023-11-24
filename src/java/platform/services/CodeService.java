package platform.services;

import platform.entities.Code;
import platform.utils.DateTimeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeService {
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
}
