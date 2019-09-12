package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnP;

    @FXML
    private Button btnC;

    @FXML
    private Button btnB;
    
    @FXML
    private Button btnCpi;
    
    @FXML
    void Bill_Info(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnB.getScene();
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
    void cpi(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnCpi.getScene();
    		scene1.getWindow().hide();
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("getInfo/GetInfoView.fxml")); 
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
    void Customer_Info(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnC.getScene();
    		scene1.getWindow().hide();
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customer/CustomerView.fxml")); 
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
    void Product_Info(ActionEvent event) {
    	try{
    		Scene scene1 = (Scene)btnP.getScene();
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
    void initialize() {

    }
}
