/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.TreeSet;
import logica.eventos.*;
import logica.entidades.*;

/**
 *
 * @author Dani
 */
public class Gestor {

    private int cantSim;
    private Cola cola;
    private TreeSet<Evento> colaEventos;
    private Tiempo tiempo;
    private Jefe jefe;
    private Ayudante ayudante;
    private TreeSet<FinEntrPed> colaEvDeFinPed;
    private Acumulador acumulador;

    public Gestor() {
    }

    public void simular(int cantSim) {
        this.cantSim = cantSim;
        init();

        int i = 0;
        while (hayEventos() && i < cantSim) {
            Evento e = proximoEvento();
            tiempo.setTime(e.getTiempoEjec());
            e.ejecutar();
            System.out.print(e.toString());
            System.out.println("\t\tCola: " + cola.genteEnCola());
            acumulador.acumular(e.getTiempoEjec(), this);
            i++;
        }
    }

    public void init() {
        acumulador = new Acumulador();
        tiempo = Tiempo.getInstance();
        tiempo.clear();
        cola = new Cola();
        jefe = new Jefe();
        ayudante = new Ayudante();
        colaEventos = new TreeSet();
        colaEvDeFinPed = new TreeSet();
        agregarEvento(new Llegada(this, 0));
    }

    private Evento proximoEvento() {
        if (colaEvDeFinPed.isEmpty()) {
            return colaEventos.pollFirst();
        }
        Evento e1 = colaEventos.first();
        Evento e2 = colaEvDeFinPed.first();
        if (e1.compareTo(e2) > 0) {
            return colaEventos.pollFirst();
        } else {
            return colaEvDeFinPed.pollFirst();
        }
    }

    public void agregarEvento(Evento e) {
        colaEventos.add(e);
    }

    public double getTiempo() {
        return tiempo.getTime();
    }

    public void agregarTiempo(double t) {
        tiempo.agregarTiempo(t);
    }

    public Cola getCola() {
        return cola;
    }

    public Jefe getJefe() {
        return jefe;
    }

    public Ayudante getAyudante() {
        return ayudante;
    }

    public void agregarEvDeFinPed(FinEntrPed e) {
        colaEvDeFinPed.add(e);
    }

    public boolean hayPedidos() {
        return !colaEvDeFinPed.isEmpty();
    }

    //Le falta - divide solo su tiempo y no suma el tiempo actual
    public void duplicarTiempoPed(double tiempoAct) {
        TreeSet<FinEntrPed> nuevaCola = new TreeSet();
        for (int i = 0; i < colaEvDeFinPed.size(); i++) {
            FinEntrPed e = colaEvDeFinPed.pollFirst();
            e.duplicarTiempo(tiempoAct);
            nuevaCola.add(e);
        }
        
        colaEvDeFinPed = nuevaCola;
        
//        for (FinEntrPed e : colaEvDeFinPed) {
//            e = (FinEntrPed) e;
//            e.duplicarTiempo(tiempoAct);
//        }
    }

    //idem al de arriba
    public void dividirTiempoPed(double tiempoAct) {
        TreeSet<FinEntrPed> nuevaCola = new TreeSet();
        for (int i = 0; i < colaEvDeFinPed.size(); i++) {
            FinEntrPed e = colaEvDeFinPed.pollFirst();
            e.dividirTiempo(tiempoAct);
            nuevaCola.add(e);
        }
        colaEvDeFinPed = nuevaCola;
        
//        for (FinEntrPed e : colaEvDeFinPed) {
//            e.dividirTiempo(tiempoAct);
//        }
    }
    
    private boolean hayEventos(){
        return !(colaEventos.isEmpty() && colaEvDeFinPed.isEmpty());
    }
}
