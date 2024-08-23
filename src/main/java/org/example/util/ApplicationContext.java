package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.menu.LoginMenu;
import org.example.menu.MainMenu;
import org.example.menu.Menu;
import org.example.menu.SignUpMenu;
import org.example.menu.util.Input;
import org.example.menu.util.Message;

public class ApplicationContext {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static ApplicationContext applicationContext;
    public static Menu menu;
    private ApplicationContext() {


        Input input=new Input();
        Message message= new Message();
        MainMenu mainMenu= new MainMenu();
        LoginMenu loginMenu= new LoginMenu();
        SignUpMenu signUpMenu= new SignUpMenu();
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
            em = emf.createEntityManager();
        }
        return em;
    }


    public Menu getMenu() {
        return menu;
    }
}
