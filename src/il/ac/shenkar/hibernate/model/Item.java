package il.ac.shenkar.hibernate.model;

import il.ac.shenkar.hibernate.model.dao.HibernateToDoListDAO;
import il.ac.shenkar.hibernate.model.dao.ToiListException;


import javax.persistence.*;


@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "USERID")
    private int userId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "STATUS")
    private String status;

    public Item() {
    }

    public Item(int userId, String title, String status) throws ToiListException {
        setUserId(userId);
        setTitle(title);
        setStatus(status);
    }

    /**
     * @return id use for
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public int getUserId() {
        return userId;
    }

    //    /**
//     * This function
//     * @param userId id of
//     * @param
//     * @return
//     * @throws
//     */
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

    @Override
    public String toString() {
        return "Item [itemId=" + getId() + ", userId=" + getUserId() + ", itemName=" + getTitle() + ", status=" + getStatus() + "]";
    }
}
