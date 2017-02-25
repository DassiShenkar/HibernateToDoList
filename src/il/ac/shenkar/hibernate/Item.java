package il.ac.shenkar.hibernate;

import javax.persistence.Id;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Item extends SimpleTagSupport {
    @Id  // auto increment unique id
    private int id;
    private int userId;
    private String title;
    private String status;
    private static final HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();

    public Item() {}

    public Item(int userId, String title, String status) {
        setUserId(userId);
        setTitle(title);
        setStatus(status);
        dao.addItem(this);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String itemName) {
        this.title = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String itemStatus) {
        this.status = itemStatus;
    }

    public void updateStatus(String status) {
        setStatus(status);
        dao.updateItem(this);
    }

    @Override
    public String toString() {
        return "Item [itemId=" + getId() + ", userId=" + getUserId() + ", itemName=" + getTitle() + ", status=" + getStatus() + "]";
    }

}
