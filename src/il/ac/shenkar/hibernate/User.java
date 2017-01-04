package il.ac.shenkar.hibernate;

import javax.persistence.Id;
import java.util.List;

public class User {
    @Id
    private int id;
    private String name;
    private String userName;
    private String password;
    private static final HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();

    public User() {
        super();
    }

    public User(String name, String userName, String password) {
        setName(name);
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

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
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

    public List<Item> getItems() {
        return dao.getAllItemsByUserId(getId());
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", name=" + getName() + ", userName=" + getUserName() + ", password=" + getPassword() + "]";
    }
}
