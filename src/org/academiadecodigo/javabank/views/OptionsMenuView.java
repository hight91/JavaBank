package org.academiadecodigo.javabank.views;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.domain.Bank;

public class OptionsMenuView {

    private Prompt prompt;
    public OptionsMenuView() {
        prompt = new Prompt(System.in, System.out);
    }

    /**
     * generating a menu
     * setting question and error prints
     *
     * @return MenuInputScanner mainMenu
     * */
    public MenuInputScanner sendOptionsMenu(){
        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);
        return mainMenu;
    }
    /**
     * receives option through scanner
     * displays all available options
     * send int response

     * @return int prompt
     * */
    public int getUserOption(MenuInputScanner mainMenu){
        return prompt.getUserInput(mainMenu);
    }

}
