/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logica.Gestor;

/**
 *
 * @author Dani
 */
public class FXMLDocumentController implements Initializable {
    
        @FXML
    private TableView<?> tvw_simulacion;

    @FXML
    private TableColumn<?, ?> reloj;

    @FXML
    private TableColumn<?, ?> evento;

    @FXML
    private TableColumn<?, ?> rndLleg;

    @FXML
    private TableColumn<?, ?> tiemEnLleg;

    @FXML
    private TableColumn<?, ?> proxLleg;

    @FXML
    private TableColumn<?, ?> rndTipC;

    @FXML
    private TableColumn<?, ?> tipoCom;

    @FXML
    private TableColumn<?, ?> rndFinAtG;

    @FXML
    private TableColumn<?, ?> tiemAt;

    @FXML
    private TableColumn<?, ?> finAt;

    @FXML
    private TableColumn<?, ?> transmP;

    @FXML
    private TableColumn<?, ?> finTansm;

    @FXML
    private TableColumn<?, ?> rndPrepC;

    @FXML
    private TableColumn<?, ?> tiempPrepC;

    @FXML
    private TableColumn<?, ?> finPrepC;

    @FXML
    private TableColumn<?, ?> estJefe;

    @FXML
    private TableColumn<?, ?> estCola;

    @FXML
    private TableColumn<?, ?> acTiemMost;

    @FXML
    private TableColumn<?, ?> acTiemCoc;

    @FXML
    private TableColumn<?, ?> estAy;

    @FXML
    private TableColumn<?, ?> acTiemTrab;

    @FXML
    private TableColumn<?, ?> acTiemLibre;


    @FXML
    private TextField txt_hasta;

    @FXML
    private TextField txt_desde;

    @FXML
    private Button btn_simular;
    
    @FXML
    private TextField txt_cantSim;
    
    @FXML
    private void iniciarSim(ActionEvent event) {
        System.out.println("You clicked me!");
        Gestor g = new Gestor();
        g.simular(Integer.parseInt(txt_cantSim.getText()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
