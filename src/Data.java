import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElementWrapper(name="products")
    @XmlElement(name="product")
    private Product[] products;
    @XmlElementWrapper(name="customers")
    @XmlElement(name="customer")
    private Customer[] customers;
    @XmlElementWrapper(name="relationships")
    @XmlElement(name="relationship")
    private Relationship[] relationships;

    public Data(){
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public void setRelationships(Relationship[] relationships) {
        this.relationships = relationships;
    }

    public Product[] getProducts() {
        return products;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public Relationship[] getRelationships() {
        return relationships;
    }
}
