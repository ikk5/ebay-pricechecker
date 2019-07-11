package keywords;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

public enum Region {
    PAL("PAL", " -us -ntsc -usa -jpn -japanese -japan", LocatedIn.EU),
    NTSC("NTSC", " -pal", LocatedIn.USJ),
    NTSCU("NTSC-U", " -pal", LocatedIn.US),
    NTSCJ("NTSC-J", " -pal", LocatedIn.JPN),
    UNKNOWN("Unknown", "");

    private final String description;
    private final String keywords;
    private final LocatedIn locatedIn;

    Region(String description, String keywords, LocatedIn locatedIn) {
        this.description = description;
        this.keywords = keywords;
        this.locatedIn = locatedIn;
    }

    Region(String description, String keywords) {
        this.description = description;
        this.keywords = keywords;
        this.locatedIn = null;
    }

    public String getKeywords() {
        return keywords;
    }

    public static Region getRegionByDescription(String description) {
        for (Region region : Region.values())
        {
            if (StringUtils.equalsIgnoreCase(region.description, description))
            {
                return region;
            }
        }
        return UNKNOWN;
    }

    public Map<String, String> getLocatedInUrlParams() {
        return locatedIn == null ? Collections.EMPTY_MAP : locatedIn.getUrlParams();
    }

    public boolean isPAL() {
        return PAL.equals(this);
    }

    public boolean isUSA() {
        return NTSC.equals(this) || NTSCU.equals(this);
    }

    public boolean isJPN() {
        return NTSCJ.equals(this);
    }
}