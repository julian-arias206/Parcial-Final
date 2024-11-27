package POOProyectoFinal.main;

import POOProyectoFinal.vehiculos.*;
import POOProyectoFinal.garajes.*;
import POOProyectoFinal.excepciones.*;
import java.util.Scanner;

public class GestorGarajes {
    public static void main(String[] args) {
        RedGarajes red = new RedGarajes();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Gestión de Garajes ---");
            System.out.println("1. Crear Garaje");
            System.out.println("2. Alquilar espacio");
            System.out.println("3. Retirar vehículo");
            System.out.println("4. Listar vehículos");
            System.out.println("5. Informe de recaudo mensual");
            System.out.println("6. Generar informes");
            System.out.println("7. Eliminar Garaje");  // Nueva opción
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    // Crear garaje con todos los datos de ubicación y administrador
                    System.out.print("Nombre del garaje: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Capacidad del garaje: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    System.out.print("Departamento: ");
                    String departamento = scanner.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudad = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nombre del administrador: ");
                    String nombreAdministrador = scanner.nextLine();

                    // Crear el garaje y agregarlo a la red
                    Garaje nuevoGaraje = new Garaje(
                            nombre, capacidad, departamento, ciudad, direccion, telefono, email, nombreAdministrador
                    );
                    red.agregarGaraje(nuevoGaraje);
                    System.out.println("Garaje creado y agregado a la red.");
                }
                case 2 -> {
                    // Alquilar espacio en un garaje
                    System.out.print("Nombre del garaje: ");
                    String nombreGaraje = scanner.nextLine();
                    Garaje garaje = red.buscarGaraje(nombreGaraje);
                    if (garaje != null) {
                        System.out.print("Placa del vehículo: ");
                        String placa = scanner.nextLine();
                        System.out.print("Tipo de vehículo (1: Moto, 2: Auto, 3: Camion): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        // Creación de vehículo según tipo
                        Vehiculo vehiculo;
                        if (tipo == 1) {
                            vehiculo = new Moto(placa, "Honda", 5000, 250, false); // Asumimos datos predeterminados
                        } else if (tipo == 2) {
                            vehiculo = new Auto(placa, "Toyota", 10000, 1600, true, false);
                        } else {
                            vehiculo = new Camion(placa, "Volvo", 20000, 3000, 2, 10);
                        }

                        try {
                            // Intentar alquilar el espacio para el vehículo
                            garaje.alquilarEspacio(vehiculo);
                        } catch (TipoVehiculoNoPermitidoException | CapacidadExcedidaPorPoliticaException | VehiculoYaRegistradoException e) {
                            System.out.println("Error al alquilar el espacio: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Garaje no encontrado.");
                    }
                }
                case 3 -> {
                    // Retirar vehículo
                    System.out.print("Nombre del garaje: ");
                    String nombreGaraje = scanner.nextLine();
                    Garaje garaje = red.buscarGaraje(nombreGaraje);
                    if (garaje != null) {
                        System.out.print("Placa del vehículo: ");
                        String placa = scanner.nextLine();
                        boolean retirado = garaje.retirarEspacio(placa);
                        if (retirado) {
                            System.out.println("Vehículo retirado del garaje.");
                        } else {
                            System.out.println("No se encontró un vehículo con esa placa.");
                        }
                    } else {
                        System.out.println("Garaje no encontrado.");
                    }
                }
                case 4 -> {
                    // Listar vehículos en todos los garajes
                    red.listarTodosVehiculos();
                }
                case 5 -> {
                    // Calcular los ingresos totales
                    System.out.println("Ingresos totales: $" + red.calcularRecaudoTotal());
                }
                case 6 -> {
                    // Generar informes
                    System.out.println("\n--- Informes ---");
                    red.informeOcupacionPorGaraje();
                    red.informeOcupacionPorTipoVehiculo();
                }
                case 7 -> {
                    // Eliminar garaje
                    System.out.print("Ingrese el nombre del garaje a eliminar: ");
                    String nombreGaraje = scanner.nextLine();
                    Garaje garaje = red.buscarGaraje(nombreGaraje);
                    if (garaje != null) {
                        red.eliminarGaraje(nombreGaraje);
                        System.out.println("Garaje eliminado de la red.");
                    } else {
                        System.out.println("No se encontró un garaje con ese nombre.");
                    }
                }
                case 8 -> {
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}