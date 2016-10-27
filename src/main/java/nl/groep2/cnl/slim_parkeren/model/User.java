package nl.groep2.cnl.slim_parkeren.model;

import java.security.Principal;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "Users", noClassnameStored = true)
public class User extends EntityModel implements Principal
{
    @NotEmpty
    private String name;
    
    @NotEmpty
    @Length(min = 3, max = 10, message = "Please choose a password with at least 3 characters and at most 10 characters.")
    private String password;
        
    private List<String> roles;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
		this.password = password;
    }

    public List<String> getRoles(){
        return roles;
    }

    public void setRoles(List<String> roles){
        this.roles = roles;
    }

    public boolean hasRole(String role){
        return roles.contains(role);
    }
}
