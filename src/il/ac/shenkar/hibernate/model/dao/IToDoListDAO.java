package il.ac.shenkar.hibernate.model.dao;

import il.ac.shenkar.hibernate.model.Item;
import il.ac.shenkar.hibernate.model.User;
import java.util.List;

/**
 * This interface is for DAO with CRUD methods
 * @author Arel Gindos
 * @author Dassi Rosen
 * @author Lior Lerner
 */
public interface IToDoListDAO {

    /**
     * This method add user into DB using hibernate
     * @param user - The user we want to add
     * @throws ToiListException @see ToiListException
     */
    void createUser(User user) throws ToiListException;

    /**
     * This method check if user exist in the DB using hibernate
     * @param user The user we want to check if exist
     * @return true if user exist, else false
     * @throws ToiListException @see ToiListException
     */
    boolean checkIfUserExists(User user) throws ToiListException;

    /**
     * This method add item into DB using hibernate
     * @param item The item we want add to DB
     * @throws ToiListException @see ToiListException
     */
    void addItem(Item item) throws ToiListException;

    /**
     * This method update the item using hibernate
     * @param item The item to be updated
     * @throws ToiListException @see ToiListException
     */
    void updateItem(Item item) throws ToiListException;

    /**
     * This method delete item using hibernate
     * @param item The item to be deleted
     * @throws ToiListException @see ToiListException
     */
    void deleteItem(Item item) throws ToiListException;

    /**
     * This method get item from DB using hibernate
     * @param itemID The item id
     * @return The specific item
     * @throws ToiListException @see ToiListException
     */
    Item getItemByID(int itemID) throws ToiListException;

    /**
     * This method get user_id from DB using hibernate
     * @param user The user we want to return
     * @return the userid of the specific user
     * @throws ToiListException @see ToiListException
     */
    int getUserIdByEmail(User user) throws ToiListException;

    /**
     * This method get all tasks from DB using hibernate
     * @return List with all the tasks
     * @throws ToiListException @see ToiListException
     */
    List getAllTasks() throws ToiListException;

    /**
     * This method get username from DB using hibernate
     * @param userId The user id
     * @return The username for the specific ID
     * @throws ToiListException @see ToiListException
     */
    String getNameById(int userId) throws ToiListException;

    /**
     * This method get user from DB using hibernate
     * @param userID The id of the user
     * @return The user for the specific id
     * @throws ToiListException @see ToiListException
     */
    User getUserById(int userID) throws ToiListException;

    /**
     * This method get all items for specific user from DB using hibernate
     * @param userId The id of the user
     * @return All the tasks for specific user
     * @throws ToiListException @see ToiListException
     */
    List<Item> getAllItemsByUserId(int userId) throws ToiListException;
}
