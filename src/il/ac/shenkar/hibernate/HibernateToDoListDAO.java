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
    }

    @Override
    public void deleteUser(User user) {
        session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }

    @Override
    public void addItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
    }

    @Override
    public void updateItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
    }

    @Override
    public void deleteItem(Item item) {
        session = factory.openSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List users = query.list();
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
        return list;
    }

    @Override
    public User getUserById(int userID){
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where ID = :userId");
        query.setParameter("userId", userID);
        List list = query.list();
        return (User)list.get(0);
    }

    @Override
    public Item getItemByID(int itemID) {
        Item item = null;
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where ID = :itemID");
        query.setParameter("itemID", itemID);
        List list = query.list();
        item = (Item)list.get(0);
        return item;
    }

    @Override
    public boolean checkIfUserExists(User user) {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where USERNAME= :username");
        query.setParameter("username",user.getUsername());
        List list = query.list();
        return list.isEmpty()? false:true;
    }

    @Override
    public int getUserIdByEmail(User user) {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where USERNAME= :username");
        query.setParameter("username", user.getUsername());
        List list = query.list();
        User tempUser = (User)list.get(0);
        return tempUser.getId();
    }

    @Override
    public String getNameById(int userId) {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where ID= :userId");
        query.setParameter("userId", userId);
        List list = query.list();
        User user = (User)list.get(0);
        return user.getUsername();
    }

    @Override
    public List getAllTasks() {
        session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item");
        List list = query.list();
        return list;
    }
}
