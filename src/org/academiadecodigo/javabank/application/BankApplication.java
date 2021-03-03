package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.application.operations.BalanceOperation;
import org.academiadecodigo.javabank.application.operations.NewAccountOperation;
import org.academiadecodigo.javabank.application.operations.Operation;
import org.academiadecodigo.javabank.application.operations.transaction.DepositOperation;
import org.academiadecodigo.javabank.application.operations.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.views.InsertIdView;
import org.academiadecodigo.javabank.views.OptionsMenuView;

import java.util.HashMap;
import java.util.Map;

/**
 * The bank application
 */
public class BankApplication {


    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;


    /** VIEWS */
    private InsertIdView idView;
    private OptionsMenuView menuView;

    /**
     * Creates a new instance of a {@code BankApplication}, initializes it with the given {@link Bank}
     *
     * @param bank the bank instance
     */
    public BankApplication(Bank bank) {
        this.bank = bank;

        idView = new InsertIdView();
        menuView = new OptionsMenuView();


    }

    /**
     * Gets the bank used by this application
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Gets the id of the customer using the Bank Application
     *
     * @return the customer id
     */
    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    /**
     * Starts the bank application
     */
    public void start() {

        mainMenu = buildMainMenu();
        accessingCustomerId = scanCustomerId();
        operationsMap = buildOperationsMap();
        menuLoop();
    }

    private void menuLoop(){

        int userChoice = menuView.getUserOption(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();
    }

    private int scanCustomerId() {
        return idView.inputMessage(bank);
    }

    private MenuInputScanner buildMainMenu() {
        return menuView.sendOptionsMenu();
    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;
    }
}
