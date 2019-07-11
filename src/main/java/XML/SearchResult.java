package XML;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@XmlRootElement(name = "searchResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResult implements Serializable {

    private static final long serialVersionUID = 2318653099394477470L;
    private List<Item> item;
}
