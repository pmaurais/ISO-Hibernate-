import javax.xml.bind.annotation.adapters.XmlAdapter;

public class charToBooleanAdapter extends XmlAdapter<String,Boolean>{

    @Override
    public String marshal(Boolean bool) throws Exception {
        if(bool){
            return "Y";
        } else {
            return "N";
        }
    }

    @Override
    public Boolean unmarshal(String str) throws Exception {
        if(str.equals("Y")){
            return true;
        } else {
            return false;
        }
    }
}

