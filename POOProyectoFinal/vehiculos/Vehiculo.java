package POOProyectoFinal.vehiculos;

// Clase base Vehiculo
public abstract class Vehiculo {
    protected String placa;
    protected String marca; // 'protected' permite acceso en subclases y el paquete.
    public String getMarca() {
        return marca;
    }

    protected double precio;
    protected int cilindraje;
    protected double impuestoCirculacion;
    protected double cuotaMesGaraje;

    // Constante para la cuota mensual por defecto
    private static final double CUOTA_DEFAULT = 100;

    // Constructor modificado para aceptar placa, marca, precio y cilindraje
    public Vehiculo(String placa, String marca, double precio, int cilindraje) {
        this.placa = placa; // Se inicializa la placa
        this.marca = marca;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.cuotaMesGaraje = CUOTA_DEFAULT; // Cuota mensual predeterminada
        calcularImpuestoCirculacion(); // Llamada al método para calcular impuestos
    }

    public Vehiculo(String marca2, double precio2, int cilindraje2) {
        //TODO Auto-generated constructor stub
    }

    // Métodos getter y setter
    public String getPlaca() {
        return placa;
    }

    public boolean setPlaca(String placa) {
        if (placa != null && placa.length() == 6) {
            this.placa = placa;
            return true;
        }
        return false;
    }

    public double getCuotaMesGaraje() {
        return cuotaMesGaraje;
    }

    public void setCuotaMesGaraje(double cuota) {
        if (cuota >= 0) {
            this.cuotaMesGaraje = cuota;
        } else {
            throw new IllegalArgumentException("La cuota no puede ser negativa.");
        }
    }

    // Método abstracto para calcular el impuesto de circulación
    protected abstract void calcularImpuestoCirculacion();
}