package POOProyectoFinal.garajes;

import POOProyectoFinal.vehiculos.*;
import java.util.ArrayList;

public class RedGarajes {
    private ArrayList<Garaje> garajes;

    public RedGarajes() {
        this.garajes = new ArrayList<>();
    }

    public void agregarGaraje(Garaje garaje) {
        garajes.add(garaje);
        System.out.println("Garaje agregado a la red.");
    }

    public void eliminarGaraje(String nombreGaraje) {
        garajes.removeIf(g -> g.getNombreGaraje().equals(nombreGaraje));
        System.out.println("Garaje eliminado de la red.");
    }

    public Garaje buscarGaraje(String nombreGaraje) {
        for (Garaje garaje : garajes) {
            if (garaje.getNombreGaraje().equals(nombreGaraje)) {
                return garaje;
            }
        }
        return null;
    }

    public void listarTodosVehiculos() {
        for (Garaje garaje : garajes) {
            System.out.println("\nGaraje: " + garaje.getNombreGaraje());
            for (Vehiculo vehiculo : garaje.getEspacios()) {
                if (vehiculo != null) {
                    System.out.println("- Placa: " + vehiculo.getPlaca() + ", Marca: " + vehiculo.getMarca());
                }
            }
        }
    }

    public double calcularRecaudoTotal() {
        double total = 0;
        for (Garaje garaje : garajes) {
            total += garaje.calcularRecaudoMensual();
        }
        return total;
    }

    // Informes
    public void informeOcupacionPorGaraje() {
        for (Garaje garaje : garajes) {
            System.out.println("Garaje: " + garaje.getNombreGaraje() + " - Ocupación: " + garaje.getOcupacionActual() + "/" + garaje.getCapacidad());
        }
    }

    public void informeOcupacionPorTipoVehiculo() {
        for (Garaje garaje : garajes) {
            System.out.println("Garaje: " + garaje.getNombreGaraje());
            System.out.println("Motos: " + garaje.getNumMotos() + "/" + garaje.getMaxMotosPermitidas());
            System.out.println("Camiones: " + garaje.getNumCamiones() + "/" + garaje.getMaxCamionesPermitidos());
        }
    }
}