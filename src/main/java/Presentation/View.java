package Presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {
    //pentru Client
    private JTextField idClient=new JTextField(10);
    private JTextField nameClient=new JTextField(10);
    private JTextField emailClient=new JTextField(10);
    private JTextField adresaClient=new JTextField(10);
    private JTextField ziDeNastereClient=new JTextField(10);

    //pentru Produs
    private JTextField idProdus=new JTextField(10);
    private JTextField nameProdus=new JTextField(10);
    private JTextField pretProdus=new JTextField(10);
    private JTextField stocProdus=new JTextField(10);

    private JFrame frame;
    private JButton clientButon=new JButton("Clienti");
    private JButton produsButon=new JButton("Produse");
    private JButton ordersButon=new JButton("Comenzi");

    JPanel panelPrincipal=new JPanel();

    public View() throws IOException {
        this.frame=new JFrame();
        this.frame.setSize(1200, 600);
        this.frame.setTitle("Order Management");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        BufferedImage img= ImageIO.read(new File("warehouse.png"));
        JLabel pic=new JLabel((new ImageIcon(img)));
        pic.setBounds(100,300,150,150);
        clientButon.setBounds(100,100,100,70);

        //panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(clientButon);
        panelPrincipal.add(produsButon);
        panelPrincipal.add(ordersButon);
        panelPrincipal.add(pic);
        panelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.setPreferredSize(new Dimension(400, 100));
        panelPrincipal.setMaximumSize(new Dimension(400, 100));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder("Order Management"));
        frame.getContentPane().add(panelPrincipal);
        this.frame.setVisible(true);

    }



    public JTextField getIdClient() {
        return idClient;
    }

    public void setIdClient(JTextField idClient) {
        this.idClient = idClient;
    }

    public JTextField getNameClient() {
        return nameClient;
    }

    public void setNameClient(JTextField nameClient) {
        this.nameClient = nameClient;
    }

    public JTextField getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(JTextField emailClient) {
        this.emailClient = emailClient;
    }

    public JTextField getAdresaClient() {
        return adresaClient;
    }

    public void setAdresaClient(JTextField adresaClient) {
        this.adresaClient = adresaClient;
    }

    public JTextField getZiDeNastereClient() {
        return ziDeNastereClient;
    }

    public void setZiDeNastereClient(JTextField ziDeNastereClient) {
        this.ziDeNastereClient = ziDeNastereClient;
    }

    public JTextField getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(JTextField idProdus) {
        this.idProdus = idProdus;
    }

    public JTextField getNameProdus() {
        return nameProdus;
    }

    public void setNameProdus(JTextField nameProdus) {
        this.nameProdus = nameProdus;
    }

    public JTextField getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(JTextField pretProdus) {
        this.pretProdus = pretProdus;
    }

    public JTextField getStocProdus() {
        return stocProdus;
    }

    public void setStocProdus(JTextField stocProdus) {
        this.stocProdus = stocProdus;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getClientButon() {
        return clientButon;
    }

    public void setClientButon(JButton clientButon) {
        this.clientButon = clientButon;
    }

    public JButton getProdusButon() {
        return produsButon;
    }

    public void setProdusButon(JButton produsButon) {
        this.produsButon = produsButon;
    }

    public JButton getOrdersButon() {
        return ordersButon;
    }

    public void setOrdersButon(JButton ordersButon) {
        this.ordersButon = ordersButon;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    void clientButonListener(ActionListener mal) {
        clientButon.addActionListener(mal);
    }
    void produsButonListener(ActionListener mal) {
        produsButon.addActionListener(mal);
    }
    void orderButonListener(ActionListener mal) {
        ordersButon.addActionListener(mal);
    }
}
