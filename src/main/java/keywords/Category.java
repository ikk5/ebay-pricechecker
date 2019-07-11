package keywords;

public enum Category {
    GAMES_AND_CONSOLES(1249, ""),
    ACCESSORIES(54968, ""),
    CONSOLES(139971, ""),
    GAMES(139973, " -guide"),
    TOYS_TO_LIFE(182180, " -guide"),
    CONTROLLERS(117042, "");

    private final int categoryId;
    private final String filter;

    Category(int categoryId, String filter) {
        this.categoryId = categoryId;
        this.filter = filter;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getFilter() {
        return filter;
    }
}
