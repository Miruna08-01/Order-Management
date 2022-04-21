package App;

import Dao.ClientDao;
import Connection.ConnectionFactory;
import Presentation.ClientView;
import Presentation.Controller;
import Presentation.View;
import model.Client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        View view = new View();
        Controller controller = new Controller(view);


 }
}

