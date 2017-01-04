package il.ac.shenkar.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateToDoListDAO implements IToDoListDAO {
    private static HibernateToDoListDAO instance = null;
    private SessionFactory factory;
    private Session session;

    public static HibernateToDoListDAO getInstance() {
        if (instance == null) {
            instance = new HibernateToDoListDAO();
        }
        return instance;
    }

    private HibernateToDoListDAO() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void createUser(User user) {
        session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) {
        session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void addItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List users = query.list();
        session.close();
        return users;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<Item> getAllItemsByUserId(int userId) {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where userId = :userId");
        query.setParameter("userId", userId);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public User getUserById(int userID) {
        session = factory.openSession();
        User user = null;
        user = (User) session.get(User.class, userID);
        Hibernate.initialize(user);
        return user;
    }
}
