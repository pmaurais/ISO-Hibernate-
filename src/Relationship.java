//+JMJ+
//Paul A Maurais

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.HashMap;
import java.util.Map;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Relationship implements Item{
    @Id
    @XmlElement(name="customerId")
    private int id;
    @XmlElementWrapper(name="productIds")
    @XmlElement(name="productId")
    private int[] productIds;
    private String productIdString;

    protected Relationship() {
    }

    public Relationship(int id, int[] productIds) {

        String productIdString = Integer.toString(productIds[0]);
        for (int i =1; i<productIds.length;i++) {
            productIdString=productIdString+","+productIds[i];
        }
        this.productIdString = productIdString;
    }

    public int getId() {
        return id;
    }

    @Override
    public Map getData() {
        if(productIdString==null&&productIds!=null){
            String out=this.productIds[0]+"";
            for(int i=1; i<this.productIds.length;i++){
                out=out+","+this.productIds[i];
            }
            this.productIdString=out;
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
            map.put(this.id,productIdString);
        return map;
    }

    public int[] getProductIds() {
        return productIds;
    }

    public String getProductIdString() {
        return productIdString;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductIds(int[] productIds) {
        this.productIds = productIds;
    }

    public void setProductIdString(String productIdString) {
        this.productIdString = productIdString;
    }

}

//+JMJ+
