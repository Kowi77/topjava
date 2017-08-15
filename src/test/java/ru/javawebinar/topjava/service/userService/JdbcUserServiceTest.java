package ru.javawebinar.topjava.service.userService;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.*;

/**
 * Created by Андрей on 14.08.2017.
 */
@ActiveProfiles(profiles = JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
    @Override
    public void testCreate() throws Exception {
        super.testCreate();
    }

    @Override
    public void testDuplicateMailCreate() throws Exception {
        super.testDuplicateMailCreate();
    }

    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testNotFoundDelete() throws Exception {
        super.testNotFoundDelete();
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
    public void testGetByEmail() throws Exception {
        super.testGetByEmail();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }
}