package dasturlash.uz;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        persist();
//        merge();
//        remove();
//        refresh();
//        detach();
        all();
//        replicate();
//        saveUpdate();

    }

    public static void persist() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer); // save parent, child also will be saved

        t.commit();
        session.close();
        factory.close();
    }

    public static void merge() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer);// save parent, child also will be saved
        session.flush(); // flush session to save all
        session.clear();

        CustomerEntity savedCustomer = session.load(CustomerEntity.class, customer.getId());
        OrdersEntity savedOrder = session.load(OrdersEntity.class, order.getId());
        savedCustomer.setName("Alishbek");
        savedOrder.setItemCount(7);

        session.update(savedCustomer);

        t.commit();
        session.close();
        factory.close();
    }

    public static void remove() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer);// save parent, child also will be saved
        session.flush(); // flush session to save all
        session.clear();

        CustomerEntity savedCustomer = session.load(CustomerEntity.class, customer.getId());
        session.remove(savedCustomer); // remove parent

        t.commit();
        session.close();
        factory.close();
    }

    public static void refresh() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer);// save parent, child also will be saved
        session.flush(); // flush session to save all
        session.clear();

        customer.setName("Alishbek"); // change parent
        order.setItemCount(12); // change child

        session.refresh(customer); // refresh them

        t.commit();
        session.close();
        factory.close();
    }

    public static void detach() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer);// save parent, child also will be saved
        session.flush(); // flush session to save all

        session.detach(customer); // detach them (remove from session cache)

        session.get(CustomerEntity.class, customer.getId()); // get customer from db
        session.get(OrdersEntity.class, order.getId()); // get order from db

        t.commit();
        session.close();
        factory.close();
    }

    public static void all() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.persist(customer);// save parent, child also will be saved

        t.commit();
        session.close();
        factory.close();
    }

    public static void replicate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        CustomerEntity customer = session.get(CustomerEntity.class, 1); // get customer from db
        OrdersEntity order = session.get(OrdersEntity.class, 1);

        session.detach(customer);

        session.replicate(customer, ReplicationMode.OVERWRITE);

        t.commit();
        session.close();
        factory.close();
    }

    public static void saveUpdate() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        OrdersEntity order = new OrdersEntity(); // child
        order.setOrderAddress("Chilonzor");
        order.setItemCount(1);
        order.setTotalPrice(5600d);

        CustomerEntity customer = new CustomerEntity(); // parent
        customer.setName("Ali");
        customer.setSurname("Aliyev");
        customer.setOrderList(List.of(order)); // set child

        order.setCustomer(customer); // set customer

        session.save(customer); // save parent, child also will be savedx

        t.commit();
        session.close();
        factory.close();
    }
}