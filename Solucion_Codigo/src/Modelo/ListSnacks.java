package Modelo;
import java.io.*;
import java.util.*;

public class ListSnacks {
    private ArrayList<Snack> snacks = new ArrayList<>();

    public void cargarSnacksDesdeCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                double precio = Double.parseDouble(datos[1]);
                snacks.add(new Snack(nombre, precio));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo snacks: " + e.getMessage());
        }
    }

    public ArrayList<Snack> listaSnacks() {
        return snacks;
    }
}