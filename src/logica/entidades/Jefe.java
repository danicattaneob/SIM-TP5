/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidades;
import logica.estados.*;

/**
 *
 * @author Dani
 */
public class Jefe {
    private EstadoJefe estado;
    private EstadoJefe estadoAntPurga;

    public Jefe() {
        this.estado = EstadoJefe.LIBRE;
    }
    
    
    public void atender(){
        estado = estado.ATENDIENDO;
    }
    
    public void irACocina(){
        estado = estado.EN_COCINA;
    }
    
    public void liberar(){
        estado = estado.LIBRE;
    }
    
    public void purgar(){
        estadoAntPurga = estado;
        estado = estado.PURGANDO;
    }
    
    public void finPurga(){
        estado = estadoAntPurga;
    }
    
    public boolean estaAtendiendo(){
        return estado.equals(EstadoJefe.ATENDIENDO);
    }
    
    public boolean estaEnCocina(){
        return estado.equals(EstadoJefe.EN_COCINA);
    }
    
    public boolean estaPurgando(){
        return estado.equals(EstadoJefe.PURGANDO);
    }

    public EstadoJefe getEstado() {
        return estado;
    }
    
    
}


