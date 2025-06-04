package Controlador;
import Modelo.*;
import Vista.Vista;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    private Cartelera cartelera;
    private Facturacion facturacion;
    private Vista vista;
    private ArrayList<Promocion> promociones;
    private Scanner scanner;

    public Controlador() {
        this.cartelera = new Cartelera();
        this.facturacion = new Facturacion();
        this.vista = new Vista();
        this.promociones = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        cartelera.cargarPeliculas("peliculas.csv");

        ListSnacks listaSnacks = new ListSnacks();
        listaSnacks.cargarSnacksDesdeCSV("snacks.csv");

        promociones.clear();
        promociones.add(new Promocion("Lunes", 2));
        promociones.add(new Promocion("Viernes", 5));
        promociones.add(new Promocion("Sabado", 3));

        mostrarPeliculas();
        realizarVenta();
    }

    public void mostrarPeliculas() {
        vista.mostrarPeliculas(cartelera.obtenerPeliculas());
        vista.mostrarPromociones(promociones);
    }

    public void realizarVenta() {
        System.out.print("Seleccione una pelicula (numero): ");
        int indice = scanner.nextInt();
        Pelicula seleccionada = cartelera.obtenerPeliculas().get(indice - 1);

        facturacion.seleccionarPelicula(seleccionada);

        System.out.print("Cantidad de boletos: ");
        int cantidad = scanner.nextInt();
        facturacion.seleccionarCantidad(cantidad);

        ListSnacks listaSnacks = new ListSnacks();
        listaSnacks.cargarSnacksDesdeCSV("snacks.csv");
        ArrayList<Snack> snacks = vista.solicitarSnacksDisponibles(listaSnacks.listaSnacks());
        for (Snack s : snacks) {
            facturacion.agregarSnack(s);
        }

        double total = facturacion.calcularTotal();
        vista.mostrarFactura(seleccionada, cantidad, snacks, total);

        Venta venta = new Venta(seleccionada, cantidad, snacks, total);
    }
}