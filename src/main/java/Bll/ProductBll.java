package Bll;

import Bll.validators.EmailValidator;
import Bll.validators.PositiveNumber;
import Bll.validators.Validator;
import Dao.ClientDao;
import Dao.ProductDao;
import Presentation.ClientView;
import Presentation.ProductView;
import model.Client;
import model.Product;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBll {
    private ProductDao productDao;
    List<Product> products= new ArrayList<>();
    JTable productTable = new JTable();
    private List<Validator<Product>> validators;
    ProductView view;

    public ProductBll() {

        this.productDao = new ProductDao();
        validators = new ArrayList<>();
        validators.add(new PositiveNumber());
    }

    public JTable findAllProducts() throws Exception {
        this.products=productDao.findAll();
        if(products.size()==0) {
            throw new Exception("Nu exista produse");
        }
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;

    }
    public JTable findById(int id) throws Exception {
        this.products = productDao.findById(id);
        if(products.size()==0) {
            throw new Exception("Nu exista produs cu id-ul: "+id);
        }
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;

    }

    public JTable findByName(String k,String name) throws Exception {
        this.products = productDao.findByField(k,name);
        if(products.size()==0) {
            throw new Exception("Nu exista produse cu numele: "+name);
        }
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }
    public JTable findByStock(String k,String stock) throws Exception {
        this.products = productDao.findByField(k,stock);
        if(products.size()==0) {
            throw new Exception("Nu exista produse cu stocul: "+stock);
        }
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }

    public JTable findByPrice(String k,String price) throws Exception {
        this.products = productDao.findByField(k,price);
        if(products.size()==0) {
            throw new Exception("Nu exista produse cu pretul: "+price);
        }
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }

    public JTable insert(Product product) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Validator<Product> i : validators) {
            i.validate(product);
        }

        productDao.insert(product);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public void setProductTable(JTable productTable) {
        this.productTable = productTable;
    }

    public JTable update(Product product,String f,String s,int id) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        productDao.update(product,f,s,id);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }
    public JTable deleteById(Product c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        productDao.delete(c,"id",k);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }
    public JTable deleteByName(Product c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        productDao.delete(c,"name",k);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;

    }
    public JTable deleteByStock(Product c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        productDao.delete(c,"stock",k);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }
    public JTable deleteByPrice(Product c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        productDao.delete(c,"price",k);
        this.products=productDao.findAll();
        this.productTable=productDao.createTable(products);
        productTable.setVisible(true);
        productTable.setEnabled(true);
        return this.productTable;
    }

//    public List<Client> selectAll() {
//        return clientDao.selectAll();
//    }
}

