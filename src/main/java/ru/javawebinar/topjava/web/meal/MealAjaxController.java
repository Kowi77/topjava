package ru.javawebinar.topjava.web.meal;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = MealAjaxController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealAjaxController extends AbstractMealController{
    static final String REST_URL = "/ajax/meals";

    @Override
    @GetMapping("/{id}")
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping
    public void createOrUpdate(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam("dateTime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories) {
        Meal meal = new Meal(id, LocalDateTime.parse(dateTime), description, calories);
        if (meal.isNew()) {
            super.create(meal);
        }
    }

    @Override
    @GetMapping(value = "/filter")
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
