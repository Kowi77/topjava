package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_TIME_FORMATTER;

/**
 * Created by Андрей on 03.08.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    private List<Meal> usersMeals = USERS_MEALS;
    private List<Meal> adminsMeals;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = usersMeals.get(0);
        MATCHER.assertEquals(meal, service.get(meal.getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetWrongId() throws Exception {
        Meal meal = usersMeals.get(0);
        MATCHER.assertEquals(meal, service.get(meal.getId(), ADMIN_ID));
    }

    @Test
    public void testDelete() throws Exception {
        int id = usersMeals.get(0).getId();
        usersMeals.remove(0);
        usersMeals = usersMeals.stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
        service.delete(id, USER_ID);
        MATCHER.assertCollectionEquals(usersMeals, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongId() throws Exception {
        service.delete(100003, ADMIN_ID);
    }



    @Test
    public void testGetBetweenDates() throws Exception {
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
    }

    @Test
    public void testGetAll() throws Exception {
        usersMeals = usersMeals.stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
        MATCHER.assertCollectionEquals(usersMeals, service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal editedMeal = new Meal (100003, LocalDateTime.parse("2017-08-02 13:40", DATE_TIME_FORMATTER), "Тефтели",1020);
        usersMeals.set(1, editedMeal);
        Meal createdMeal = service.update(editedMeal, USER_ID);
        usersMeals = usersMeals.stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
        MATCHER.assertCollectionEquals(usersMeals, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongId() throws Exception {
        Meal editedMeal = new Meal (100003, LocalDateTime.parse("2017-08-02 13:40", DATE_TIME_FORMATTER), "Тефтели",1020);
        service.save(editedMeal, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "smth",111);
        Meal createdMeal = service.save(newMeal, USER_ID);
        newMeal.setId(createdMeal.getId());
        usersMeals.add(newMeal);
        usersMeals = usersMeals.stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
        MATCHER.assertCollectionEquals(usersMeals, service.getAll(USER_ID));
    }

}