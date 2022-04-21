package Bll.validators;

import model.Client;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<Client>{
    private static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public void validate(Client client) {
        Pattern p=Pattern.compile(regex);
        if(p.matcher(client.getEmail()).matches()==false){
            try {
                throw new Exception("Incorecr client email");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
