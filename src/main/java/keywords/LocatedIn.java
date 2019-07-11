package keywords;

import java.util.LinkedHashMap;
import java.util.Map;

public enum LocatedIn {
    EU(new String[]{
            "NL", "GB", "DE", "FR", "IT", "BE", "ES", "DK", "FI", "GR"
            , "HU", "IE", "NO", "PL", "PT", "RU", "SE", "CH", "TR"
    }),
    US(new String[]{"US"}),
    JPN(new String[]{"JP"}),
    USJ(new String[]{"US", "JP"});

//    Belgium, Denmark, Finland, France, Germany, Greece, Hungary, Ireland, Italy,
//    the Netherlands, Norway, Poland, Portugal, Russia, Spain, Sweden, Switzerland, Turkey, and the UK.

    private final String[] params;

    LocatedIn(String[] params) {
        this.params = params;
    }

    public Map<String, String> getUrlParams() {
        Map<String, String> locatedInParams = new LinkedHashMap<>();
        if (params.length > 0) {
            locatedInParams.put("itemFilter(1).name", "LocatedIn");
            for (int i = 0; i < params.length; i++) {
                String code = params[i];
                locatedInParams.put("itemFilter(1).value(" + i + ")", code);
            }
        }
        return locatedInParams;
    }
}