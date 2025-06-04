package Modelo;
public class Promocion {
    private String dia;
    private double descuento;

    public Promocion(String dia, double descuento) {
        this.dia = dia;
        this.descuento = descuento;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}