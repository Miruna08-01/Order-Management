package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class Controller {

    private View v;


    public Controller(View v) {
        this.v = v;
        v.clientButonListener(new ClientButonListener());
        v.produsButonListener(new ProdusButonListener());
        v.orderButonListener(new OrderButonListener());
    }

    class ClientButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();

            ClientView client = new ClientView();

        }
    }

    class ProdusButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();
            ProductView product = new ProductView();
        }
    }


    class OrderButonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            v.getFrame().dispose();
            OrderView order = new OrderView();
        }
    }

    public View getV() {
        return v;
    }


}
