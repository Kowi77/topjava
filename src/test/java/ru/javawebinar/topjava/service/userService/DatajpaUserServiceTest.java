package ru.javawebinar.topjava.service.userService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;
import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Андрей on 14.08.2017.
 */
@ActiveProfiles(profiles = DATAJPA)
public class DatajpaUserServiceTest extends AbstractUserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testGetWithMeals() throws Exception {
        MEALS.sort((Meal m1, Meal m2) -> m1.getId()-m2.getId());
        MATCHER.assertCollectionEquals(MEALS, service.getWithMeals(USER_ID).getMeals());
    }
}
