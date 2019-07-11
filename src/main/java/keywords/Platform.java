package keywords;

import org.apache.commons.lang3.StringUtils;

public enum Platform {
    PC("PC", Category.GAMES, ""),
    NES("NES", Category.GAMES, ""),
    SNES("SNES", Category.GAMES, "", "Super NES"),
    N64("N64", Category.GAMES, " -choice", "Nintendo 64"),
    GAMECUBE("Gamecube", Category.GAMES, " -choice", "GCN"),
    WII("Wii", Category.GAMES, " -wiiu -\"wii u\" -selects"),
    WIIU("Wii U", Category.GAMES, "", "WiiU -selects"),
    SWITCH("Switch", Category.GAMES, "", "NSW"),
    GAMEBOY("Gameboy", Category.GAMES, "", "GB"),
    GBC("GBC", Category.GAMES, "", "Gameboy Color"),
    GBA("GBA", Category.GAMES, "", "Gameboy Advance"),
    DS("NDS", Category.GAMES, "", "DS"),
    DSI("DSi", Category.GAMES, ""),
    DS3("3DS", Category.GAMES, ""),
    PS1("PS1", Category.GAMES, " -platinum"),
    PS2("PS2", Category.GAMES, " -platinum"),
    PS3("PS3", Category.GAMES, " -platinum"),
    PS4("PS4", Category.GAMES, ""),
    PSP("PSP", Category.GAMES, ""),
    XBOX("XBOX", Category.GAMES, " -360 -one -classics"),
    XBOX_360("XBOX 360", Category.GAMES, " -classics"),
    XBONE("XBONE", Category.GAMES, "", "XBOX One"),
    MASTER_SYSTEM("Master System", Category.GAMES, "", "SMS"),
    SATURN("Saturn", Category.GAMES, ""),
    MEGA_DRIVE("Mega Drive", Category.GAMES, "", "SMD"),
    DREAMCAST("Dreamcast", Category.GAMES, "", "DC"),
    VECTREX("Vectrex", Category.GAMES, ""),
    VIDEOPAC("Videopac", Category.GAMES, ""),
    ATARI_2600("Atari 2600", Category.GAMES, ""),
    CONSOLE("Console", Category.CONSOLES, ""),
    CONTROLLER("Controller", Category.CONTROLLERS, ""),
    DISNEY_INFINITY("Disney Infinity", Category.TOYS_TO_LIFE, ""),
    AMIIBO("Amiibo", Category.TOYS_TO_LIFE, ""),
    LEGO_DIMENSIONS("Lego Dimensions", Category.TOYS_TO_LIFE, " -tag -tags -discs -disc"),
    ACCESSORY("Accessory", Category.ACCESSORIES, "", "Accessoire"),
    OTHERS("Others", Category.GAMES_AND_CONSOLES, ""),
    UNKNOWN("Unknown", Category.GAMES_AND_CONSOLES, "");

    private final String description;
    private final Category category;
    private final String keywords;
    private final String alias;

    Platform(String description, Category category, String keywords) {
        this.description = description;
        this.category = category;
        this.keywords = keywords;
        this.alias = null;
    }

    Platform(String description, Category category, String keywords, String alias) {
        this.description = description;
        this.category = category;
        this.keywords = keywords;
        this.alias = alias;
    }

    public String getKeywords() {
        return keywords + category.getFilter();
    }

    public int getCategoryId() {
        return category.getCategoryId();
    }

    public boolean shouldAddPlatformToKeyword() {
        return !OTHERS.equals(this) && !ACCESSORY.equals(this);
    }

    public static Platform getPlatformByDescription(String description) {
        for (Platform platform : Platform.values())
        {
            if (StringUtils.equalsIgnoreCase(platform.description, description) ||
                    StringUtils.equalsIgnoreCase(platform.alias, description))
            {
                return platform;
            }
        }
        return UNKNOWN;
    }

    public boolean isGame() {
        return !isAccessory() && !isConsole() && !isToysToLife() && !isController() && !OTHERS.equals(this) && !UNKNOWN.equals(this);
    }

    public boolean isAccessory() {
        return ACCESSORY.equals(this);
    }

    public boolean isController() {
        return CONTROLLER.equals(this);
    }

    public boolean isConsole() {
        return CONSOLE.equals(this);
    }

    public boolean isToysToLife() {
        return AMIIBO.equals(this) || DISNEY_INFINITY.equals(this) || LEGO_DIMENSIONS.equals(this);
    }

    public Category getCategory() {
        return category;
    }
}

