package Excel;

import lombok.Getter;
import lombok.Setter;

public class ProcessedRow {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String platform;

    @Getter
    @Setter
    private String completeness;

    @Getter
    @Setter
    private String Region;

    @Getter
    @Setter
    private String notes;
}
