/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

import logica.entidades.*;
import logica.*;

/**
 *
 * @author Dani
 */
public class FinAtGol extends Evento {

    private Gestor g;
    private final double min = 0.5;
    private final double max = 2;
    private Cliente cliente;
    private double rndFinAt;
    private double tiempAt;

    public FinAtGol(Gestor g, Cliente c, double tiempoFin) {
        this.g = g;
        this.cliente = c;
        this.tiempoEjec = randomTiempoAt(tiempoFin);
    }

    @Override
    public void ejecutar() {
//        double porcOcupDiscoActual = g.getPorcentajeOcupDisco();
//        double porcOcupDiscoNuevo = calcularRK(porcOcupDiscoActual, H, ALFA, tiempoEjec);
//        g.setPorcentajeOcupDisco(porcOcupDiscoNuevo);
        
        //Validar inestabilidad
        
        
        if (g.getCola().estaVacia()) {
            if (g.hayPedidos()) {
                g.getJefe().irACocina();
                g.dividirTiempoPed(tiempoEjec);
            } else {
                g.getJefe().liberar();
            }
        } else {
            Cliente c = g.getCola().avanzar();
            c.atender();
            Evento e = Llegada.GenerarTipoCompra(c, tiempoEjec, g);
            if(g.getProxPurg().getTiempoEjec() < e.getTiempoEjec()){
                e.retrasarEjecucion(FinPurgado.getTiempoPurgado());
            }
            g.agregarEvento(e);
        }
    }

    public double randomTiempoAt(double tiempoActual) {
        rndFinAt = redondear(Math.random(), DECIMALES);
        tiempAt = redondear(rndFinAt * (max - min) + min, DECIMALES);
        return tiempoActual + tiempAt;
    }

    @Override
    public String toString() {
        return "FinAtGol";
    }

    public double getRndFinAt() {
        return rndFinAt;
    }

    public double getTiempAt() {
        return tiempAt;
    }

    
}
