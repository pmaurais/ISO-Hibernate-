import javax.xml.bind.annotation.adapters.XmlAdapter;

public class relationshipStringAdapter extends XmlAdapter<Integer[],String>{

    @Override
    public String unmarshal(Integer[] arr) throws Exception {
        String out=arr[0]+",";
        for(int i=1; i<arr.length;i++){
            out=out+","+arr[i];
        }
        return out;
    }

    @Override
    public Integer[] marshal(String str) throws Exception {
        return new Integer[0];
    }
}
