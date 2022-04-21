package Bll.validators;

import model.Client;
import model.Product;

public class PositiveNumber implements Validator<Product> {

    @Override
    public void validate(Product product) {
        if(product.getPrice()<0 || product.getStock()<0){
            try {
                throw new Exception("Wrong price or stock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
