package il.ac.shenkar.hibernate;

import javax.persistence.Id;
import java.util.List;

public class User {
    @Id
    private int id;
    private String userName;
    private String password;
    private static final HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();

    public User() {
        super();
    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        dao.createUser(this);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void addItem(String itemName, String itemStatus) {
        Item item = new Item(getId(), itemName, itemStatus);
    }

    public void deleteItem(Item item) {
        dao.deleteItem(item);
    }

    public List<Item> getItems() {
        return dao.getAllItemsByUserId(getId());
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", name=" +  ", userName=" + getUserName() + ", password=" + getPassword() + "]";
    }
}
