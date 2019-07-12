package keywords;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SearchBuilder {
    private static final String DEFAULT = " -lot -empty -edition -2 -3 -replacement -repro";
    private static final String SPACE = " ";
    private static final int MAX_SEARCH_LENGTH = 350;

    private String title;
    private String platform;
    private Platform platformEnum;
    private Region regionEnum;
    private String completeness;
    private String notes;

    public SearchBuilder(String title, String platform, String completeness, String region, String notes) {
        this.title = title;
        this.platform = platform;
        platformEnum = Platform.getPlatformByDescription(platform);
        this.completeness = completeness;
        regionEnum = Region.getRegionByDescription(region);
        this.notes = notes;
//        System.out.println("Input: " + title + SPACE + platform + SPACE + completeness + SPACE + region + SPACE + notes);
    }

    public String buildSearchString() {
        if (StringUtils.isBlank(title))
        {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append(addNotesWords());

        //Platform
        if (platform != null)
        {
            builder.append(platformEnum.getKeywords());
        }

        //Completeness
        String completenessKeywords = Completeness.getCompletenessKeywords(completeness);
        if (completenessKeywords != null)
        {
            builder.append(completenessKeywords);
        }

        //Region
        builder.append(regionEnum.getKeywords());

        //The rest
        builder.append(addBundleFilter()).append(DEFAULT);

        String searchString = removeContainedWords(builder.toString().trim(), title);
        searchString = removeNumbersInTitle(searchString);
        searchString = removeContainedWords(searchString, notes);

        while (searchString.length() > MAX_SEARCH_LENGTH)
        {
            searchString = removeLastFilter(searchString);
        }
        System.out.println("Zoekopdracht: " + searchString);
//        System.out.println("Zoekcategorie: " + platformEnum.getCategory().name());
        return searchString;
    }

    private String removeContainedWords(String searchString, String words) {
        if (words != null)
        {
            String[] splitWords = StringUtils.lowerCase(words).split(SPACE);
            for (String word : splitWords)
            {
                if (StringUtils.contains(searchString, "-" + word + SPACE))
                {
                    searchString = searchString.replace(" -" + word, "");
                }
            }
        }
        return searchString;
    }

    private String removeNumbersInTitle(String searchString) {
        if ((title.contains("2") || platform.contains("2")) && searchString.contains("-2"))
        {
            searchString = searchString.replaceAll(" -2", "");
        }
        if ((title.contains("3") || platform.contains("3")) && searchString.contains("-3"))
        {
            searchString = searchString.replaceAll(" -3", "");
        }
        return searchString;
    }


    private String addBundleFilter() {
        String bundleFilter = " -bundle -pack";
        if (StringUtils.containsIgnoreCase(title, "bundle") ||
                StringUtils.containsIgnoreCase(title, "pack") ||
                StringUtils.containsIgnoreCase(notes, "pack") ||
                StringUtils.containsIgnoreCase(notes, "bigbox"))
        {
            bundleFilter = "";
        }
        return bundleFilter;
    }

    private String addNotesWords() {
        String noteWords = "";
        if (StringUtils.containsIgnoreCase(notes, "edition"))
        {
            noteWords = " edition";
        }
        return noteWords;
    }

    private String removeLastFilter(String searchString) {
        int lastDashIndex = searchString.lastIndexOf("-");
        return searchString.substring(0, lastDashIndex).trim();
    }

    public int getCategoryId() {
        return platformEnum.getCategoryId();
    }

    public Map<String, String> getLocatedInUrlParams() {
        return regionEnum.getLocatedInUrlParams();
    }
}
