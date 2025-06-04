package Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Venta {
    private static int contadorVentas = 0;

    private int numeroVenta;
    private Pelicula pelicula;
    private int cantidadBoletos;
    private ArrayList<Snack> snacks;
    private double total;

    public Venta(Pelicula pelicula, int cantidadBoletos, ArrayList<Snack> snacks, double total) {
        this.numeroVenta = ++contadorVentas;
        this.pelicula = pelicula;
        this.cantidadBoletos = cantidadBoletos;
        this.snacks = snacks;
        this.total = total;

        guardarEnArchivo();
    }

    @Override
    public String toString() {
        String resultado = numeroVenta + ": " +
                "Película: " + pelicula.getTitulo() +
                " | Horario: " + pelicula.getHorario() +
                " | Sala: " + pelicula.getSala() +
                " | Boletos: " + cantidadBoletos +
                " | Snacks: ";

        for (int i = 0; i < snacks.size(); i++) {
            Snack s = snacks.get(i);
            resultado += s.getNombre() + " ($" + s.getPrecio() + ")";
            if (i < snacks.size() - 1) {
                resultado += ", ";
            }
        }

        resultado += " | Total: $" + total;
        return resultado;
    }

    public void guardarEnArchivo() {
        try {
            FileWriter fw = new FileWriter("ventas.txt", true);
            fw.write(this.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la venta en el archivo: " + e.getMessage());
        }
    }
}