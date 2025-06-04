package Modelo;
import java.io.*;
import java.util.ArrayList;

public class Cartelera {
    private ArrayList<Pelicula> peliculas = new ArrayList<>();

    public void cargarPeliculas(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String titulo = datos[0];
                String horario = datos[1];
                String sala = datos[2];
                String diaPromo = (datos[3] != null && !datos[3].isEmpty()) ? datos[3] : null;
                Double descuento = (datos[4] != null && !datos[4].isEmpty()) ? Double.parseDouble(datos[4]) : null;
                Promocion promo = (diaPromo != null && descuento != null) ? new Promocion(diaPromo, descuento) : null;
                Pelicula p = new Pelicula(titulo, horario, sala, promo);
                peliculas.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo peliculas: " + e.getMessage());
        }
    }

    public ArrayList<Pelicula> obtenerPeliculas() {
        return peliculas;
    }
}