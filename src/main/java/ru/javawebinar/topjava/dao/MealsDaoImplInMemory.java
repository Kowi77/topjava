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

    private AtomicInteger id = new AtomicInteger();
    private Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>(); //DB imitation field

    public MealsDaoImplInMemory() {
        id.set(1);
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Конфеты", 500));
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 30, 13, 0), "Котлеты", 1000));
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 30, 20, 0), "Ужин", 490));
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 31, 13, 0), "Обед", 500));
        mealsMap.put(id.get(), new Meal(id.getAndIncrement(), LocalDateTime.of(2017, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void add (Meal meal) {
        meal.setId(id.get());
        mealsMap.put(id.getAndIncrement(), meal);
    }

    @Override
    public void delete(int mealId) { mealsMap.remove(mealId); }

    @Override
    public void update(Meal meal) { mealsMap.put(meal.getId(), meal); }

    @Override
    public List<Meal> getAll() {
        return  new ArrayList<>(mealsMap.values());
    }

    @Override
    public Meal getById(int mealId) { return mealsMap.get(mealId); }
}
