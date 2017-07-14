package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;


/**
 * Created by Андрей on 13.07.2017.
 */
public class MealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealsWithExceed", MealsUtil.getFilteredWithExceeded(MealsUtil.getMeals(), LocalTime.of(0,0), LocalTime.of(23,59, 59), 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request,response);
    }
}
