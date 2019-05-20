import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Driver {
    private static SessionFactory factory;

    public static void main(String[] args) {
        File file = new File("C:\\Users\\NEC.HP840G2SSD\\IdeaProjects\\ISO(Hibernate)\\src\\Data.xml");
        Data data = unMarshalData(file);
        List[] localData = getLocal();
        List<Item> localCustomers = localData[0];
        List<Item> localProducts = localData[1];
        List<Item> localRelationships = localData[2];

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            check(mapify(data.getCustomers()), mapify(localCustomers), session,"Customer");
            check(mapify(data.getProducts()), mapify(localProducts), session,"Product");
            check(mapify(data.getRelationships()), mapify(localRelationships), session,"Relationship");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static Map<Integer, Item> mapify(Item[] items) {
        Map<Integer, Item> map = new HashMap<>();
        for (Item item : items) {
            map.put(item.getId(), item);
        }
        return map;
    }

    private static Map<Integer, Item> mapify(Iterable<Item> items) {
        Map<Integer, Item> map = new HashMap<>();
        for (Item item : items) {
            map.put(item.getId(), item);
        }
        return map;
    }

    private static void check(Map<Integer, Item> master, Map<Integer, Item> local,Session session, String dataType) {
        for (int masterKey : master.keySet()) {
            if (!local.containsKey(masterKey)) {
                System.out.println(dataType + " " + masterKey + " added to the local Database");
                session.save(master.get(masterKey));
            } else {
                if (!master.get(masterKey).getData().equals(local.get(masterKey).getData())) {
                    System.out.println(dataType + " " + masterKey + " updated in the local Database");
                    session.delete(local.get(masterKey));
                    session.save(master.get(masterKey));
                }
            }
        }
        for (int localKey : local.keySet()) {
            if (!master.containsKey(localKey)) {
                System.out.println(dataType + " " + localKey + " deleted from the local Database");
                session.delete(local.get(localKey));
            }
        }
    }

    private static List[] getLocal() {
        List<Item> localProducts = null;
        List<Item> localRelationships = null;
        List<Item> localCustomers = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            localProducts = session.createQuery("FROM Product").list();
            localRelationships = session.createQuery("FROM Relationship").list();
            localCustomers = session.createQuery("FROM Customer").list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return new List[]{localCustomers, localProducts, localRelationships};
    }

    private static Data unMarshalData(File file) {
        Data data = new Data();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            data = (Data) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return data;
    }

}
