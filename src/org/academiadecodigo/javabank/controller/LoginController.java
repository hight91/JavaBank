package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.services.AuthServices;
import org.academiadecodigo.javabank.services.CustomerServices;
import org.academiadecodigo.javabank.view.LoginView;

import java.util.Set;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;

    private Bank bank;
    private AuthServices authServices;
    private CustomerServices customerServices;

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    /**
     * Sets the bank
     *
     * @param bank the bank to be set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {

        authServices.authenticate(id);
        nextController.init();
    }

    public void setAuthServices(AuthServices authServices) {
        this.authServices = authServices;
    }

    public void setCustomerServices(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    public Set<Integer> listCustomerAccountIds(){
        return customerServices.listCustomerAccountIds();
    }

    public Bank getBank() {
        return bank;
    }
}
