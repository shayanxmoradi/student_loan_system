package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.menu.*;
import org.example.menu.loggedin.LoggedInMenu;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.repositories.student.StudentRepo;
import org.example.repositories.student.StudentRepoImpl;
import org.example.services.student.StudentService;
import org.example.services.student.StudentServiceImpl;

public class ApplicationContext {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static ApplicationContext applicationContext;
    public static Menu menu;
    private ApplicationContext() {


        Input input=new Input();
        Message message= new Message();
        MainMenu mainMenu= new MainMenu();

        StudentRepo studentRepo =new StudentRepoImpl(getEntityManager());
        StudentService studentService = new StudentServiceImpl(studentRepo);
        AuthHolder authHolder = new AuthHolder();
        LoggedInMenu loggedInMenu= new LoggedInMenu(authHolder);
        LoginMenu loginMenu= new LoginMenu(input,message,studentService,authHolder,loggedInMenu);

        SignUpMenu signUpMenu= new SignUpMenu(input,message,studentService);
        menu = new Menu(input,message,mainMenu,loginMenu,signUpMenu);

    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("postgresdb");
        }
        return emf;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }


    public Menu getMenu() {
        return menu;
    }
}
