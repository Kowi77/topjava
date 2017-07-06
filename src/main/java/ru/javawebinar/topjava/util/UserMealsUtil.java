package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceededWithStream(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
    }
    // First
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> map = new TreeMap<>();
        for (UserMeal meal : mealList) {
            map.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (a, b) -> a + b);
        }

        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal meal : mealList) {
            list.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay && isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)));
        }
        return list;
    }

    //Second
    public static List<UserMealWithExceed> getFilteredWithExceededWithStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = mealList.stream()
                .collect(Collectors.toMap(p -> p.getDateTime().toLocalDate(), UserMeal::getCalories, (x, y) -> x + y));

        return mealList.stream()
                .map(s -> new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), map.get(s.getDateTime().toLocalDate()) > caloriesPerDay && isBetween(s.getDateTime().toLocalTime(), startTime, endTime)))
                .collect(Collectors.toList());
    }

}
