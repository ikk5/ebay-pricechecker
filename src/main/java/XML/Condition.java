package XML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "condition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Condition implements Serializable {

    private static final long serialVersionUID = 1606238746556128519L;
    private int conditionId;

    private String conditionDisplayName; //Meestal is alleen conditionId gevuld en deze null...
}