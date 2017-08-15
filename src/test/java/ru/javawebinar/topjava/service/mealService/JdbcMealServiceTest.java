package ru.javawebinar.topjava.service.mealService;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.*;
/**
 * Created by Андрей on 14.08.2017.
 */
@ActiveProfiles(profiles = JDBC)
public class JdbcMealServiceTest extends AbstarctMealServiceTest {

    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Override
    public void testCreate() throws Exception {
        super.testCreate();
    }

    @Override
    public void testGet() throws Exception {
        super.testGet();
    }

    @Override
    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
    }

    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Override
    public void testUpdateNotFound() throws Exception {
        super.testUpdateNotFound();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testGetBetween() throws Exception {
        super.testGetBetween();
    }
}
