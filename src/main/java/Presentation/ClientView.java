package Presentation;

import Bll.ClientBll;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientView {
    JFrame frame;
    JPanel panelPrincipal=new JPanel();
    JButton insert= new JButton("Insert");
    JButton selectAll= new JButton("Select All");
    JButton selectById= new JButton("Select By Id");
    JButton selectByName= new JButton("Select By Name");
    JButton selectByEmail= new JButton("Select By Email");
    JButton selectByAddress= new JButton("Select By Address");
    JButton selectByBirthdate=new JButton("Select By Birthdate");
    JButton updateName= new JButton("Update Name");
    JButton updateEmail= new JButton("Update Email");
    JButton updateAddress= new JButton("Update Address");
    JButton updateZideNastere= new JButton("Update Birthdate");
    JButton back= new JButton("Inapoi");
    JButton exit= new JButton("Exit");
    JButton deleteById=new JButton("Delete by Id");
    JButton deleteByName=new JButton("Delete by Name");
    JButton deleteByEmail=new JButton("Delete by Email");
    JButton deleteByAddress=new JButton("Delete by Address");
    JButton deleteByZiDeNastere=new JButton("Delete by Birthdate");
    JTable clienti=new JTable();
    ClientBll client=new ClientBll();
    Client clientC=new Client();





    public ClientView()  {
        this.frame=new JFrame();
        this.frame.setSize(1200, 600);
        this.frame.setTitle("Clients");
        this.frame.setLocationRelativeTo(null);
        JLabel id=new JLabel("Id Client: ");
        JLabel name=new JLabel("Nume Client: ");
        JLabel email=new JLabel("Email Client: ");
        JLabel adresa=new JLabel("Adresa Client: ");
        JLabel ziDeNastere=new JLabel("Zi de nastere Client: ");
        JTextField idText=new JTextField(10);
        JTextField nameText=new JTextField(20);
        JTextField emailText=new JTextField(20);
        JTextField adresaText=new JTextField(20);
        JTextField ziDeNastereText=new JTextField(10);

        panelPrincipal.setLayout(null);


        id.setBounds(50, 20, 100, 20);
        idText.setBounds(100,20,100,20);
        name.setBounds(210, 20, 100, 20);
        nameText.setBounds(285,20,100,20);
        email.setBounds(400, 20, 100, 20);
        emailText.setBounds(470,20,100,20);
        adresa.setBounds(585, 20, 100, 20);
        adresaText.setBounds(665,20,100,20);
        ziDeNastere.setBounds(785, 20, 150, 20);
        ziDeNastereText.setBounds(900,20,100,20);

        selectAll.setBounds(50,60,140,30);
        selectById.setBounds(50,100,140,30);
        selectByName.setBounds(50,140,140,30);
        selectByEmail.setBounds(50,180,140,30);
        selectByAddress.setBounds(50,220,140,30);
        selectByBirthdate.setBounds(50,260,140,30);
        insert.setBounds(50,300,140,30);
        updateName.setBounds(1010,60,140,30);
        updateEmail.setBounds(1010,100,140,30);
        updateAddress.setBounds(1010,140,140,30);
        updateZideNastere.setBounds(1010,180,140,30);
        back.setBounds(50,380,140,30);
        exit.setBounds(50,420,140,30);
        deleteById.setBounds(50,500,140,30);
        deleteByName.setBounds(200,500,140,30);
        deleteByEmail.setBounds(350,500,140,30);
        deleteByAddress.setBounds(500,500,140,30);
        deleteByZiDeNastere.setBounds(650,500,140,30);

        panelPrincipal.add(id);
        panelPrincipal.add(idText);
        panelPrincipal.add(name);
        panelPrincipal.add(nameText);
        panelPrincipal.add(email);
        panelPrincipal.add(emailText);
        panelPrincipal.add(adresa);
        panelPrincipal.add(adresaText);
        panelPrincipal.add(ziDeNastere);
        panelPrincipal.add(ziDeNastereText);
        panelPrincipal.add(selectAll);
        panelPrincipal.add(selectById);
        panelPrincipal.add(selectByName);
        panelPrincipal.add(selectByEmail);
        panelPrincipal.add(selectByAddress);
        panelPrincipal.add(selectByBirthdate);
        panelPrincipal.add(insert);
        panelPrincipal.add(updateName);
        panelPrincipal.add(updateEmail);
        panelPrincipal.add(updateAddress);
        panelPrincipal.add(updateZideNastere);
        panelPrincipal.add(back);
        panelPrincipal.add(exit);
        panelPrincipal.add(deleteById);
        panelPrincipal.add(deleteByName);
        panelPrincipal.add(deleteByEmail);
        panelPrincipal.add(deleteByAddress);
        panelPrincipal.add(deleteByZiDeNastere);

        JTextArea textArea=new JTextArea();
        textArea.setBounds(200,60,800,430);
        textArea.setText("Clients Table");
        panelPrincipal.add(textArea);
        frame.getContentPane().add(panelPrincipal);

        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    clienti=client.findAllClients();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                JScrollPane myScrollPane = new JScrollPane(clienti);
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
                    clienti=client.findById(Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        selectByName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    nameText.setEditable(true);
                    clienti=client.findByName(nameText.getText(),"name");
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        selectByEmail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    emailText.setEditable(true);
                    clienti=client.findByEmail(emailText.getText(),"email");
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        selectByAddress.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    adresaText.setEditable(true);
                    clienti=client.findByAddress(adresaText.getText(),"address");
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        selectByBirthdate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    ziDeNastereText.setEditable(true);
                    clienti=client.findByBirthdate(ziDeNastereText.getText(),"birthdate");
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

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

        insert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client(Integer.parseInt(idText.getText()),nameText.getText(),emailText.getText(),adresaText.getText(),ziDeNastereText.getText());
                    clienti=client.insert(c);
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteById.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.deleteById(c,idText.getText());
                    JScrollPane myScrollPane = new JScrollPane(clienti);
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
                    Client c=new Client();
                    clienti=client.deleteByName(c,nameText.getText());
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteByEmail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.deleteByEmail(c,emailText.getText());
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteByAddress.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.deleteByAddress(c,adresaText.getText());
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        deleteByZiDeNastere.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.deleteByBirthdate(c,ziDeNastereText.getText());
                    JScrollPane myScrollPane = new JScrollPane(clienti);
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
                    Client c=new Client();
                    clienti=client.update(c,"name",nameText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updateEmail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.update(c,"email",emailText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updateAddress.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.update(c,"address",adresaText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(clienti);
                    myScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    myScrollPane.setBounds(200,60,800,390);
                    panelPrincipal.add(myScrollPane);
                } catch (SQLException | IntrospectionException | InvocationTargetException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        updateZideNastere.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.removeAll();
                try {
                    Client c=new Client();
                    clienti=client.update(c,"birthdate",ziDeNastereText.getText(),Integer.parseInt(idText.getText()));
                    JScrollPane myScrollPane = new JScrollPane(clienti);
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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getClienti() {
        return clienti;
    }

    public void setClienti(JTable clienti) {
        this.clienti = clienti;
    }

    public ClientBll getClient() {
        return client;
    }

    public void setClient(ClientBll client) {
        this.client = client;
    }




}
