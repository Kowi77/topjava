package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
       // MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.getUserId() != userId)
            return null;
        else if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        if (repository.get(id).getUserId() != AuthorizedUser.id())
            return;
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        if (repository.get(id).getUserId() != AuthorizedUser.id())
            return null;
        else
            return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream().
                filter(meal -> meal.getUserId() == AuthorizedUser.id()).
                sorted(Comparator.comparing(Meal::getDateTime).reversed()).
                collect(Collectors.toList());
    }
}

