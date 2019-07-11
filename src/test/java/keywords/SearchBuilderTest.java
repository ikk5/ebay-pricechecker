package keywords;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

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
        assertEquals(builder.buildSearchString(), "Purple Gamecube Console -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -us -ntsc -usa -jpn -japan -bundle -pack -lot -empty -2 -3 -replacement");
    }

    @Test
    public void sealedTest() {
        SearchBuilder builder = new SearchBuilder("Psychonauts", "PC", "Sealed", "PAL", null);
        assertEquals(builder.buildSearchString(), "Psychonauts PC sealed -only -loose -ohne -nur -sans -us -ntsc -usa -jpn -japan -bundle -pack -lot -empty -2 -3 -replacement");
    }

    @Test
    public void bigboxTest() {
        SearchBuilder builder = new SearchBuilder("Donkey Konga", "Gamecube", "CIB", "PAL", "Bigbox");
        assertEquals(builder.buildSearchString(), "Donkey Konga Gamecube -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -us -ntsc -usa -jpn -japan -lot -empty -2 -3 -replacement");
    }

    @Test
    public void sequelTest() {
        SearchBuilder builder = new SearchBuilder("Super Mario Bros 3", "NES", "CIB", "PAL", "");
        assertEquals(builder.buildSearchString(), "Super Mario Bros 3 NES -only -loose -ohne -nur -sans -sealed -neu -new -neuf -nuovo -us -ntsc -usa -jpn -japan -bundle -pack -lot -empty -2 -replacement");
    }

    @Test
    public void newTitleTest() {
        SearchBuilder builder = new SearchBuilder("New Super Mario Bros Wii", "Wii", "CIB", "PAL", "");
        assertEquals(builder.buildSearchString(), "New Super Mario Bros Wii -wiiu -\"wii u\" -only -loose -ohne -nur -sans -sealed -neu -neuf -nuovo -us -ntsc -usa -jpn -japan -bundle -pack -lot -empty -2 -3 -replacement");
    }
}