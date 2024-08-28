package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.menu.*;
import org.example.menu.loggedin.LoanRepaymentPage;
import org.example.menu.loggedin.LoggedInMenu;
import org.example.menu.registerloan.HousingLoanPage;
import org.example.menu.registerloan.RegisterForLoanPage;
import org.example.menu.registerloan.StudyLoanPage;
import org.example.menu.registerloan.TuitionLoanPage;
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
        StudyLoanPage studyLoanPage = new StudyLoanPage(authHolder);
        HousingLoanPage housingLoanPage= new HousingLoanPage(authHolder,message,input);
        TuitionLoanPage tuitionLoanPage=new TuitionLoanPage();
        RegisterForLoanPage registerForLoanPage= new RegisterForLoanPage(message,input,studyLoanPage,housingLoanPage,tuitionLoanPage,authHolder);
        LoanRepaymentPage loanRepaymentPage=new LoanRepaymentPage();
        LoggedInMenu loggedInMenu= new LoggedInMenu(authHolder,message,input,registerForLoanPage,loanRepaymentPage);
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
