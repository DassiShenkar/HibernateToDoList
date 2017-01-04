package il.ac.shenkar.hibernate;

import javax.persistence.Id;

public class Item {
    @Id
    private int id;
    private int userId;
    private String itemName;
    private String itemStatus;

    public Item() {

    }

    public Item(int userId, String itemName, String itemStatus) {
        setUserId(userId);
        setItemName(itemName);
        setItemStatus(itemStatus);
        HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
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

    public String getItemName() {
        return itemName;
    }

    private void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    private void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + getId() + ", userId=" + getUserId() + ", itemName=" + getItemName() + ", itemStatus=" + getItemStatus() + "]";
    }

}
