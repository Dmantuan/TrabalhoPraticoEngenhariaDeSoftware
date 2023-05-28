package principal;

import presenter.MainPresenter;

public class Principal {
    public static void main( String[] args ){
        MainPresenter mainPresenter = MainPresenter.getInstance();
        mainPresenter.MainFramePresenter();
    }
}
