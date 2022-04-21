package Bll;
import Bll.validators.EmailValidator;
import Bll.validators.NameValidator;
import Bll.validators.Validator;
import Presentation.ClientView;
import model.Client;
import Dao.ClientDao;

import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBll {

    private ClientDao clientDao;
    List<Client> clients=new ArrayList<>();
    JTable clientTable=new JTable();
    ClientView view;
    private List<Validator<Client>> validators;
    public ClientBll() {

        this.clientDao = new ClientDao();
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new NameValidator());
    }

    public JTable findAllClients() throws Exception {
        this.clients=clientDao.findAll();
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti");
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;

    }
    public JTable findById(int id) throws Exception {
        this.clients = clientDao.findById(id);
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti cu id-ul: "+id);
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;

    }

    public JTable findByName(String k,String name) throws Exception {
        this.clients = clientDao.findByField(k,name);
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti cu numele: "+name);
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable findByEmail(String k,String email) throws Exception {
        this.clients = clientDao.findByField(k,email);
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti cu emailul: "+email);
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }

    public JTable findByAddress(String k,String address) throws Exception {
        this.clients = clientDao.findByField(k,address);
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti cu adresa: "+address);
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable findByBirthdate(String k,String birthdate) throws Exception {
        this.clients = clientDao.findByField(k,birthdate);
        if(clients.size()==0) {
            throw new Exception("Nu exista clienti cu ziua de nastere: "+birthdate);
        }
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }


    public JTable insert(Client client) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        for (Validator<Client> i : validators) {
            i.validate(client);
        }

        clientDao.insert(client);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }

    public JTable getClientTable() {
        return clientTable;
    }

    public void setClientTable(JTable clientTable) {
        this.clientTable = clientTable;
    }

    public JTable update(Client client,String f,String s,int id) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        clientDao.update(client,f,s,id);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable deleteById(Client c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        clientDao.delete(c,"id",k);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable deleteByName(Client c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        clientDao.delete(c,"name",k);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable deleteByEmail(Client c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        clientDao.delete(c,"email",k);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable deleteByAddress(Client c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        clientDao.delete(c,"address",k);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }
    public JTable deleteByBirthdate(Client c, String k) throws SQLException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        clientDao.delete(c,"birthdate",k);
        this.clients=clientDao.findAll();
        this.clientTable=clientDao.createTable(clients);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);
        return this.clientTable;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
