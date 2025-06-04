package Modelo;
import java.util.ArrayList;

public class Facturacion {
    private Pelicula peliculaSeleccionada;
    private int cantidadBoletos;
    private ArrayList<Snack> listaSnacks;

    public Facturacion() {
        listaSnacks = new ArrayList<>();
    }

    public void seleccionarPelicula(Pelicula p) {
        peliculaSeleccionada = p;
    }

    public void seleccionarCantidad(int cantidad) {
        cantidadBoletos = cantidad;
    }

    public void agregarSnack(Snack s) {
        listaSnacks.add(s);
    }

    public double calcularTotal() {
        double subtotal = (cantidadBoletos * 5.5);
        for (Snack s : listaSnacks) {
            subtotal += s.getPrecio();
        }
        double descuentoAplicado = 0.0;
        if (peliculaSeleccionada.getPromocion() != null) {
            double porcentaje = peliculaSeleccionada.getPromocion().getDescuento();
            descuentoAplicado = subtotal * (porcentaje / 100);
        }
        double total = subtotal - descuentoAplicado;
        return total;
    }
}