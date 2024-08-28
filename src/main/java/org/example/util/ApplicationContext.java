package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Card;
import org.example.menu.*;
import org.example.menu.loggedin.LoanRepaymentPage;
import org.example.menu.loggedin.LoggedInMenu;
import org.example.menu.registerloan.*;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.repositories.baseentity.BaseEntityRepo;
import org.example.repositories.baseentity.BaseEntityRepoImpl;
import org.example.repositories.card.CardRepo;
import org.example.repositories.card.CardRepoImpl;
import org.example.repositories.loan.study.StudyLoanRepo;
import org.example.repositories.loan.study.StudyLoanRepoImpl;
import org.example.repositories.student.StudentRepo;
import org.example.repositories.student.StudentRepoImpl;
import org.example.services.baseentity.BaseEntityService;
import org.example.services.baseentity.BaseEntityServiceImp;
import org.example.services.card.CardService;
import org.example.services.card.CardServiceImpl;
import org.example.services.student.StudentService;
import org.example.services.student.StudentServicempl;
import org.example.services.studyloan.StudyLoanService;
import org.example.services.studyloan.StudyLoanServiceImpl;

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
        StudentService studentService = new StudentServicempl(studentRepo);
        AuthHolder authHolder = new AuthHolder();
        StudyLoanRepo studyLoanRepo= new StudyLoanRepoImpl(getEntityManager());
        StudyLoanService studyLoanService= new StudyLoanServiceImpl(studyLoanRepo);
        CardRepo cardrepo= new CardRepoImpl(getEntityManager());
        CardService cardService = new CardServiceImpl(cardrepo);
        CardPage cardPage= new CardPage(input,message,authHolder,cardService);
        StudyLoanPage studyLoanPage = new StudyLoanPage(authHolder,input,message,studyLoanService,cardService,cardPage);
        HousingLoanPage housingLoanPage= new HousingLoanPage(authHolder,message,input);
        TuitionLoanPage tuitionLoanPage=new TuitionLoanPage(authHolder);
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
