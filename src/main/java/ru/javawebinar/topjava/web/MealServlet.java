package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoImplInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Андрей on 13.07.2017.
 */
public class MealServlet extends HttpServlet {

    private ServletConfig config;
    public void init (ServletConfig config) throws ServletException
    {
        this.config = config;
    }
    private MealsDao dao = new MealsDaoImplInMemory();
    private static final Logger log = getLogger(MealServlet.class);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("id") == null ){
            dao.add(LocalDateTime.parse(request.getParameter("dateTime"), formatter), request.getParameter("description"),Integer.parseInt(request.getParameter("calories")));
            log.debug("Meal was added");}
        else {
            dao.update(Integer.parseInt(request.getParameter("id")), LocalDateTime.parse(request.getParameter("dateTime"), formatter), request.getParameter("description"),Integer.parseInt(request.getParameter("calories")));
            log.debug("Meal was edited");
        }
        goMealsList(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action=request.getParameter("action");

        if (action == null){
            goMealsList(request, response);
        }

        else if(action.equalsIgnoreCase("delete")){
            dao.delete(Integer.parseInt(request.getParameter("mealId")));
            log.debug("Meal with ID = " +  request.getParameter("mealId") + " was deleted");
            response.sendRedirect("meals");
        }

        else if(action.equalsIgnoreCase("edit")){
            log.debug("Meal with ID = " +  request.getParameter("mealId") + " prepare to be edit");
            request.setAttribute("mealForEdit", dao.getById(Integer.parseInt(request.getParameter("mealId"))));
            goMealsList(request, response);
        }
        else{
            goMealsList(request, response);
        }
    }

    public void goMealsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealsWithExceed", MealsUtil.getFilteredWithExceeded(dao.getAll(), LocalTime.of(0,0), LocalTime.of(23,59, 59), 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request,response);
    }
}
