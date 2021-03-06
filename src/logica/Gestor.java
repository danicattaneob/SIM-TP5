/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import gui.Columna;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Columna columnaAnterior;
    private double tiempoUltimoPurgado;
    private Purgado proxPurg;

    public Gestor() {
    }

    public ObservableList<Columna> simular(int cantSim, int desde, int hasta) {
        ObservableList<Columna> lista = FXCollections.observableArrayList();
        this.cantSim = cantSim;
        init();
        Columna primeraCol = armarPrimeraColumna(colaEventos.first().getTiempoEjec());
        lista.add(primeraCol);

        int i = 0;
        while (hayEventos() && i < cantSim) {
            Evento e = proximoEvento();
            tiempo.setTime(e.getTiempoEjec());
            e.ejecutar();
            acumulador.acumular(e.getTiempoEjec(), this);

            Columna col = armarColumna(e);
            if (i >= desde && i < hasta) {
                lista.add(col);
            }

            i++;
            debug(e);
        }
        return lista;
    }

    private void debug(Evento e) {
        System.out.print(e.toString());
        System.out.print("\t Cola: " + cola.genteEnCola());
        System.out.print("\t Jefe: " + jefe.getEstado().toString());
        System.out.println("\t\t Ayudante: " + ayudante.getEstado().toString());
        //System.out.println("\t\t\t Ac tiempo Jefe en cocina: " + acumulador.getTiempoJefeCocina());
        //System.out.println("\t Ac tiempo Ayudante tabajando: " + acumulador.getTiempoAyudanteTrabajando());
    }

    public void init() {
        columnaAnterior = new Columna();
        acumulador = new Acumulador();
        tiempo = Tiempo.getInstance();
        tiempo.clear();
        cola = new Cola();
        jefe = new Jefe();
        ayudante = new Ayudante();
        colaEventos = new TreeSet();
        colaEvDeFinPed = new TreeSet();
        agregarEvento(new Llegada(this, 0));
        proxPurg = new Purgado(this);
        colaEventos.add(proxPurg);
        tiempoUltimoPurgado = 0;
    }

    private Evento proximoEvento() {
        if (colaEvDeFinPed.isEmpty()) {
            return colaEventos.pollFirst();
        }
        Evento e1 = colaEventos.first();
        Evento e2 = colaEvDeFinPed.first();
        if (e1.compareTo(e2) < 0) {
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

    private boolean hayEventos() {
        return !(colaEventos.isEmpty() && colaEvDeFinPed.isEmpty());
    }

    public double porcTiempOcAy() {
        return redondear((acumulador.getTiempoAyudanteLibre() / tiempo.getTime()) * 100, Evento.DECIMALES);
    }

    public double porcTiempCocJef() {
        return redondear((acumulador.getTiempoJefeCocina() / tiempo.getTime()) * 100, Evento.DECIMALES);
    }

    public double porcTiempMostJef() {
        return redondear((acumulador.getTiempoJefeMostrador() / tiempo.getTime()) * 100, Evento.DECIMALES);
    }

    public Columna armarColumna(Evento e) {
        Columna nuevaCol = new Columna();
        nuevaCol.setReloj(Double.toString(tiempo.getTime()));
        nuevaCol.setEvento(e.toString());

        //Puesta en cero
        nuevaCol.setRndLleg("");
        nuevaCol.setTiemEnLleg("");
        nuevaCol.setRndTipC("");
        nuevaCol.setTipoCom("");

        //Valores acarreados
        nuevaCol.setRndPorcFall("");
        nuevaCol.setPorcFall("");
        nuevaCol.setTiempEntrePurg("");
        nuevaCol.setTiempProxPurg(columnaAnterior.getTiempProxPurg());
        nuevaCol.setTiempPurga("");
        nuevaCol.setTiempFinPurg(columnaAnterior.getTiempFinPurg());

        nuevaCol.setProxLleg(columnaAnterior.getProxLleg());
        if (columnaAnterior.getFinAt().equals(Double.toString(tiempo.getTime()))) {
            nuevaCol.setFinAt("");
        } else {
            nuevaCol.setFinAt(columnaAnterior.getFinAt());
        }

        if (columnaAnterior.getFinTansm().equals(Double.toString(tiempo.getTime()))) {
            nuevaCol.setFinTansm("");
        } else {
            nuevaCol.setFinTansm(columnaAnterior.getFinTansm());
        }

        if (!colaEvDeFinPed.isEmpty()) {
            nuevaCol.setFinPrepC(Double.toString(colaEvDeFinPed.first().getTiempoEjec()));
            //nuevaCol.setFinTansm(Double.toString(colaEvDeFinPed.first().getTiempoEjec()));
        } else {
            nuevaCol.setFinPrepC("");
        }
        //nuevaCol.setFinPrepC(columnaAnterior.getFinPrepC());

        if (e instanceof Llegada) {
            nuevaCol.setRndLleg(Double.toString(((Llegada) e).getRandomTiempLleg()));
            nuevaCol.setTiemEnLleg(Double.toString(-(tiempo.getTime() - ((Llegada) e).getProxLleg())));
            nuevaCol.setProxLleg(Double.toString(((Llegada) e).getProxLleg()));
            nuevaCol.setRndTipC(Double.toString(((Llegada) e).getRandomTipoCom()));
            nuevaCol.setTipoCom(((Llegada) e).getTipoComida());
            if (((Llegada) e).fueAtendido()) {
                if (((Llegada) e).getProxEvento() instanceof FinAtGol) {
                    nuevaCol.setRndFinAtG(Double.toString(((FinAtGol) ((Llegada) e).getProxEvento()).getRndFinAt()));
                    nuevaCol.setTiemAt(Double.toString(((FinAtGol) ((Llegada) e).getProxEvento()).getTiempAt()));
                    nuevaCol.setFinAt(Double.toString(((FinAtGol) ((Llegada) e).getProxEvento()).getTiempoEjec()));
                } else {
                    nuevaCol.setTransmP(Double.toString(((FinTrans) ((Llegada) e).getProxEvento()).getTiempoTansm()));
                    nuevaCol.setFinTansm(Double.toString(((FinTrans) ((Llegada) e).getProxEvento()).getTiempoEjec()));
                }
            }
        }

        if (e instanceof FinTrans) {
            nuevaCol.setRndPrepC(Double.toString(((FinTrans) e).rndTiempPrep()));
            nuevaCol.setTiempPrepC(Double.toString(((FinTrans) e).tiempoPrep()));
            nuevaCol.setFinPrepC(Double.toString(((FinTrans) e).finPrep()));

        }

        if (e instanceof Purgado) {
            nuevaCol.setRndPorcFall("");
            nuevaCol.setPorcFall("");
            nuevaCol.setTiempEntrePurg("");
            nuevaCol.setTiempProxPurg("");
        }

        if (e instanceof FinPurgado) {
            nuevaCol.setRndPorcFall(Double.toString(proxPurg.getRandomPorcInest()));
            nuevaCol.setPorcFall(Double.toString(proxPurg.getPorcInest()));
            nuevaCol.setTiempEntrePurg(Double.toString(proxPurg.getTiempEntrePurg()));
            nuevaCol.setTiempProxPurg(Double.toString(proxPurg.getTiempoEjec()));
            nuevaCol.setTiempPurga(Double.toString(FinPurgado.getTiempoPurgado()));
            nuevaCol.setTiempFinPurg(Double.toString(proxPurg.getTiempoEjec() + FinPurgado.getTiempoPurgado()));
        }

        nuevaCol.setEstJefe(jefe.getEstado().toString());
        nuevaCol.setEstCola(Integer.toString(cola.genteEnCola()));
        nuevaCol.setAcTiemMost(Double.toString(acumulador.getTiempoJefeMostrador()));
        nuevaCol.setAcTiemCoc(Double.toString(acumulador.getTiempoJefeCocina()));
        nuevaCol.setEstAy(ayudante.getEstado().toString());
        nuevaCol.setAcTiemTrab(Double.toString(acumulador.getTiempoAyudanteTrabajando()));
        nuevaCol.setAcTiemLibre(Double.toString(acumulador.getTiempoAyudanteLibre()));

        columnaAnterior = nuevaCol;

        return nuevaCol;
    }

    public Columna armarPrimeraColumna(double proxLleg) {
        Columna nuevaCol = new Columna();

        nuevaCol.setReloj(Integer.toString(0));
        nuevaCol.setEvento("Init");
        nuevaCol.setRndLleg("");
        nuevaCol.setTiemEnLleg("");
        nuevaCol.setProxLleg(Double.toString(proxLleg));
        nuevaCol.setRndTipC("");
        nuevaCol.setTipoCom("");
        nuevaCol.setRndFinAtG("");
        nuevaCol.setTiemAt("");
        nuevaCol.setFinAt("");
        nuevaCol.setTransmP("");
        nuevaCol.setFinTansm("");

        //Hay un error magico
        nuevaCol.setRndPorcFall(Double.toString(proxPurg.getRandomPorcInest()));
        nuevaCol.setPorcFall(Double.toString(proxPurg.getPorcInest()));
        nuevaCol.setTiempEntrePurg(Double.toString(proxPurg.getTiempEntrePurg()));
        //nuevaCol.settEntrePurg("Hola");
        nuevaCol.setTiempProxPurg(Double.toString(proxPurg.getTiempoEjec()));
        nuevaCol.setTiempPurga(Double.toString(FinPurgado.getTiempoPurgado()));
        nuevaCol.setTiempFinPurg(Double.toString(proxPurg.getTiempoEjec() + FinPurgado.getTiempoPurgado()));

        nuevaCol.setRndPrepC("");
        nuevaCol.setTiempPrepC("");
        nuevaCol.setFinPrepC("");
        nuevaCol.setEstJefe(jefe.getEstado().toString());
        nuevaCol.setEstCola(Integer.toString(cola.genteEnCola()));
        nuevaCol.setAcTiemMost(Double.toString(acumulador.getTiempoJefeMostrador()));
        nuevaCol.setAcTiemCoc(Double.toString(acumulador.getTiempoJefeCocina()));
        nuevaCol.setEstAy(ayudante.getEstado().toString());
        nuevaCol.setAcTiemTrab(Double.toString(acumulador.getTiempoAyudanteTrabajando()));
        nuevaCol.setAcTiemLibre(Double.toString(acumulador.getTiempoAyudanteLibre()));

        columnaAnterior = nuevaCol;
        return nuevaCol;
    }

    private static double redondear(double numero, int decimales) {
        int factorRed = (int) Math.pow(10, (double) decimales);
        int i = (int) (numero * factorRed);
        double res = ((double) i) / factorRed;
        return res;
    }

    public double getTiempoUltimoPurgado() {
        return tiempoUltimoPurgado;
    }

    public void setTiempoUltimoPurgado(double tiempoUltimoPurgado) {
        this.tiempoUltimoPurgado = tiempoUltimoPurgado;
    }

    public void setProxPurg(Purgado proxPurg) {
        this.proxPurg = proxPurg;
    }

    public Purgado getProxPurg() {
        return proxPurg;
    }

}
