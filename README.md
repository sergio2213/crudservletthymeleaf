# Gestión de usuarios (CRUD App)
## Se utilizó:
- Jakarta Servlet 6.0
- Thymeleaf 3.1
- Bootstrap 5
- MySQL 8
- Tomcat 10.1.18

## A tener en cuenta:
- Reemplazar usuario y contraseña de su DB en com.sergioramos.config.HikariCpConfig.contextInitialized
`config.setUsername("your_username")`
`config.setPassword("your_password")`
- Los scripts de creación e inserción se encuentran en src/main/resources/scripts