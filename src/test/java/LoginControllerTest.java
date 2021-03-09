import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.academiadecodigo.javabank.Bootstrap;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.LoginView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;


@RunWith(JUnitParamsRunner.class)
public class LoginControllerTest {
    LoginController loginController = new LoginController();
    AuthService authService = mock(AuthService.class);
    Controller controller = mock(Controller.class);
    LoginView loginView = mock(LoginView.class);


    @Before
    public void setup(){
        loginController.setAuthService(authService);
        loginController.setNextController(controller);
        loginController.setView(loginView);


    }

    @Test
    @Parameters ({"1","234","1000"})
    public void testIfAuthenticateWhenIsTrue(int id){
        setup();
        when(authService.authenticate(id)).thenReturn(true);
        loginController.onLogin(id);
        verify(authService).authenticate(id);
        verify(controller).init();
    }

    @Test
    @Parameters ({"1","234","1000"})
    public void testIfAuthenticateWhenIsFalse(int id){
        setup();
        when(authService.authenticate(id)).thenReturn(false);
        authService.authenticate(id);
        verify(authService).authenticate(id);
        verify(controller, never()).init();
        loginController.init();
        verify(loginView).show();
    }



}
