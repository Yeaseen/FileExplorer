package sample;

/**
 * Created by Asus on 4/28/2017.
 */
public class FactoryView {

    public Views getView(int m)
    {

        if(m==0) return new GoGrid();
        else if(m==1) return new Tables();

        return null;

    }
}
