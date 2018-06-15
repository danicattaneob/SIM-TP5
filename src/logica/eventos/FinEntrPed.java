/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import java.util.LinkedList;
import logica.Gestor;
import logica.entidades.Cliente;

/**
 *
 * @author Dani
 */
public class FinEntrPed extends Evento {
    private Gestor g;
    private final double min = 5;
    private final double max = 10;
    private final Cliente cliente;

    public FinEntrPed(Gestor g, Cliente cliente, double tiempoFin) {
        this.g = g;
        this.cliente = cliente;
        this.tiempoEjec = randomTiempoAt(tiempoFin);
    }

    public void duplicarTiempo(double tiempoAct){
        tiempoEjec = (tiempoEjec - tiempoAct) * 2 + tiempoAct; 
    }
    
    public void dividirTiempo(double tiempoAct){
        tiempoEjec = (tiempoEjec - tiempoAct) / 2 + tiempoAct;
    }

    public double randomTiempoAt(double tiempoActual) {
        return tiempoActual + Math.random() * (max - min) + min;
    }
    
    @Override
    public void ejecutar() {
        
        if (!g.hayPedidos()) {
            g.getAyudante().liberar();
            if (g.getJefe().estaEnCocina()) {
                g.getJefe().liberar();
            }
        }
    }

    @Override
    public String toString() {
        return "FinEntrPed " + tiempoEjec;
    }
    
    
    
}
