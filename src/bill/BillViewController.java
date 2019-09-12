package bill;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import customer.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bill_no;

    @FXML
    private TextField amount;

    @FXML
    private TextField customer_id;

    @FXML
    private DatePicker bill_date;

    @FXML
    private Button btnpro;
    

    @FXML
    private Label saved;
    
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
    void saved(MouseEvent event) {
    	saved.setText("");
    }
    
    @FXML
    void product_info(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnpro.getScene();
    		scene1.getWindow().hide();
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("product/ProductView.fxml")); 
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
    	LocalDate local = bill_date.getValue();
    	try {
            pst = con.prepareStatement("insert into bill values(?,?,?,?)");
            pst.setString(1,bill_no.getText());
            Date d = Date.valueOf(local);
            pst.setDate(2, d);
            pst.setString(3,amount.getText());
            pst.setString(4,customer_id.getText());
            pst.executeUpdate();
            saved.setText("Saved Successfully!");
            
            customer_id.setText("");
            bill_no.setText("");
            amount.setText("");
            bill_date.setValue(null);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    	doConnection();
    }
}
