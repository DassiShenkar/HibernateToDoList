package il.ac.shenkar.hibernate;


import java.util.List;

public interface IToDoListDAO {
     void createUser(User user);

     void deleteUser(User userId);

     void updateUser(User user);

     boolean checkIfUserExists(User user);

     void addItem(Item item);

     void updateItem(Item item);

     void deleteItem(Item item);

     Item getItemByID(int itemID);

     int getUserIdByEmail(User user);

     List getAllTasks();

     String getNameById(int userId);

     User getUserById(int userID);

     Boolean checkIfDBExist();

     List<User> getAllUsers();

     List<Item> getAllItemsByUserId(int userId);
}
