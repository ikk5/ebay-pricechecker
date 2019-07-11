package keywords;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Completeness {
    private static final String NOT_SEALED = " -sealed -neu -new -neuf -nuovo -neuware -seal";
    private static final Map<String, String> completeness = new HashMap<String, String>() {
        {
            put("cib", " -only -loose -ohne -nur -sans" + NOT_SEALED);
            put("ci", " -ovp -boxed" + NOT_SEALED);
            put("cb", " -ovp" + NOT_SEALED);
            put("loose", " -cib -complete -ovp -boxed" + NOT_SEALED);
            put("sealed", " -only -loose -ohne -nur -sans");
        }
    };

    public static String getCompletenessKeywords(String comp) {
        return completeness.get(StringUtils.lowerCase(comp));
    }
}
