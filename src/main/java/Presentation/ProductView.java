package Presentation;

import Bll.ClientBll;
import Bll.ProductBll;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ProductView {

    JFrame frame;
    JPanel panelPrincipal=new JPanel();
    JButton back=new JButton("Inapoi");
    JButton exit=new JButton("Exit");
    JButton insert= new JButton("Insert");
    JButton selectAll= new JButton("Select All");
    JButton selectById= new JButton("Select By Id");
    JButton selectByName= new JButton("Select By Name");
    JButton selectByStock= new JButton("Select By Stock");
    JButton selectByPrice= new JButton("Select By Price");

    JButton updateName= new JButton("Update Name");
    JButton updateStock= new JButton("Update Stock");
    JButton updatePrice= new JButton("Update Price");
    JButton deleteById=new JButton("Delete by Id");
    JButton deleteByName=new JButton("Delete by Name");
    JButton deleteByStock=new JButton("Delete by Stock");
    JButton deleteByPrice=new JButton("Delete by Price");
    JTable produse=new JTable();
    ProductBll product=new ProductBll();
    Product productP=new Product();

    public ProductView(){
        this.frame=new JFrame();
        this.frame.setSize(1200, 600);
        this.frame.setTitle("Products ");
        this.frame.setLocationRelativeTo(null);
        panelPrincipal.setLayout(null);
        JLabel id=new JLabel("Id Produs: ");
        JLabel name=new JLabel("Nume Produs: ");
        JLabel stock=new JLabel("Stoc Produs: ");
        JLabel price=new JLabel("Pret Produs: ");
        JTextField idText=new JTextField(10);
        JTextField nameText=new JTextField(20);
        JTextField stockText=new JTextField(10);
        JTextField priceText=new JTextField(10);

        id.setBounds(50, 20, 100, 20);
        idText.setBounds(110,20,100,20);
        name.setBounds(220, 20, 100, 20);
        nameText.setBounds(300,20,100,20);
        stock.setBounds(410, 20, 100, 20);
        stockText.setBounds(490,20,100,20);
        price.setBounds(610, 20, 100, 20);
        priceText.setBounds(695,20,100,20);

        selectAll.setBounds(50,60,140,30);
        selectById.setBounds(50,100,140,30);
        selectByName.setBounds(50,140,140,30);
        selectByStock.setBounds(50,180,140,30);
        selectByPrice.setBounds(50,220,140,30);
        insert.setBounds(50,300,140,30);
        updateName.setBounds(1010,60,140,30);
        updateStock.setBounds(1010,100,140,30);
        updatePrice.setBounds(1010,140,140,30);
        back.setBounds(50,380,140,30);
        exit.setBounds(50,420,140,30);
        deleteById.setBounds(50,500,140,30);
        deleteByName.setBounds(200,500,140,30);
        deleteByStock.setBounds(350,500,140,30);
        deleteByPrice.setBounds(500,500,140,30);

        panelPrincipal.add(id);
        panelPrincipal.add(idText);
        panelPrincipal.add(name);
        panelPrincipal.add(nameText);
        panelPrincipal.add(stock);
        panelPrincipal.add(stockText);
        panelPrincipal.add(price);
        panelPrincipal.add(priceText);
        panelPrincipal.add(selectAll);
        panelPrincipal.add(selectById);
        panelPrincipal.add(selectByName);
        panelPrincipal.add(selectByStock);
        panelPrincipal.add(selectByPrice);
        panelPrincipal.add(insert);
        panelPrincipal.add(updateName);
        panelPrincipal.add(updateStock);
        panelPrincipal.add(updatePrice);
        panelPrincipal.add(back);
        panelPrincipal.add(exit);
        panelPrincipal.add(deleteById);
        panelPrincipal.add(deleteByName);
        panelPrincipal.add(deleteByStock);
        panelPrincipal.add(deleteByPrice);

        JTextArea textArea=new JTextArea();
        textArea.setBounds(200,60,800,430);
        textArea.setText("Products Table");
        panelPrincipal.add(textArea);
        frame.getContentPane().add(panelPrincipal);

        panelPrincipal.setVisible(true);

        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    produse=product.findAllProducts();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(produse);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectById.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    idText.setEditable(true);
                    produse=product.findById(Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        selectByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    produse=product.findByName(nameText.getText(),"name");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(produse);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectByStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    produse=product.findByStock(stockText.getText(),"stock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(produse);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
        selectByPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    produse=product.findByPrice(priceText.getText(),"price");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(produse);
                myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                myScrollPane.setBounds(200,60,800,390);
                panelPrincipal.add(myScrollPane);
            }
        });
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

        deleteById.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.deleteById(p,idText.getText());
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteByName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.deleteByName(p,nameText.getText());
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteByStock.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.deleteByStock(p,stockText.getText());
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteByPrice.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.deleteByPrice(p,priceText.getText());
                    JScrollPane myScrollPane = new JScrollPane(produse);
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
                   Product p=new Product(Integer.parseInt(idText.getText()),nameText.getText(),Integer.parseInt(stockText.getText()),Float.parseFloat(priceText.getText()));
                    produse=product.insert(p);
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updateName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.update(p,"name",nameText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updateStock.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.update(p,"stock",stockText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updatePrice.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Product p=new Product();
                    produse=product.update(p,"price",priceText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(produse);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        this.frame.setVisible(true);
    }
}
