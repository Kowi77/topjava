package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Андрей on 01.09.2017.
 */
@EnableWebMvc
public class MealRestControllerTest extends AbstractControllerTest {

    @Test
    public void getAllTest() throws Exception{
        List<MealWithExceed> expected = MealsUtil.getWithExceeded(MEALS, AuthorizedUser.getCaloriesPerDay());
        List<MealWithExceed> actual = JsonUtil.readValues(mockMvc.perform(get("/rest/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString(), MealWithExceed.class);
        MATCHER_WITH_EXCEED.assertListEquals(expected, actual);
    }


    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete("/rest/meals/" + MEAL4.getId()))
                .andDo(print())
                .andExpect(status().isOk());
        List<Meal> mealsAfterDelete = JsonUtil.readValues(mockMvc.perform(get("/rest/meals"))
                .andReturn().getResponse().getContentAsString(), MealWithExceed.class)
                .stream().map(m -> new Meal(m.getId(), m.getDateTime(), m.getDescription(), m.getCalories()))
                .collect(Collectors.toList());
        mealsAfterDelete.forEach(m -> MATCHER.assertNotEquals(m, MEAL4));
    }

    @Test
    public void testUpdate() throws Exception{
        Meal expected = MealTestData.getUpdated();

        mockMvc.perform(put("/rest/meals/" + MEAL1.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());
        System.out.println(JsonUtil.writeValue(expected));
        Meal actual = JsonUtil.readValue(mockMvc.perform(get("/rest/meals/" + MEAL1.getId()))
                .andReturn().getResponse().getContentAsString(), Meal.class);
        MATCHER.assertEquals(actual, expected);
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = JsonUtil.readValue(mockMvc.perform(get("/rest/meals/" + MEAL5.getId()))
                .andDo(print())
                .andReturn().getResponse().getContentAsString(), Meal.class);
        MATCHER.assertEquals(meal, MEAL5);
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = MealTestData.getCreated();
        System.out.println(JsonUtil.writeValue(expected));
        mockMvc.perform(post("/rest/meals")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());
        expected.setId(100010);
        Meal actual = JsonUtil.readValue(mockMvc.perform(get("/rest/meals/100010"))
                .andReturn().getResponse().getContentAsString(), Meal.class);
        MATCHER.assertEquals(actual, expected);
    }

    @Test
    public void testGetBetween() throws Exception{
        List<MealWithExceed> mealsWithExceedBetween = JsonUtil.readValues(mockMvc.perform(get("/rest/meals/between")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("startDate", JsonUtil.writeValue(LocalDate.of(2015, Month.MAY, 31)))
                .param("startTime", JsonUtil.writeValue(LocalTime.of(14, 0)))
                .param("endDate", JsonUtil.writeValue(LocalDate.of(2015, Month.JUNE, 1)))
                .param("endTime", JsonUtil.writeValue(LocalTime.of(23,0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), MealWithExceed.class);
        MATCHER_WITH_EXCEED.assertListEquals(mealsWithExceedBetween, MealsUtil.getWithExceeded(Arrays.asList(MEAL6), 10));

        //check with null params
        List<MealWithExceed> mealsWithExceedBetweenWithNull = JsonUtil.readValues(mockMvc.perform(get("/rest/meals/between")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("endDate", JsonUtil.writeValue(LocalDate.of(2015, Month.MAY, 30)))
                .param("endTime", JsonUtil.writeValue(LocalTime.of(14,0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), MealWithExceed.class);
        MATCHER_WITH_EXCEED.assertListEquals(mealsWithExceedBetweenWithNull, MealsUtil.getWithExceeded(Arrays.asList(MEAL2, MEAL1), 10000));


    }
}
