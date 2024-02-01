package com.sergioramos.servlets;

import com.sergioramos.config.DataSourceUtil;
import com.sergioramos.config.TemplateEngineUtil;
import com.sergioramos.controllers.UserController;
import com.sergioramos.dao.UserDao;
import com.sergioramos.dao.UserDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;

import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", urlPatterns = "/update")
public class UpdateUserServlet extends HttpServlet {

    private UserController userController;

    @Override
    public void init() {
        UserDao userDao = new UserDaoImpl(DataSourceUtil.getDataSource(getServletContext()));
        ITemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());
        this.userController = new UserController(userDao, templateEngine);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.userController.updateUser(request, response);
    }
}
