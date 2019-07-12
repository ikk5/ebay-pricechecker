package keywords;

import org.apache.commons.lang3.StringUtils;

public enum Platform {
    PC("PC", Category.GAMES, " PC"),
    NES("NES", Category.GAMES, " NES"),
    SNES("SNES", Category.GAMES, " SNES", "Super NES"),
    N64("N64", Category.GAMES, " N64 -choice", "Nintendo 64"),
    GAMECUBE("Gamecube", Category.GAMES, " (Gamecube,GCN,NGC) -choice", "GCN"),
    WII("Wii", Category.GAMES, " Wii -wiiu -\"wii u\" -selects"),
    WIIU("Wii U", Category.GAMES, " (WiiU,Wii U)", "WiiU -selects"),
    SWITCH("Switch", Category.GAMES, " Switch", "NSW"),
    GAMEBOY("Gameboy", Category.GAMES, " (Gameboy,Game boy)", "GB"),
    GBC("GBC", Category.GAMES, " (Gameboy Color,GBC)", "Gameboy Color"),
    GBA("GBA", Category.GAMES, " (Gameboy Advance,GBA)", "Gameboy Advance"),
    DS("NDS", Category.GAMES, " (NDS,DS)", "DS"),
    DSI("DSi", Category.GAMES, " (DSi,DS,NDS)"),
    DS3("3DS", Category.GAMES, " 3DS"),
    PS1("PS1", Category.GAMES, " (PS1,PSX,Playstation) -platinum"),
    PS2("PS2", Category.GAMES, " (PS2,Playstation 2) -platinum"),
    PS3("PS3", Category.GAMES, " (PS3,Playstation 3) -platinum"),
    PS4("PS4", Category.GAMES, " (PS4,Playstation 4)"),
    PSP("PSP", Category.GAMES, " PSP"),
    XBOX("XBOX", Category.GAMES, " XBOX -360 -one -classics"),
    XBOX_360("XBOX 360", Category.GAMES, " XBOX 360 -classics"),
    XBONE("XBONE", Category.GAMES, " (XBONE,XBOX One)", "XBOX One"),
    MASTER_SYSTEM("Master System", Category.GAMES, " (SMS,Master System)", "SMS"),
    SATURN("Saturn", Category.GAMES, " Saturn"),
    MEGA_DRIVE("Mega Drive", Category.GAMES, " (SMD,Mega Drive)", "SMD"),
    DREAMCAST("Dreamcast", Category.GAMES, " (Dreamcast,DC)", "DC"),
    VECTREX("Vectrex", Category.GAMES, " Vectrex"),
    VIDEOPAC("Videopac", Category.GAMES, " Videopac"),
    ATARI_2600("Atari 2600", Category.GAMES, " Atari 2600"),
    CONSOLE("Console", Category.CONSOLES, " Console"),
    CONTROLLER("Controller", Category.CONTROLLERS, ""),
    DISNEY_INFINITY("Disney Infinity", Category.TOYS_TO_LIFE, " Disney Infinity"),
    AMIIBO("Amiibo", Category.TOYS_TO_LIFE, " Amiibo"),
    LEGO_DIMENSIONS("Lego Dimensions", Category.TOYS_TO_LIFE, " Lego Dimensions -tag -tags -discs -disc"),
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

