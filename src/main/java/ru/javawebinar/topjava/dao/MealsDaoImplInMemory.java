package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Андрей on 14.07.2017.
 */
public class MealsDaoImplInMemory implements MealsDao{

    private static AtomicInteger id;
    private static Map<Integer, Meal> mealsMap; //DB imitation field
    private  static List<Meal> meals = Arrays.asList(   //Initial values
            new Meal(LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Конфеты", 500),
            new Meal(LocalDateTime.of(2017, Month.MAY, 30, 13, 0), "Котлеты", 1000),
            new Meal(LocalDateTime.of(2017, Month.MAY, 30, 20, 0), "Ужин", 490),
            new Meal(LocalDateTime.of(2017, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2017, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2017, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public MealsDaoImplInMemory() {
        //DB hardcode filling
        id = new AtomicInteger();
        id.set(1);
        mealsMap = new ConcurrentHashMap<>();
        for(Meal meal: meals){
            mealsMap.put(id.get(), meal);
            meal.setId(id.getAndIncrement());
        }
    }

    @Override
    public synchronized void addMeal(Meal meal) {
        mealsMap.put(id.get(), meal);
        meal.setId(id.getAndIncrement());
    }

    @Override
    public void deleteMeal(int mealId) {
        try {
            mealsMap.remove(mealId);
        } catch (Exception e) {
        }
    }

    @Override
    public synchronized void updateMeal(Meal meal) {
        mealsMap.put(meal.getId(), meal);
    }

    @Override
    public synchronized List<Meal> getAllMeals() {
        return  new ArrayList(mealsMap.values());
    }

    @Override
    public synchronized Meal getMealById(int mealId) {
        if (mealsMap.containsKey(mealId))
                return mealsMap.get(mealId);
        else return null;
    }
}
