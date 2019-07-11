package XML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@XmlRootElement(name = "listingInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListingInfo implements Serializable {

    private static final long serialVersionUID = 7096344394100399901L;
    private Date startTime;

    private Date endTime;

    private String listingType;

    private boolean bestOfferEnabled;

}
