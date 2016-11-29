package il.ac.shenkar.hibernate;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateToDoListDAO implements IToDoListDAO  {
	private static HibernateToDoListDAO instance = null;
	public SessionFactory factory ;
	Session session;
	
	   public static HibernateToDoListDAO getInstance() { // singleton
	      if(instance == null) {
	         instance = new HibernateToDoListDAO();
	      }
	      return instance;
	   }
	   
	   private HibernateToDoListDAO(){
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
			session = factory.openSession();
	}
	
	@Override
	public void createUser(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteUser(User user) {
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateUser(User user) {
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void addItem(int userId, Item item) { // add item to UserItem table with userID
		UserItem useritem = new UserItem();
		useritem.setItemID(item.getId());
		useritem.setUserID(userId);
		session.beginTransaction();
		session.save(useritem);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateItem(int userId, Item item) { // update item in UserItem table
		UserItem useritem = new UserItem();
		useritem.setItemID(item.getId());
		useritem.setUserID(userId);
		session.beginTransaction();
		session.update(useritem);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteItem(int userId, Item item) {// delete item in UserItem table
		UserItem useritem = new UserItem();
		useritem.setItemID(item.getId());
		useritem.setUserID(userId);
		session.beginTransaction();
		session.delete(useritem);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAllItemsByUserId(User user) {  // return a list of all items by userID
		session.beginTransaction();
		Query query = session.createQuery("from UserItem where userID = :userID");
		query.setParameter("userID",2);
		List list = query.list();
		session.close();
		return list;
	}

	@Override
	public User getUserById(int userID) { // return user details by userID
	        User user = null;
	        user =  (User) session.get(User.class,userID);
	        Hibernate.initialize(user);
	        return user;
	}
}
