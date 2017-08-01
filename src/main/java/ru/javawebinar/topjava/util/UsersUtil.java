package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.Role.*;

/**
 * Created by Андрей on 23.07.2017.
 */
public class UsersUtil {

     public static final List<User> USERS = Arrays.asList(
             new User(null, "Андрей", "kov@mail.ru", "123", ROLE_ADMIN, ROLE_USER),
             new User(null, "Юля", "yul@mail.ru", "456", ROLE_USER, ROLE_USER),
             new User(null, "Златан", "zlat@mail.ru", "789", ROLE_USER, ROLE_USER)
     );

}
