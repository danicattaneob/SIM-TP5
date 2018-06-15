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
public class Cliente {
    private EstadoCliente estado;

    public Cliente() {
        estado = EstadoCliente.ESPERANDO_ATENCION;
    }
    
    public void atender(){
        estado = estado.SIENDO_ATENDIDO;
    }
    
    public void PrepararPedido(){
        estado = estado.ESPERANDO_PEDIDO;
    }
    
    
}
