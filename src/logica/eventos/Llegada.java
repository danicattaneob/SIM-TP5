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
public class Llegada extends Evento {

    private static final double MEDIA = 5;
    private Cliente cliente;
    private Gestor g;
    private double proxLleg;
    private static double randomTipoCom;
    private static double randomTiempLleg;

    public Llegada(Gestor g, double tiempoAct) {
        this.g = g;
        tiempoEjec = randomProxLleg(tiempoAct);
        randomTipoCom = 0;
        randomTiempLleg = 0;
    }

    public double randomProxLleg(double tiempoActual) {
        randomTiempLleg = Math.random();
        return tiempoActual + (-MEDIA) * Math.log(1 - randomTiempLleg);
    }

    public static Evento GenerarTipoCompra(Cliente c, double tiempoEjec, Gestor g) {
        randomTipoCom = Math.random();
        if (randomTipoCom < 0.8) {
            return new FinAtGol(g, c, tiempoEjec);
        } else {
            return new FinTrans(g, c, tiempoEjec);
        }
    }

    @Override
    public void ejecutar() {
        Evento evProxLleg = new Llegada(g, tiempoEjec);
        g.agregarEvento(evProxLleg);
        proxLleg = evProxLleg.getTiempoEjec();

        cliente = new Cliente();
        if (g.getJefe().estaAtendiendo()) {
            g.getCola().agregar(cliente);
        } else {
            if (g.getJefe().estaEnCocina()) {
                g.duplicarTiempoPed(tiempoEjec);
            }
            g.getJefe().atender();
            cliente.atender();
            Evento e = GenerarTipoCompra(cliente, tiempoEjec, g);
            g.agregarEvento(e);
        }

//        Evento evProxLleg = new Llegada(g, tiempoEjec);
//        g.agregarEvento(evProxLleg);
    }

    @Override
    public String toString() {
        return "Llegada " + tiempoEjec;
    }

    public double getProxLleg() {
        return proxLleg;
    }

    public double getRandomTipoCom() {
        return randomTipoCom;
    }

    public String getTipoComida() {
        if (randomTipoCom < 0.8) {
            return "Golos";
        } else {
            return "Com. rap";
        }
    }

    public double getRandomTiempLleg() {
        return randomTiempLleg;
    }
    
    

}
