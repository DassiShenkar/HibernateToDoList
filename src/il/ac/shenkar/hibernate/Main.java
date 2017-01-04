package il.ac.shenkar.hibernate;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user = new User("arel gindos22", "music", "123456");
        user.addItem("shopping", "accepted");
        List<Item> userItems = user.getItems();
        userItems.forEach(item-> {
            System.out.println(item.toString());
        });
    }
}