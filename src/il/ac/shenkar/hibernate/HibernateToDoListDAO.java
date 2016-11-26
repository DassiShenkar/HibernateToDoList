package il.ac.shenkar.hibernate;


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
	public void addItem(int userId, Item item) { // TODO: implement by user and item and not by table
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateItem(int userId, Item item) { // TODO: implement by user and item and not by table
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void deleteItem(int userId, Item item) { // TODO: implement by user and item and not by table
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void getAllItemsByUserID(User user) {
		// TODO Auto-generated method stub
		
	}

}
