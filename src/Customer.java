//+JMJ+
//Paul A Maurais

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Item{
    @Id
    private int id;
    private String name;
    private int duns;
    @XmlElement()
    @XmlJavaTypeAdapter(charToBooleanAdapter.class)
    private Boolean active;

    protected  Customer(){
    }

    public Customer(int id, String name, int duns, boolean active){
        this.name=name;
        this.duns=duns;
        this.active=active;
        this.id=id;
    }
    public Map getData(){
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("name",this.name);
        map.put("duns", this.duns);
        map.put("active",this.active);

        return map;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuns() {
        return duns;
    }

    public boolean isActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuns(int duns) {
        this.duns = duns;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(int id) {
        this.id = id;
    }
}

//+JMJ+