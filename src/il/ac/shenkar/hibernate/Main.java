package il.ac.shenkar.hibernate;

public class Main {

    public static void main(String[] args) {
        User arel = new User("music_gindos", "123456");
        User yoel = new User("yoel", "654321");
        arel.addItem("buy milk", "waiting");
        Item item2 = new Item(arel.getId(), "java homework", "in progress");
        Item item1 = arel.getItems().get(0);
        item1.updateStatus("done");
        arel.deleteItem(item2);
        HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
        dao.deleteUser(yoel);
        dao.getAllUsers().forEach(user-> {
            System.out.println(user.toString());
            user.getItems().forEach(item-> System.out.println(item.toString()));
        });
    }
}