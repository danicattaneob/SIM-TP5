/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Gestor;

/**
 *
 * @author Dani
 */
public class FXMLDocumentController implements Initializable {
    
        @FXML
    private TableView<Columna> tvw_simulacion;

    @FXML
    private TableColumn<Columna, String> reloj;

    @FXML
    private TableColumn<Columna, String> evento;

    @FXML
    private TableColumn<Columna, String> rndLleg;

    @FXML
    private TableColumn<Columna, String> tiemEnLleg;

    @FXML
    private TableColumn<Columna, String> proxLleg;

    @FXML
    private TableColumn<Columna, String> rndTipC;

    @FXML
    private TableColumn<Columna, String> tipoCom;

    @FXML
    private TableColumn<Columna, String> rndFinAtG;

    @FXML
    private TableColumn<Columna, String> tiemAt;

    @FXML
    private TableColumn<Columna, String> finAt;

    @FXML
    private TableColumn<Columna, String> transmP;

    @FXML
    private TableColumn<Columna, String> finTansm;

    @FXML
    private TableColumn<Columna, String> rndPrepC;

    @FXML
    private TableColumn<Columna, String> tiempPrepC;

    @FXML
    private TableColumn<Columna, String> finPrepC;

    @FXML
    private TableColumn<Columna, String> estJefe;

    @FXML
    private TableColumn<Columna, String> estCola;

    @FXML
    private TableColumn<Columna, String> acTiemMost;

    @FXML
    private TableColumn<Columna, String> acTiemCoc;

    @FXML
    private TableColumn<Columna, String> estAy;

    @FXML
    private TableColumn<Columna, String> acTiemTrab;

    @FXML
    private TableColumn<Columna, String> acTiemLibre;


    @FXML
    private TextField txt_hasta;

    @FXML
    private TextField txt_desde;

    @FXML
    private Button btn_simular;
    
    @FXML
    private TextField txt_cantSim;
    
    @FXML
    private Label lbl_porcTiempOcAy;

    @FXML
    private Label lbl_porcTiempCocJef;

    @FXML
    private Label lbl_porcTiempMostrJefe;
    
    @FXML
    private void iniciarSim(ActionEvent event) {
        //tvw_simulacion.getColumns().clear();
        tvw_simulacion.getItems().clear();
        System.out.println("You clicked me!");
        Gestor g = new Gestor();
        ObservableList<Columna> lista = g.simular(getCantSim(), getDesde(), getHasta());
        tvw_simulacion.setItems(lista);
        
        lbl_porcTiempCocJef.setText(Double.toString(g.porcTiempCocJef()) + " %");
        lbl_porcTiempOcAy.setText(Double.toString(g.porcTiempOcAy()) + " %");
        lbl_porcTiempMostrJefe.setText(Double.toString(g.porcTiempMostJef()) + " %");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setearColumnas();
    }
    
    private void setearColumnas(){
    reloj.setCellValueFactory( new PropertyValueFactory<>("reloj"));
    evento.setCellValueFactory( new PropertyValueFactory<>("evento"));
    rndLleg.setCellValueFactory( new PropertyValueFactory<>("rndLleg"));
    tiemEnLleg.setCellValueFactory( new PropertyValueFactory<>("tiemEnLleg"));
    proxLleg.setCellValueFactory( new PropertyValueFactory<>("proxLleg"));
    rndTipC.setCellValueFactory( new PropertyValueFactory<>("rndTipC"));
    tipoCom.setCellValueFactory( new PropertyValueFactory<>("tipoCom"));
    rndFinAtG.setCellValueFactory( new PropertyValueFactory<>("rndFinAtG"));
    tiemAt.setCellValueFactory( new PropertyValueFactory<>("tiemAt"));
    finAt.setCellValueFactory( new PropertyValueFactory<>("finAt"));
    transmP.setCellValueFactory( new PropertyValueFactory<>("transmP"));
    finTansm.setCellValueFactory( new PropertyValueFactory<>("finTansm"));
    rndPrepC.setCellValueFactory( new PropertyValueFactory<>("rndPrepC"));
    tiempPrepC.setCellValueFactory( new PropertyValueFactory<>("tiempPrepC"));
    finPrepC.setCellValueFactory( new PropertyValueFactory<>("finPrepC"));
    estJefe.setCellValueFactory( new PropertyValueFactory<>("estJefe"));
    estCola.setCellValueFactory( new PropertyValueFactory<>("estCola"));
    acTiemMost.setCellValueFactory( new PropertyValueFactory<>("acTiemMost"));
    acTiemCoc.setCellValueFactory( new PropertyValueFactory<>("acTiemCoc"));
    estAy.setCellValueFactory( new PropertyValueFactory<>("estAy"));
    acTiemTrab.setCellValueFactory( new PropertyValueFactory<>("acTiemTrab"));
    acTiemLibre.setCellValueFactory( new PropertyValueFactory<>("acTiemLibre"));
    }

    public int getCantSim(){
        return Integer.parseInt(txt_cantSim.getText());
    }
    
    public int getDesde(){
        return Integer.parseInt(txt_desde.getText());
    }
    
    public int getHasta(){
        return Integer.parseInt(txt_hasta.getText());
    }
}
