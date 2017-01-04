package il.ac.shenkar.hibernate;


import java.util.List;

public interface IToDoListDAO {
    public void createUser(User user);

    public void deleteUser(User userId);

    public void updateUser(User user);

    public void addItem(Item item);

    public void updateItem(int userId, Item item);

    public void deleteItem(int userId, Item item);

    public User getUserById(int userId);

    public List<Item> getAllItemsByUserId(int userId);
}
