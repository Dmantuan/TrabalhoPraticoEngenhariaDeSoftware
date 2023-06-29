package ufes.main;

import ufes.presenter.MainPresenter;

public class Main {
    public static void main( String[] args ){
        MainPresenter mainPresenter = MainPresenter.getInstance();
        mainPresenter.MainFramePresenter();
    }
}
