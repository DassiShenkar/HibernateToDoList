package il.ac.shenkar.hibernate;

import javax.persistence.Id;
import java.util.List;

public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private static final HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();

    public User() {
        super();
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addItem(String itemName, String itemStatus) throws ToiListException{
        Item item = new Item(getId(), itemName, itemStatus);
    }

    public void deleteItem(Item item) throws ToiListException{
        dao.deleteItem(item);
    }

    public List<Item> getItems() throws ToiListException{
        return dao.getAllItemsByUserId(getId());
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", name=" +  ", userName=" + getUsername() + ", password=" + getPassword() + "]";
    }
}
