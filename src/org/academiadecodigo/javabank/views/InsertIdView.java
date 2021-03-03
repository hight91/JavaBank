package org.academiadecodigo.javabank.views;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.domain.Bank;

public class InsertIdView {

    private Prompt prompt;

    public InsertIdView() {
        prompt = new Prompt(System.in, System.out);
    }

    /**
     * @param bank the bank instance
     *
     * receives input userID from user through the prompt
     * sends input to BankApp
     *
     * @return int prompt
     * */
    public int inputMessage(Bank bank){

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        return prompt.getUserInput(scanner);
    }

}
