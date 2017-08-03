package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_TIME_FORMATTER;

public class MealTestData {
    public static final List<Meal> USERS_MEALS = new ArrayList<Meal>(Arrays.asList(
          new Meal (100002, LocalDateTime.parse("2017-08-02 07:20", DATE_TIME_FORMATTER), "Конфеты", 550),
          new Meal (100003, LocalDateTime.parse("2017-08-02 13:40", DATE_TIME_FORMATTER), "Котлеты",1020),
          new Meal (100004, LocalDateTime.parse("2017-08-02 18:30", DATE_TIME_FORMATTER), "Ужин", 510),
          new Meal (100005, LocalDateTime.parse("2017-08-01 06:20", DATE_TIME_FORMATTER), "Завтрак", 450),
          new Meal (100006, LocalDateTime.parse("2017-08-01 14:40", DATE_TIME_FORMATTER), "Обед", 820),
          new Meal (100007, LocalDateTime.parse("2017-08-01 20:30", DATE_TIME_FORMATTER), "Ужин  в ресторане",530)));

    public static final List<Meal> ADMINS_MEALS = new ArrayList<Meal>(Arrays.asList(
          new Meal (100008, LocalDateTime.parse("2017-08-01 08:30", DATE_TIME_FORMATTER), "Завтрак админа", 1000),
          new Meal (100009, LocalDateTime.parse("2017-08-01 20:35", DATE_TIME_FORMATTER), "Ужин админа", 1001)));

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
            (Objects.equals(expected.getDateTime(), actual.getDateTime())
            && Objects.equals(expected.getId(), actual.getId())
            && Objects.equals(expected.getDescription(), actual.getDescription())
            && Objects.equals(expected.getCalories(), actual.getCalories())
    ));

}
