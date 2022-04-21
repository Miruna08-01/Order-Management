package Bll.validators;

import model.Orders;
import model.Product;

public class PositiveNumberOrder implements Validator<Orders> {

    @Override
    public void validate(Orders orders) {
        if(orders.getQuantity()<0 ){
            try {
                throw new Exception("Wrong quantity");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



