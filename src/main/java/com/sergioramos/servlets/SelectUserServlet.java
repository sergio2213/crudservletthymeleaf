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

@WebServlet(name = "SelectUserServlet", urlPatterns = "/select")
public class SelectUserServlet extends HttpServlet {

    private UserController userController;

    @Override
    public void init() {
        UserDao userDao = new UserDaoImpl(DataSourceUtil.getDataSource(getServletContext()));
        ITemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());
        this.userController = new UserController(userDao, templateEngine);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.userController.showSelectForm(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.userController.showUserInfo(request, response);
    }
}
