package POOProyectoFinal.vehiculos;

import POOProyectoFinal.vehiculos.Camion;
public class Camion extends Vehiculo {
    private int cantidadEjes;
    public int getCantidadEjes() {
        return cantidadEjes;
    }
    private double capacidadCarga;
    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public Camion(String placa, String marca, double precio, int cilindraje, int cantidadEjes, double capacidadCarga) {
        super(placa, marca, precio, cilindraje); // Llamada al constructor de la clase base Vehiculo
        this.cantidadEjes = cantidadEjes;
        this.capacidadCarga = capacidadCarga;
    }

    // Métodos adicionales de la clase Camion
    private int numeroEjes; // Otros atributos y código de la clase.

    // Getter y Setter para 'numeroEjes'
    public int getNumeroEjes() {
        return numeroEjes;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    // Otros métodos de la clase

    private String tipoCamion; // "Sencillo" o "Doble"
    

    public Camion(String marca, double precio, int cilindraje, int numeroEjes, double capacidadCarga) {
        super(marca, precio, cilindraje);
        this.numeroEjes = numeroEjes;
        this.capacidadCarga = capacidadCarga;
        this.tipoCamion = (numeroEjes == 2) ? "Sencillo" : "Doble";

        calcularImpuestoCirculacion();
        if ("Sencillo".equals(tipoCamion)) {
            this.cuotaMesGaraje *= 1.75; // Incremento del 75%
        } else if ("Doble".equals(tipoCamion)) {
            this.cuotaMesGaraje *= 2.25; // Incremento del 125%
        }
    }

    @Override
    protected void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = this.precio * 0.09 + (10 * Math.floor(capacidadCarga / 5));
    }
}