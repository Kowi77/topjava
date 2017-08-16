package ru.javawebinar.topjava.service.userService;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;
import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;

/**
 * Created by Андрей on 14.08.2017.
 */
@ActiveProfiles(profiles = DATAJPA)
public class DatajpaUserServiceTest extends AbstractUserServiceTest {

}
