package Bll.validators;

import model.Client;

public class NameValidator implements Validator<Client>{
    @Override
    public void validate(Client client) {
        for(int i=0;i<client.getName().length();i++){
            if(Character.isDigit(client.getName().charAt(i))){
                try {
                    throw new Exception("Incorect client name");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
