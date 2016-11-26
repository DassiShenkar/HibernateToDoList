package il.ac.shenkar.hibernate;

public class Main {

	public static void main(String[] args) {

		HibernateToDoListDAO hibernateToDoListDAO = HibernateToDoListDAO.getInstance();
		User user = new User("arel gindos", "music", "123456");
		Item item = new Item(1,"shopping","accepted");
		hibernateToDoListDAO.createUser(user);
		hibernateToDoListDAO.addItem(1, item);
		//hibernateToDoListDAO.deleteUser(user);
		
	}

}
