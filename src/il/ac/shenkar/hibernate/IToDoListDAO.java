package il.ac.shenkar.hibernate;


import java.util.List;

public interface IToDoListDAO {
	public void createUser(User user);
	public void deleteUser(User userID);
	public void updateUser(User user);
	public void addItem(int userId,Item item);
	public void updateItem(int userId,Item item);
	public void deleteItem(int userId,Item item);
	public User getUserById(int userID);
	public List<Item> getAllItemsByUserId(User user);
}
