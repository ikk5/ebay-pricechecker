package XML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "findCompletedItemsResponse", namespace = "http://www.ebay.com/marketplace/search/v1/services")
@XmlAccessorType(XmlAccessType.FIELD)
public class FindCompletedItemsResponse implements Serializable {

    private static final long serialVersionUID = -9036848149670103466L;

    private String ack;

    private String version;

    private String timestamp;

    private SearchResult searchResult;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String space = " ";
        double total = 0;
        int results = 0;
        if (searchResult != null && searchResult.getItem() != null) {
            for (Item item : searchResult.getItem()) {
                results++;
                total += item.getSellingStatus().getConvertedCurrentPrice();
                builder.append(item.getTitle())
                        .append(space).append("€").append(item.getSellingStatus().getConvertedCurrentPrice())
                        .append(space).append(item.getCountry())
                        .append(space).append(item.getListingInfo().getEndTime())
                        .append("\n");
            }
            builder.append("Gemiddelde prijs: €").append(total / results).append("\n");
        }
        builder.append("Aantal resultaten: ").append(results);
        return builder.toString();
    }
}