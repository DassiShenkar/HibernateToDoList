package il.ac.shenkar.hibernate;

public class Main {
    public static void main(String []args){
        Time time1 = new Time(0,0);
        time1.startCount();
        Double x = time1.computeTime();
        System.out.println(x);

        HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
        User user = new User("lifemichael","life");
        Item item = new Item(user.getId(),"Learn to java EE exam","Waiting");
        Item item1 = new Item(user.getId(),"shopping","Waiting");
        dao.createUser(user);
        dao.addItem(item);
        dao.addItem(item1);
        item.setStatus("done");
        dao.updateItem(item);
    }
}
