package ru.javawebinar.topjava.service.mealService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.Profiles.*;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Андрей on 14.08.2017.
 */
@ActiveProfiles(profiles = DATAJPA)
public class DatajpaMealServiceTest extends AbstarctMealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void testGetWithUser() throws Exception {
        Meal meal = service.getWithUser(ADMIN_MEAL_ID);
        MATCHER.assertEquals(ADMIN, meal.getUser());
    }
}