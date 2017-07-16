package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoImplInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



/**
 * Created by Андрей on 13.07.2017.
 */
public class MealServlet extends HttpServlet {

    private MealsDao dao = new MealsDaoImplInMemory();

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter), request.getParameter("description"),Integer.parseInt(request.getParameter("calories")));
        dao.addMeal(meal);
        goMealsList(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward="";
        String action=request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("mealsList")){
            goMealsList(request, response);
        }

        else if(action.equalsIgnoreCase("delete")){
            dao.deleteMeal(Integer.parseInt(request.getParameter("mealId")));
            goMealsList(request, response);
        }

        else if(action.equalsIgnoreCase("edit")){
            Meal meal = dao.getMealById(Integer.parseInt(request.getParameter("mealId")));
            dao.updateMeal(new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter), request.getParameter("description"), Integer.parseInt(request.getParameter("calories"))));
            goMealsList(request, response);
        }

        request.getRequestDispatcher(forward).forward(request,response);
    }

    public void goMealsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealsWithExceed", MealsUtil.getFilteredWithExceeded(dao.getAllMeals(), LocalTime.of(0,0), LocalTime.of(23,59, 59), 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request,response);
    }
}
