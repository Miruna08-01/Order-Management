package Bll;


import Bll.validators.EmailValidator;
import Bll.validators.PositiveNumberOrder;
import Bll.validators.Validator;
import Dao.ClientDao;
import Dao.OrdersDAO;
import Dao.ProductDao;
import Presentation.OrderView;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdersBll {
    private OrdersDAO orderDao;
    List<Orders> orders = new ArrayList<>();
    JTable orderTable = new JTable();
    OrderView orderView;
    private List<Validator<Orders>> validators;
    ArrayList<Client> clients=new ArrayList<>();
    ArrayList<Product> products=new ArrayList<>();
    ClientDao clientDao=new ClientDao();
    ProductDao productDao=new ProductDao();

    public OrdersBll() {
        this.orderDao = new OrdersDAO();
        validators = new ArrayList<>();
        validators.add(new PositiveNumberOrder());
    }
    public JTable findAllOrders() throws Exception {
        this.orders=orderDao.findAll();
        if(orders.size()==0) {
            throw new Exception("Nu exista clienti");
        }
        this.orderTable=orderDao.createTable(orders);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;

    }
    public JTable findById(int id) throws Exception {
        this.orders = orderDao.findById(id);
        if(orders.size()==0) {
            throw new Exception("Nu exista comenzi cu id-ul: "+id);
        }
        this.orderTable=orderDao.createTable(orders);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;

    }

    public JTable findByIdClientOrProduct(String k,String id) throws Exception {
        this.orders = orderDao.findByField(k,id);
        if(orders.size()==0) {
            throw new Exception("Nu exista comenzi cu clientul cu id-ul : "+id);
        }
        this.orderTable=orderDao.createTable(orders);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;
    }
    public JTable findByQuantity(String k,String cantitate) throws Exception {
        this.orders = orderDao.findByField(k,cantitate);
        if(orders.size()==0) {
            throw new Exception("Nu exista comanda cu cantitatea "+cantitate);
        }
        this.orderTable=orderDao.createTable(orders);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;
    }

    public JTable insert(Orders order) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Validator<Orders> i : validators) {
            i.validate(order);
        }
        orderDao.insert(order);
        this.orders=orderDao.findAll();
        this.orderTable=orderDao.createTable(orders);
        clients= (ArrayList<Client>) clientDao.findAll();
        products= (ArrayList<Product>) productDao.findAll();
        FileReader file= null;
        try {
            file = new FileReader("number.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner myReader = new Scanner(file);
        String number="";
        while (myReader.hasNext())
        {
            number= String.valueOf(myReader.nextInt());
        }
        FileWriter writeNumber= null;
        try {
            writeNumber = new FileWriter("number.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter("order"+number+".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<clients.size();i++) {
            if(order.getId_client()==clients.get(i).getId()) {
                try {
                    writer.write("Numele clientului este: " + clients.get(i).getName() + "\n");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i=0;i<products.size();i++){
            if(order.getId_product()==products.get(i).getId())
            {
                try {
                    writer.write("Numele produsului este "+products.get(i).getName()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                float total=order.getQuantity()*products.get(i).getPrice();
                try {
                    writer.write("Cantitate "+order.getQuantity()+"\n"+"Pretul pe produs: "+String.valueOf(products.get(i).getPrice()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.write("\nPretul total "+String.valueOf(total));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int newValue=Integer.parseInt(number)+1;
                products.get(i).setStock(products.get(i).getStock()-order.getQuantity());
                productDao.update(products.get(i),"stock",String.valueOf(products.get(i).getStock()),products.get(i).getId());
                try {
                    writeNumber.write(String.valueOf(newValue));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeNumber.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;
    }

    public JTable deleteById(Orders o, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        orderDao.delete(o,"id",k);
        this.orders=orderDao.findAll();
        this.orderTable=orderDao.createTable(orders);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);
        return this.orderTable;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
