package Vista;
import Modelo.Pelicula;
import Modelo.Promocion;
import Modelo.Snack;

import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    public Scanner sc = new Scanner(System.in);

    public void mostrarPeliculas(ArrayList<Pelicula> peliculas) {
        System.out.println("+==========================================+");
        System.out.println("|         C I N E M A S   -   L O J A      |");
        System.out.println("+==========================================+");
        System.out.println("| Películas disponibles:                   |");
        System.out.println("|------------------------------------------|");
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula p = peliculas.get(i);
            System.out.printf("| %d. %-14s | Hora: %-5s | %s |\n",
                            (i + 1),
                            p.getTitulo(),
                            p.getHorario(),
                            p.getSala());
        }
        System.out.println("+------------------------------------------+");
    }

    public void mostrarPromociones(ArrayList<Promocion> promociones) {
        if (promociones.isEmpty()) return;
        System.out.println("| Promociones disponibles:                 |");
        System.out.println("|------------------------------------------|");
        for (Promocion promo : promociones) {
            System.out.printf("| Día: %-10s | Descuento: %.1f%%        |\n",
                            promo.getDia(),
                            promo.getDescuento());
        }
        System.out.println("+------------------------------------------+\n");
    }

    public ArrayList<Snack> solicitarSnacksDisponibles(ArrayList<Snack> menu) {
        ArrayList<Snack> snacksElegidos = new ArrayList<>();

        System.out.println("\n+--------------------+");
        System.out.println("| Menu de Snacks     |");
        System.out.println("+--------------------+");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getNombre() + " - $" + menu.get(i).getPrecio());
        }

        while (true) {
            System.out.print("Seleccione snack (0 para finalizar): ");
            int opcion = sc.nextInt();
            if (opcion == 0) break;
            if (opcion > 0 && opcion <= menu.size()) {
                snacksElegidos.add(menu.get(opcion - 1));
            } else {
                System.out.println("Opcion invalida.");
            }
        }
        return snacksElegidos;
    }

    public void mostrarFactura(Pelicula p, int boletos, ArrayList<Snack> snacks, double total) {
        System.out.println("\n+--------------------+");
        System.out.println("|    FACTURA FINAL   |");
        System.out.println("+--------------------+");
        System.out.println("Pelicula: " + p.getTitulo());
        System.out.println("Horario: " + p.getHorario());
        System.out.println("Sala: " + p.getSala());
        System.out.println("Boletos: " + boletos);
        System.out.print("Snacks: ");
        for (Snack s : snacks) {
            System.out.print(s.getNombre() + " ");
        }
        System.out.println();

        double precioBoleto = 5.0;
        double subtotal = boletos * precioBoleto;
        for (Snack s : snacks) {
            subtotal += s.getPrecio();
        }

        double descuentoAplicado = 0.0;
        if (p.getPromocion() != null) {
            double porcentaje = p.getPromocion().getDescuento();
            descuentoAplicado = subtotal * (porcentaje / 100);
            System.out.printf("Promocion (%s - %.1f%%): -$%.2f\n",
                    p.getPromocion().getDia(), porcentaje, descuentoAplicado);
        }

        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.println("===========================");
        System.out.printf("TOTAL A PAGAR: $%.2f\n", subtotal - descuentoAplicado);
        System.out.println("===========================");
    }
}