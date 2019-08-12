package it.unibo.ndgg.controller;

import it.unibo.ndgg.view.View;

public class MainControllerImpl implements MainController {

    private final View view;

    
    public MainControllerImpl(View view) {
        this.view = view;
    }

    @Override
    public void startNewGame() {
        new GameControllerImpl(view.showGame(), this);
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }

}
