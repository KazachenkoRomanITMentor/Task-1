package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Semen", "Semenov", (byte) 22);
        userService.saveUser("Vladislav", "Drakula", (byte) 122);
        userService.saveUser("Oleg", "Ivanov", (byte) 42);
        userService.saveUser("Ivan", "Olegov", (byte) 12);
        List<User> userList1 = userService.getAllUsers();
        userList1.forEach(System.out::println);
        userService.removeUserById(1);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
