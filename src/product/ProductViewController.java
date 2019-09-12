package product;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customer.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProductViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Product_id;

    @FXML
    private TextField Bill_no;

    @FXML
    private TextField brand_name;

    @FXML
    private TextField brand_price;

    @FXML
    private TextField discount;

    @FXML
    private TextField net_price;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Label save;
    
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
    void dd(MouseEvent event) {
    	save.setText("");
    }

    @FXML
    void HOME(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnHome.getScene();
    		scene1.getWindow().hide();
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("home/HomeView.fxml")); 
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
    void save(ActionEvent event) {

    	try {
            pst = con.prepareStatement("insert into product values(?,?,?,?,?,?)");
            pst.setString(1,Product_id.getText());
            pst.setString(2,Bill_no.getText());
            pst.setString(3,brand_name.getText());
            pst.setString(4,brand_price.getText());
            pst.setString(5,discount.getText());
            pst.setString(6,net_price.getText());
            pst.executeUpdate();
            save.setText("Saved Successfully!");
            
            Product_id.setText("");
            Bill_no.setText("");
            brand_name.setText("");
            brand_price.setText("");
            discount.setText("");
            net_price.setText("");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    		doConnection();
    }
}
