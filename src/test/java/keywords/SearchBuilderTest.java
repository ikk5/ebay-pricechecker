package keywords;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SearchBuilderTest {

    @Test
    public void testBuildSearchStringEmpty() {
        SearchBuilder builder = new SearchBuilder("", "", "", "", "");
        assertEquals(builder.buildSearchString(), "");
    }

    @Test
    public void nullTest() {
        SearchBuilder builder = new SearchBuilder(null, null, null, null, null);
        assertEquals(builder.buildSearchString(), "");
    }

    @Test
    public void consoleTest() {
        SearchBuilder builder = new SearchBuilder("Purple Gamecube", "Console", "CIB", "PAL", null);
        assertEquals(builder.buildSearchString(), "Purple Gamecube Console -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -neuware -seal -us -ntsc -usa -jpn -japanese -japan -bundle -pack -lot -empty -edition -2 -3 -replacement");
    }

    @Test
    public void sealedTest() {
        SearchBuilder builder = new SearchBuilder("Psychonauts", "PC", "Sealed", "PAL", null);
        assertEquals(builder.buildSearchString(), "Psychonauts PC -guide -only -loose -ohne -nur -sans -us -ntsc -usa -jpn -japanese -japan -bundle -pack -lot -empty -edition -2 -3 -replacement");
    }

    @Test
    public void bigboxTest() {
        SearchBuilder builder = new SearchBuilder("Donkey Konga", "Gamecube", "CIB", "PAL", "Bigbox");
        assertEquals(builder.buildSearchString(), "Donkey Konga Gamecube -choice -guide -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -neuware -seal -us -ntsc -usa -jpn -japanese -japan -lot -empty -edition -2 -3 -replacement");
    }

    @Test
    public void sequelTest() {
        SearchBuilder builder = new SearchBuilder("Super Mario Bros 3", "NES", "CIB", "PAL", "");
        assertEquals(builder.buildSearchString(), "Super Mario Bros 3 NES -guide -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -neuware -seal -us -ntsc -usa -jpn -japanese -japan -bundle -pack -lot -empty -edition -2 -replacement");
    }

    @Test
    public void newTitleTest() {
        SearchBuilder builder = new SearchBuilder("New Super Mario Bros Wii", "Wii", "CIB", "PAL", "");
        assertEquals(builder.buildSearchString(), "New Super Mario Bros Wii -wiiu -\"wii u\" -selects -guide -only -loose -ohne -nur -sans -sealed -neu -neuf -nuovo -neuware -seal -us -ntsc -usa -jpn -japanese -japan -bundle -pack -lot -empty -edition -2 -3 -replacement");
    }
}