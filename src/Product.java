//+JMJ+
//Paul A Maurais

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Product implements Item{
    @Id
    private int id;
    private String name;
    private String effectiveDate;
    private String expirationDate;
    private String category;
    private double invoicePrice;
    private double Msrp;
    private double vendorComissionPercent;

    protected  Product(){}

    public Product(int id, String name, String effectiveDate, String expirationDate, String category, double invoicePrice, double Msrp, double vendorComissionPercent){
        this.id=id;
        this.name=name;
        this.effectiveDate=effectiveDate;
        this.expirationDate=expirationDate;
        this.category=category;
        this.invoicePrice=invoicePrice;
        this.Msrp=Msrp;
        this.vendorComissionPercent=vendorComissionPercent;
    }

    public Map getData(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",this.name);
        map.put("effectiveDate",this.effectiveDate);
        map.put("expirationDate",this.expirationDate);
        map.put("category", this.category);
        map.put("invoicePrice",this.invoicePrice);
        map.put("Msrp",this.Msrp);
        map.put("vendorComissionPercent",this.vendorComissionPercent);

        return map;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getEffectiveDate() {
        return effectiveDate;
    }

    @XmlElement
    public String getExpirationDate() {
        return expirationDate;
    }

    @XmlElement
    public String getCategory() {
        return category;
    }

    @XmlElement
    public double getInvoicePrice() {
        return invoicePrice;
    }

    @XmlElement
    public double getMsrp() {
        return Msrp;
    }

    @XmlElement
    public double getVendorComissionPercent() {
        return vendorComissionPercent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public void setMsrp(double msrp) {
        Msrp = msrp;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setVendorComissionPercent(double vendorComissionPercent) {
        this.vendorComissionPercent = vendorComissionPercent;
    }

}

//+JMJ+