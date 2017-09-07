package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";


    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal create(@RequestBody Meal meal) {
        return super.create(meal);
    }

    @GetMapping(value = "/between", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@RequestParam(name = "startDate", required = false) String startDate,
                                            @RequestParam(name = "startTime", required = false) String startTime,
                                            @RequestParam(name = "endDate", required = false) String endDate,
                                            @RequestParam(name = "endTime", required = false) String endTime) {
        return super.getBetween(startDate == null ? null : JsonUtil.readValue(startDate, LocalDate.class),
                                 startTime == null ? null : JsonUtil.readValue(startTime, LocalTime.class),
                                 endDate == null ? null : JsonUtil.readValue(endDate, LocalDate.class),
                                 endTime == null ? null : JsonUtil.readValue(endTime, LocalTime.class));
    }
}
