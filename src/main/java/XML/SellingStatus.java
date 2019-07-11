package XML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "sellingStatus")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellingStatus implements Serializable {
    private static final long serialVersionUID = -4478538221801685410L;

    private double currentPrice;
    private double convertedCurrentPrice;
    private Integer bidCount;
    private String sellingState; //EndedWithSales

}