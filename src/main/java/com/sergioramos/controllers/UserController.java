package com.sergioramos.controllers;

import com.sergioramos.dao.UserDao;
import com.sergioramos.models.User;
import com.sergioramos.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class UserController {

    private final UserDao userDao;
    private final ITemplateEngine templateEngine;
    private WebContext webContext;

    public UserController(UserDao userDao, ITemplateEngine templateEngine) {
        this.userDao = userDao;
        this.templateEngine = templateEngine;
    }

    private void initializeWebContext(HttpServletRequest request, HttpServletResponse response) {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(request.getServletContext()).buildExchange(request, response);
        this.webContext = new WebContext(webExchange);
    }

    public void showUsersPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        List<User> users = this.userDao.findAll();
        this.webContext.setVariable("users", users);
        this.templateEngine.process("users", this.webContext, response.getWriter());
    }

    public void showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        this.templateEngine.process("create", this.webContext, response.getWriter());
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        initializeWebContext(request, response);
        String message;
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date birth = DateUtil.toUtilDate(request.getParameter("birth"));
        Character sex = request.getParameter("sex").charAt(0);
        User user = new User(firstName, lastName, email, password, birth, sex);
        if (this.userDao.add(user)) {
            message = "User added";
        } else {
            message = "Error adding user";
        }
        this.webContext.setVariable("message", message);
        this.templateEngine.process("information", this.webContext, response.getWriter());
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        String message;
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(this.userDao.findById(id) != null) {
            if(this.userDao.deleteById(id)) {
                message = "User deleted";
            } else {
                message = "Error deleting user";
            }
        } else {
            message = "User does not exist";
        }
        this.webContext.setVariable("message", message);
        this.templateEngine.process("information", this.webContext, response.getWriter());
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        String message;
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = this.userDao.findById(id);
        if (user != null) {
            String newFirstName = request.getParameter("firstname");
            String newLastName = request.getParameter("lastname");
            String newEmail = request.getParameter("email");
            String newPassword = request.getParameter("password");
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            if (this.userDao.edit(user)) {
                message = "User edited successfully";
            } else {
                message = "Error editing user";
            }
        } else {
            message = "User does not exist";
        }
        this.webContext.setVariable("message", message);
        this.templateEngine.process("information", this.webContext, response.getWriter());
    }

    public void showSelectForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        this.templateEngine.process("select", this.webContext, response.getWriter());
    }

    public void showUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        Integer userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userDao.findById(userId);
        if (user != null) {
            this.webContext.setVariable("user", user);
            boolean isEditable = Boolean.parseBoolean(request.getParameter("isEditable"));
            this.webContext.setVariable("isEditable", isEditable);
            this.templateEngine.process("userInfo", this.webContext, response.getWriter());
        } else {
            String message = "User does not exist";
            this.webContext.setVariable("message", message);
            this.templateEngine.process("information", this.webContext, response.getWriter());
        }

    }

    public void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        this.templateEngine.process("delete", this.webContext, response.getWriter());
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initializeWebContext(request, response);
        this.templateEngine.process("edit", this.webContext, response.getWriter());
    }

}
