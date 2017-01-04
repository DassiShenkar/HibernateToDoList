package il.ac.shenkar.hibernate;


import java.util.List;

public interface IToDoListDAO {
    public void createUser(User user);

    public void deleteUser(User userId);

    public void updateUser(User user);

    public void addItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public User getUserById(int userId);

    public List<User> getAllUsers();

    public List<Item> getAllItemsByUserId(int userId);
}
