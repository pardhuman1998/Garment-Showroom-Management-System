package customer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField customer_id;

    @FXML
    private TextField First_name;

    @FXML
    private TextField last_name;

    @FXML
    private RadioButton Others;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Female;

    @FXML
    private TextField Address;

    @FXML
    private TextField Age;
    
    @FXML
    private Label lblSuccess;
    
    @FXML
    private Button Bill_info;
    
    
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
    void Bill_Info(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)Bill_info.getScene();
    		scene1.getWindow().hide();
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("bill/BillView.fxml")); 
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void Fetch(ActionEvent event) {
    	try {
            pst = con.prepareStatement("select * from customer where CustomerID = ?");
            pst.setString(1, customer_id.getText());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
            	First_name.setText(rs.getString("First_Name"));
                last_name.setText(rs.getString("LastName"));
                if (rs.getString("Gender").equals("Male"))
                	Male.setSelected(true);
                else if (rs.getString("Gender").equals("Female"))
                	Female.setSelected(true);
                else
                	Others.setSelected(true);
                Address.setText(rs.getString("Address"));
                Age.setText(rs.getString("Age"));
            }
        }
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {
    	try {
            pst = con.prepareStatement("delete from customer where CustomerID = ?");
            pst.setString(1, customer_id.getText());
            pst.executeUpdate();
            
            customer_id.setText("");
            First_name.setText("");
            last_name.setText("");
            Age.setText("");
            Address.setText("");
            Male.setSelected(false);
            Female.setSelected(false);
            Others.setSelected(false);
            lblSuccess.setText("Deleted Successfully!");
            
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

    }
    
    @FXML
    void success(MouseEvent event) {
    	lblSuccess.setText("");
    }

    @FXML
    void update(ActionEvent event) {
    	try {
            pst = con.prepareStatement("update customer set First_Name = ?, LastName = ?, Age = ?, Address = ? where CustomerID = ?");
            pst.setString(1,First_name.getText());
            pst.setString(2,last_name.getText());
            pst.setString(4,Address.getText());
            pst.setString(3,Age.getText());
            pst.setString(5,customer_id.getText());
    
            pst.executeUpdate();
      
            customer_id.setText("");
            First_name.setText("");
            last_name.setText("");
            Age.setText("");
            Address.setText("");
            Male.setSelected(false);
            Female.setSelected(false);
            Others.setSelected(false);
            lblSuccess.setText("Updated Successfully!");
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void Save(ActionEvent event) {
    	
    	try {
            pst = con.prepareStatement("insert into customer values(?,?,?,?,?,?)");
            pst.setString(2,First_name.getText());
            pst.setString(3,last_name.getText());
            pst.setString(4,Address.getText());
            pst.setString(1,customer_id.getText());
            if(Male.isSelected())
            	pst.setString(5,"Male");
            else if(Female.isSelected())
            	pst.setString(5,"Female");
            else
            	pst.setString(5,"Others");
            pst.setString(6,Age.getText());
            pst.executeUpdate();
            
            customer_id.setText("");
            First_name.setText("");
            last_name.setText("");
            Age.setText("");
            Address.setText("");
            Male.setSelected(false);
            Female.setSelected(false);
            Others.setSelected(false);
            lblSuccess.setText("Saved Successfully!");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }


    }

    @FXML
    void initialize() {
    	doConnection();
    }
}
