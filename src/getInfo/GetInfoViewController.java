package getInfo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class GetInfoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> bill_no;

    @FXML
    private ListView<String> dop;

    @FXML
    private TextField customer_id;

    @FXML
    private TextField first_name;

    @FXML
    private TextField age;

    @FXML
    private TextField address;

    @FXML
    private TextField last_name;
    
    PreparedStatement pst;
    Connection con;
    
    void doConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mittal", data.uid, data.pwd);
            System.out.println("Connected");
        }
        
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void fetch(ActionEvent event) {
    	
    	try {
           // pst = con.prepareStatement("select * from bill inner join customer on customer.CustomerID = bill.CustomerID where bill.CustomerID = ?");
    		  pst = con.prepareStatement("select * from customer where CustomerID = ?");
            pst.setString(1, customer_id.getText());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
            	first_name.setText(rs.getString("customer.First_Name"));
                last_name.setText(rs.getString("customer.LastName"));
                address.setText(rs.getString("customer.Address"));
                age.setText(rs.getString("customer.Age"));
            }
        }
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    	
    	try {
            pst = con.prepareStatement("select * from bill where CustomerID = ?");
            pst.setString(1, customer_id.getText());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                bill_no.getItems().add(rs.getString("Bill_No"));
                dop.getItems().add(rs.getString("Date"));
            }
            
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
    	doConnection();
    }
}
