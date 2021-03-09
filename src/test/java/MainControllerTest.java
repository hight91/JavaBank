import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MainController;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.MainView;
import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(JUnitParamsRunner.class)
public class MainControllerTest {
    MainController mainController = new MainController();
    Map<Integer, Controller> controllerMap = new HashMap<>();
    MainView view = mock(MainView.class);


    @Before
    public void setup(){

        mainController.setControllerMap(controllerMap);
        mainController.setView(view);

    }


    @Test
    @Parameters ({"5"})
    public void onMenuSelectionUserChoosesQuitOption(int option){

        Assert.assertEquals(5, option);
        Assert.assertEquals(option, UserOptions.QUIT.getOption());
        verify(controllerMap, never()).get(option);
        verify(controllerMap, never()).containsKey(option);
        verify(view,never()).show();


    }


}
