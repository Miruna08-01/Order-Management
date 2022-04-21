package Presentation;

import Bll.ClientBll;
import Bll.OrdersBll;
import Dao.ClientDao;
import Dao.ProductDao;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {

    JFrame frame;
    JPanel panelPrincipal=new JPanel();
    JButton insert= new JButton("Insert");
    JButton selectAll= new JButton("Select All");
    JButton selectById= new JButton("Select By Id");
    JButton selectByIdClient= new JButton("Select By Client Id");
    JButton selectByIdProdus= new JButton("Select By Product Id");
    JButton selectByCantitate= new JButton("Select By Quantity");
    JButton update= new JButton("Update");
    JButton back= new JButton("Inapoi");
    JButton exit= new JButton("Exit");
    JButton deleteById=new JButton("Delete by Id");
    JTable orders=new JTable();
    OrdersBll order=new OrdersBll();
    ClientDao clientDao=new ClientDao();
    ProductDao productDao=new ProductDao();
    Orders orderO=new Orders();
    ArrayList<Client> clients=new ArrayList<>();
    ArrayList<Product> products=new ArrayList<>();
    public OrderView(){
        this.frame=new JFrame();
        this.frame.setSize(1100, 600);
        this.frame.setTitle("Orders");
        this.frame.setLocationRelativeTo(null);
        JLabel id=new JLabel("Id Comanda: ");
        JLabel idClient=new JLabel("Id Client: ");
        JLabel idProdus=new JLabel("Id Produs: ");
        JLabel cantitate=new JLabel("Cantitate: ");
        JTextField idText=new JTextField(10);
        JTextField idClientText=new JTextField(20);
        JTextField idProdusText=new JTextField(20);
        JTextField cantitateText=new JTextField(20);
        panelPrincipal.setLayout(null);

        id.setBounds(50, 20, 100, 20);
        idText.setBounds(120,20,100,20);
        idClient.setBounds(230, 20, 100, 20);
        idClientText.setBounds(285,20,100,20);
        idProdus.setBounds(400, 20, 100, 20);
        idProdusText.setBounds(470,20,100,20);
        cantitate.setBounds(600, 20, 100, 20);
        cantitateText.setBounds(665,20,100,20);

        selectAll.setBounds(50,60,140,30);
        selectById.setBounds(50,100,140,30);
        selectByIdClient.setBounds(50,140,140,30);
        selectByIdProdus.setBounds(50,180,140,30);
        selectByCantitate.setBounds(50,220,140,30);
        insert.setBounds(50,260,140,30);
        update.setBounds(50,300,140,30);
        deleteById.setBounds(50,340,140,30);
        back.setBounds(50,380,140,30);
        exit.setBounds(50,420,140,30);

        panelPrincipal.add(id);
        panelPrincipal.add(idText);
        panelPrincipal.add(idClient);
        panelPrincipal.add(idClientText);
        panelPrincipal.add(idProdus);
        panelPrincipal.add(idProdusText);
        panelPrincipal.add(cantitate);
        panelPrincipal.add(cantitateText);
        panelPrincipal.add(selectAll);
        panelPrincipal.add(selectById);
        panelPrincipal.add(selectByIdClient);
        panelPrincipal.add(selectByIdProdus);
        panelPrincipal.add(selectByCantitate);
        panelPrincipal.add(insert);
        panelPrincipal.add(deleteById);
        //panelPrincipal.add(update);
        panelPrincipal.add(back);
        panelPrincipal.add(exit);

        JTextArea textArea=new JTextArea();
        textArea.setBounds(200,60,800,430);
        textArea.setText("Orders Table");
        panelPrincipal.add(textArea);
        frame.getContentPane().add(panelPrincipal);
        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                View v= null;
                try {
                    v = new View();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Controller c=new Controller(v);
            }
        });

        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    orders=order.findAllOrders();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(orders);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    orders=order.findById(Integer.parseInt(idText.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(orders);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectByIdClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    orders=order.findByIdClientOrProduct(idClientText.getText(),"id_client");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(orders);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });

        selectByIdProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    orders=order.findByIdClientOrProduct(idProdusText.getText(),"id_product");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(orders);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectByCantitate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    orders=order.findByIdClientOrProduct(cantitateText.getText(),"quantity");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(orders);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        deleteById.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Orders o=new Orders();
                    orders=order.deleteById(o,idText.getText());
                    JScrollPane myScrollPane = new JScrollPane(orders);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

        insert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                   Orders o=new Orders(Integer.parseInt(idText.getText()), Integer.parseInt(idClientText.getText()),Integer.parseInt(idProdusText.getText()), Integer.parseInt(cantitateText.getText()));
                   orders=order.insert(o);
                    JScrollPane myScrollPane = new JScrollPane(orders);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        frame.setVisible(true);
    }
}
