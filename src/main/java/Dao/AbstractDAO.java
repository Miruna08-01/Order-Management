package Dao;

import Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    public final Class<T> type;
    private JTable table;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }



    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    protected String createInsertQuery(ArrayList<Field> fields)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()+"(");

        for(int i = 0; i < fields.size(); i++){
            sb.append(fields.get(i).getName());
            if(i < fields.size() -1){
             sb.append(", ");
            }
            if(i==fields.size()-1){
                sb.append(")\nVALUES(");
            }
        }
        for(int i = 0; i < fields.size(); i++){
            sb.append("?");
            if(i < fields.size() -1){
                sb.append(", ");
            }
            else{
                sb.append(")");
            }
        }
        return sb.toString();
    }

    private String createUpdateQuery(T t, String f,int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET "+f+" =?");
        sb.append(" WHERE id="+id);
        return sb.toString();
    }


    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = createSelectAllQuery();
        connection = ConnectionFactory.getConnection();
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            return createObjects(result);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(result);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }


    public List<T> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public List<T> findByField(String s,String f) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(f);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, s);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByAddress" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void insert(T t) throws SQLException {
        // TODO:
        ArrayList<Field>fields=new ArrayList<>();
        for(Field j:type.getDeclaredFields()){
            fields.add(j);
        }

        ResultSet resultSet = null;
        String query = createInsertQuery(fields);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        int k = 1;
        connection = ConnectionFactory.getConnection();

        try {
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                statement.setObject(k, f.get(t));
                k++;
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally{

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public void delete(T t,Object s,String k) throws SQLException, IllegalAccessException {
        String query = createDeleteQuery(String.valueOf(s));
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        connection=ConnectionFactory.getConnection();
        statement=connection.prepareStatement(query);
        ResultSet resultSet = null;
        for (Field f : type.getDeclaredFields()){
            if(f.getName().equals(s)){
                statement.setObject(1,k);
                break;
            }
        }
        statement.executeUpdate();
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    public void update(T t,String field,String f,int id) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        String query = createUpdateQuery(t,field,id);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        connection=ConnectionFactory.getConnection();
        statement=connection.prepareStatement(query);
        ResultSet resultSet = null;
        for (Field ff : type.getDeclaredFields()){
            if(ff.getName().equals(field)){
                statement.setObject(1,f);
                break;
            }
        }
        statement.executeUpdate();
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    public JTable createTable(List<T> list) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        ArrayList<String> coloane = new ArrayList<>();
        for (Field f : type.getDeclaredFields()) {
            coloane.add(f.getName());
        }
        int i = 0;
        int j = 0;
        String[][] content = new String[100][coloane.size()];
        for (T t : list) {
            for (Field f : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                content[i][j++]=method.invoke(t).toString();

            }
            ++i;
            j=0;
        }
        Object[] col=coloane.toArray();
        DefaultTableModel tab = new DefaultTableModel(content,col);
        this.setTable(new JTable(tab));
        return this.getTable();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}