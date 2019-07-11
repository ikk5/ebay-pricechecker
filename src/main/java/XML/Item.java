package XML;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {
    private static final long serialVersionUID = -1215844539927435713L;
    //    private String itemId;

    private String title;

    private String globalId;
//
//    private String subtitle;
//
//    private Category primaryCategory;
//
//    private Category secondaryCategory;
//
//    private String galleryURL;
//
//    private GalleryInfoContainer galleryInfoContainer;
//
//    private String viewItemURL;
//
//    private String charityId;
//
//    private ProductId productId;
//
//    private List<String> paymentMethod;
//
//    private Boolean autoPay;
//
//    private String postalCode;

    private String location;

    private String country;

    //    private Storefront storeInfo;
//
//    private SellerInfo sellerInfo;
//
//    private ShippingInfo shippingInfo;

    private SellingStatus sellingStatus;

    private ListingInfo listingInfo;
    //
//    private Boolean returnsAccepted;
//
//    private List<String> galleryPlusPictureURL;
//
//    private String compatibility;
//
//    private Distance distance;

    private Condition condition;
//
//    private Boolean isMultiVariationListing;
//
//    private DiscountPriceInfo discountPriceInfo;
//
//    private String pictureURLSuperSize;
//
//    private String pictureURLLarge;
//
//    private UnitPriceInfo unitPrice;
//
//    private List<ItemAttribute> attribute;
//
//    private Boolean topRatedListing;
//
//    private String delimiter;

}
