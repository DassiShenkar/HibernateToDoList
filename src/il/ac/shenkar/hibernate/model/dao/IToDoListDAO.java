package il.ac.shenkar.hibernate.model.dao;

import il.ac.shenkar.hibernate.model.Item;
import il.ac.shenkar.hibernate.model.User;

import java.util.List;

public interface IToDoListDAO {
    void createUser(User user) throws ToiListException;

    boolean checkIfUserExists(User user) throws ToiListException;

    void addItem(Item item) throws ToiListException;

    void updateItem(Item item) throws ToiListException;

    void deleteItem(Item item) throws ToiListException;

    Item getItemByID(int itemID) throws ToiListException;

    int getUserIdByEmail(User user) throws ToiListException;

    List getAllTasks() throws ToiListException;

    String getNameById(int userId) throws ToiListException;

    User getUserById(int userID) throws ToiListException;

    List<Item> getAllItemsByUserId(int userId) throws ToiListException;
}
