package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.menu.*;
import org.example.menu.loggedin.LoanRepaymentPage;
import org.example.menu.loggedin.LoggedInMenu;
import org.example.menu.loggedin.PayLoanPage;
import org.example.menu.registerloan.*;
import org.example.menu.util.Input;
import org.example.menu.util.Message;
import org.example.repositories.card.CardRepo;
import org.example.repositories.card.CardRepoImpl;
import org.example.repositories.loan.LoanRepo;
import org.example.repositories.loan.LoanRepoImpl;
import org.example.repositories.loan.housing.HousingLoanRepo;
import org.example.repositories.loan.housing.HousingLoanRepoImpl;
import org.example.repositories.loan.installments.InstallmentRepo;
import org.example.repositories.loan.installments.InstallmentRepoImpl;
import org.example.repositories.loan.study.StudyLoanRepo;
import org.example.repositories.loan.study.StudyLoanRepoImpl;
import org.example.repositories.loan.tuition.TuitionLoanRepo;
import org.example.repositories.loan.tuition.TuitionLoanRepoImpl;
import org.example.repositories.student.StudentRepo;
import org.example.repositories.student.StudentRepoImpl;
import org.example.services.card.CardService;
import org.example.services.card.CardServiceImpl;
import org.example.services.loan.LoanService;
import org.example.services.loan.LoanServiceImpl;
import org.example.services.loan.housing.HousingLoanService;
import org.example.services.loan.housing.HousingLoanServiceImpl;
import org.example.services.loan.installment.InstallmentService;
import org.example.services.loan.installment.InstallmentServiceImpl;
import org.example.services.loan.tuitionloan.TuitionLoanService;
import org.example.services.loan.tuitionloan.TuitionLoanServiceImpl;
import org.example.services.student.StudentService;
import org.example.services.student.StudentServicempl;
import org.example.services.loan.studyloan.StudyLoanService;
import org.example.services.loan.studyloan.StudyLoanServiceImpl;

public class ApplicationContext {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static ApplicationContext applicationContext;
    public static Menu menu;
    private ApplicationContext() {


        Input input=new Input();
        Message message= new Message();
        MainMenu mainMenu= new MainMenu();
        AuthHolder authHolder = new AuthHolder();

        StudentRepo studentRepo =new StudentRepoImpl(getEntityManager());
        StudentService studentService = new StudentServicempl(studentRepo);
        StudyLoanRepo studyLoanRepo= new StudyLoanRepoImpl(getEntityManager());
        StudyLoanService studyLoanService= new StudyLoanServiceImpl(studyLoanRepo);
        CardRepo cardrepo= new CardRepoImpl(getEntityManager());
        CardService cardService = new CardServiceImpl(cardrepo);
        CardPage cardPage= new CardPage(input,message,authHolder,cardService);
        StudyLoanPage studyLoanPage = new StudyLoanPage(authHolder,input,message,studyLoanService,cardService,cardPage);
       HousingLoanRepo housingLoanRepo= new HousingLoanRepoImpl(getEntityManager());
        HousingLoanService housingLoanService= new HousingLoanServiceImpl(housingLoanRepo,studentRepo);
        HousingLoanPage housingLoanPage= new HousingLoanPage(authHolder,message,input,housingLoanService,cardService,cardPage);
        TuitionLoanRepo tuitionLoanRepo = new TuitionLoanRepoImpl(getEntityManager());
        TuitionLoanService tuitionLoanService= new TuitionLoanServiceImpl(tuitionLoanRepo);
        TuitionLoanPage tuitionLoanPage=new TuitionLoanPage(authHolder,tuitionLoanService,cardPage,message,input);
        RegisterForLoanPage registerForLoanPage= new RegisterForLoanPage(message,input,studyLoanPage,housingLoanPage,tuitionLoanPage,authHolder);
        PayLoanPage payLoanPage= new PayLoanPage();
        LoanRepo loanRepo= new LoanRepoImpl(getEntityManager());
        LoanService loanService= new LoanServiceImpl(loanRepo);
        InstallmentRepo installmentRepo= new InstallmentRepoImpl(getEntityManager());
        InstallmentService installmentService= new InstallmentServiceImpl(installmentRepo);
        LoanRepaymentPage loanRepaymentPage=new LoanRepaymentPage(authHolder,input,message,payLoanPage,loanService,installmentService);
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
