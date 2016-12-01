package il.ac.shenkar.hibernate;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		HibernateToDoListDAO hibernateToDoListDAO = HibernateToDoListDAO.getInstance();
		User user = new User("arel gindos22", "music", "123456");
		Item item = new Item(1,"shopping","accepted");
		System.out.println("hello");
	}
}