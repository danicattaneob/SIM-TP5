/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.eventos;

/**
 *
 * @author Dani
 */
public abstract class Evento implements Comparable<Evento> {

    protected double tiempoEjec;
    public static final int DECIMALES = 4;
    protected static final double H = 0.5;
    protected static final double ALFA = 0.03;

    public double getTiempoEjec() {
        return tiempoEjec;
    }

    public abstract void ejecutar();

    protected static double redondear(double numero, int decimales) {
        int factorRed = (int) Math.pow(10, (double) decimales);
        int i = (int) (numero * factorRed);
        double res = ((double) i) / ((double) factorRed);
        return res;
    }

    @Override
    public int compareTo(Evento e) {
        return (int) ((this.getTiempoEjec() - e.getTiempoEjec()) * 1000);
    }

    protected double calcularRK(double T, double h, double alfa, double corte) {
        double t = 0;
        do {
            double rk1 = (alfa * T);
            double rk2 = (alfa * (T + (h * rk1) / 2));
            double rk3 = (alfa * (T + (h * rk2) / 2));
            double rk4 = (alfa * (T + (h * rk3)));
            T = T + (h / 6) * (rk1 + 2 * rk2 + 2 * rk3 + rk4);
            t += h;
        } while (t < corte);
        return T;
    }
}
