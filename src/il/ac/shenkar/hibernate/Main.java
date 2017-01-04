package il.ac.shenkar.hibernate;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user = new User("music_gindos", "123456");
        User user2 = new User("yoel", "654321");
        user.addItem("buy milk", "waiting");
        Item item2 = new Item(user.getId(), "java homework", "in progress");
        Item item1 = user.getItems().get(0);
        item1.updateStatus("done");
        List<Item> userItems = user.getItems();
        user.deleteItem(item2);
        userItems.forEach(item-> System.out.println(item.toString()));
    }
}