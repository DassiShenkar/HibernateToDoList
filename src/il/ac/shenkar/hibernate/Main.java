package il.ac.shenkar.hibernate;

import java.sql.*;

public class Main{
    public static void main(String []args) throws SQLException,ClassNotFoundException {
        HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
        User user = new User("lifemichael","life");
        Item item = new Item(user.getId(),"Learn to java EE exam","Waiting");
        Item item1 = new Item(user.getId(),"shopping","Waiting");
        dao.createUser(user);   // add user to database
        dao.addItem(item);      // add item to user
        dao.addItem(item1);     // add item to user
        item.setStatus("done"); // change item status
        dao.updateItem(item);   // update the item in the database
    }
}
