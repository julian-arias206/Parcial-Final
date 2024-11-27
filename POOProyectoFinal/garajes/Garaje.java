package POOProyectoFinal.garajes;

import POOProyectoFinal.vehiculos.Vehiculo;
import POOProyectoFinal.excepciones.*;

public class Garaje {
    private String nombreGaraje;
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String nombreAdministrador;

    // Atributos relacionados con la capacidad
    private Vehiculo[] espacios;
    private int capacidad;
    public int getCapacidad() {
        return capacidad; // Retorna la capacidad del garaje
    }

    private int ocupacionActual;
    private int maxMotosPermitidas;
    private int maxCamionesPermitidos;
    private int numMotos;
    public int getNumMotos() {
        return numMotos;
    }

    private int numCamiones;

    public int getNumCamiones() {
        return numCamiones;
    }

    public Vehiculo[] getEspacios() {
        return espacios; // Retorna el arreglo de espacios ocupados por los vehículos
    }

    public Garaje(String nombreGaraje, int capacidad, String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador) {
        this.nombreGaraje = nombreGaraje;
        this.capacidad = capacidad;
        this.ocupacionActual = 0;
        this.numMotos = 0;
        this.numCamiones = 0;

        // Datos de ubicación y contacto
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;

        // Definir las políticas de cantidad de vehículos
        if (capacidad < 100) {
            this.maxCamionesPermitidos = 10;
            this.maxMotosPermitidas = (int) (capacidad * 0.20); // 20% de la capacidad para motos
        } else {
            this.maxCamionesPermitidos = 20;
            this.maxMotosPermitidas = (int) (capacidad * 0.20); // 20% de la capacidad para motos
        }

        this.espacios = new Vehiculo[capacidad];
    }

    // Método para alquilar espacio en el garaje
    public boolean alquilarEspacio(Vehiculo vehiculo) throws TipoVehiculoNoPermitidoException, CapacidadExcedidaPorPoliticaException, VehiculoYaRegistradoException {
        if (vehiculo instanceof POOProyectoFinal.vehiculos.Moto && numMotos >= maxMotosPermitidas) {
            throw new CapacidadExcedidaPorPoliticaException("No se puede agregar más motos a este garaje debido a las políticas.");
        }

        if (vehiculo instanceof POOProyectoFinal.vehiculos.Camion && numCamiones >= maxCamionesPermitidos) {
            throw new CapacidadExcedidaPorPoliticaException("No se puede agregar más camiones a este garaje debido a las políticas.");
        }

        // Verificar si ya hay espacio
        if (ocupacionActual >= capacidad) {
            System.out.println("El garaje está lleno.");
            return false;
        }

        for (int i = 0; i < capacidad; i++) {
            if (espacios[i] == null) {
                espacios[i] = vehiculo;
                ocupacionActual++;

                if (vehiculo instanceof POOProyectoFinal.vehiculos.Moto) {
                    numMotos++;
                }
                if (vehiculo instanceof POOProyectoFinal.vehiculos.Camion) {
                    numCamiones++;
                }

                System.out.println("Vehículo alquilado en el garaje.");
                return true;
            }
        }
        return false;
    }

    // Método para retirar espacio en el garaje
    public boolean retirarEspacio(String placa) {
        for (int i = 0; i < capacidad; i++) {
            if (espacios[i] != null && espacios[i].getPlaca().equals(placa)) {
                if (espacios[i] instanceof POOProyectoFinal.vehiculos.Moto) {
                    numMotos--;
                }
                if (espacios[i] instanceof POOProyectoFinal.vehiculos.Camion) {
                    numCamiones--;
                }
                espacios[i] = null;
                ocupacionActual--;
                System.out.println("Vehículo retirado del garaje.");
                return true;
            }
        }
        System.out.println("No se encontró un vehículo con esa placa.");
        return false;
    }

    // Métodos para obtener la información del garaje
    public String getNombreGaraje() {
        return nombreGaraje;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public int getOcupacionActual() {
        return ocupacionActual;
    }

    public int getMaxMotosPermitidas() {
        return maxMotosPermitidas;
    }

    public int getMaxCamionesPermitidos() {
        return maxCamionesPermitidos;
    }

    // Método para calcular el recaudo mensual
    public double calcularRecaudoMensual() {
        double total = 0;
        for (Vehiculo vehiculo : espacios) {
            if (vehiculo != null) {
                total += vehiculo.getCuotaMesGaraje();
            }
        }
        return total;
    }
}