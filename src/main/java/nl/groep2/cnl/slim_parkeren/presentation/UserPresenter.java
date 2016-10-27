package nl.groep2.cnl.slim_parkeren.presentation;

import java.util.ArrayList;
import java.util.List;

import nl.groep2.cnl.slim_parkeren.model.User;
import nl.groep2.cnl.slim_parkeren.presentation.model.UserView;


public class UserPresenter extends BasePresenter
{
    public UserView present( User user )
    {
        UserView view = new UserView();
        
        view.id = user.getId().toString();
        view.name = user.getName();
                
        return view;
    }

    public List<UserView> present( List<User> users )
    {
        List<UserView> views = new ArrayList<UserView>();
        
        for( User user : users )
            views.add( present( user ) );
        
        return views;
    }
}
