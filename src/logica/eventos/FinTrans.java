/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import java.util.LinkedList;
import logica.entidades.*;
import logica.estados.*;
import logica.*;

/**
 *
 * @author Dani
 */
public class FinTrans extends Evento {

    private Gestor g;
    private final double tiempo = 0.1;
    private Cliente cliente;

    public FinTrans(Gestor g, Cliente cliente, double tiempoFin) {
        this.g = g;
        this.cliente = cliente;
        this.tiempoEjec = tiempo + tiempoFin;
        
    }
    

    @Override
    public void ejecutar() {
        FinEntrPed entrPed = new FinEntrPed(g, cliente, tiempoEjec);
        g.agregarEvDeFinPed(entrPed);
        
        if (!g.getAyudante().estaOcupado()) {
            g.getAyudante().ocupar();
        }
        if (g.getCola().estaVacia()) {
            g.getJefe().irACocina();
            g.dividirTiempoPed(tiempoEjec);            
        } else {
            Cliente c = g.getCola().avanzar();
            c.atender();
            Evento e = Llegada.GenerarTipoCompra(c, tiempoEjec, g);
            g.agregarEvento(e);
        }
    }

    @Override
    public String toString() {
        return "FinTrans " + tiempoEjec;
    }
    
    

}
