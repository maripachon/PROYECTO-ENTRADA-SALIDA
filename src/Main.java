import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean personaYaIngresoVehiculo(List<Vehiculo> lista, String id) {
        for (Vehiculo v : lista) {
            if (v.getPersona().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean personaYaIngresoComputador(List<Computador> lista, String id) {
        for (Computador c : lista) {
            if (c.getPersona().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static String formatearFecha(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fecha.format(formatter);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Vehiculo> vehiculosDentro = new ArrayList<>();
        List<Computador> computadoresDentro = new ArrayList<>();
        int opcionPrincipal = 0;
        int subOpcion = 0;
        int cupoCarros = 24;
        int cupoMotos = 44;
        int cupoBicicletas = 40;
        int cupoPatinetas = 10;
        int tipoVehiculo;

        do {
            try {
                System.out.println("\n===== MENÚ PRINCIPAL =====");
                System.out.println("1. Registro de vehículos");
                System.out.println("2. Registro de computadores");
                System.out.println("3. Salida de vehículos");
                System.out.println("4. Salida de computadores");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcionPrincipal = sc.nextInt();
                sc.nextLine();

                switch (opcionPrincipal) {
                    case 1:
                        System.out.println("\n--- Tipos de vehículos ---");
                        System.out.println("1. Carro (disponibles: " + cupoCarros + ")");
                        System.out.println("2. Moto (disponibles: " + cupoMotos + ")");
                        System.out.println("3. Bicicleta (disponibles: " + cupoBicicletas + ")");
                        System.out.println("4. Patineta Eléctrica (disponibles: " + cupoPatinetas + ")");
                        System.out.print("Seleccione tipo de vehículo: ");


                        try {
                            tipoVehiculo = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Opción inválida. Debe ingresar un número.");
                            sc.nextLine();
                            break;
                        }

                        if (tipoVehiculo < 1 || tipoVehiculo > 4) {
                            System.out.println("Opción inválida.");
                            break;
                        }

                        if ((tipoVehiculo == 1 && cupoCarros == 0) ||
                                (tipoVehiculo == 2 && cupoMotos == 0) ||
                                (tipoVehiculo == 3 && cupoBicicletas == 0) ||
                                (tipoVehiculo == 4 && cupoPatinetas == 0)) {
                            System.out.println("No hay cupo disponible para este tipo de vehículo.");
                            break;
                        }

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("ID/Cédula: ");
                        String id = sc.nextLine();

                        if (personaYaIngresoVehiculo(vehiculosDentro, id)) {
                            System.out.println("Error: Ya hay un vehículo registrado con este ID.");
                            break;
                        }

                        System.out.print("Color: ");
                        String color = sc.nextLine();

                        Vehiculo vehiculo = null;

                        if (tipoVehiculo == 1 || tipoVehiculo == 2) {
                            System.out.print("Placa: ");
                            String placa = sc.nextLine();
                            if (tipoVehiculo == 1 && !placa.matches("[A-Z]{3}[0-9]{3}")) {
                                System.out.println("Placa inválida. Debe tener 3 letras y 3 números. Ej: (BKO372)");
                                break;
                            } else if (tipoVehiculo == 2 && !placa.matches("[A-Z]{3}[0-9]{2}[A-Z]")) {
                                System.out.println("Placa inválida. Debe tener 3 letras, 2 números y 1 letra. Ej: (MPG20W)");
                                break;
                            }
                            vehiculo = (tipoVehiculo == 1) ? new Carro(new Persona(nombre, id), color, placa)
                                    : new Moto(new Persona(nombre, id), color, placa);
                        } else {
                            System.out.print("Serial: ");
                            String serial = sc.nextLine();
                            if (!serial.matches("[A-Z a-z 0-9]{1,20}")) {
                                System.out.println("Serial inválido. Máximo 20 caracteres alfanuméricos.");
                                break;
                            }
                            vehiculo = (tipoVehiculo == 3) ? new Bicicleta(new Persona(nombre, id), color, serial)
                                    : new PatinetaElectrica(new Persona(nombre, id), color, serial);
                        }

                        vehiculosDentro.add(vehiculo);
                        if (tipoVehiculo == 1) cupoCarros--;
                        if (tipoVehiculo == 2) cupoMotos--;
                        if (tipoVehiculo == 3) cupoBicicletas--;
                        if (tipoVehiculo == 4) cupoPatinetas--;

                        System.out.println("Vehículo registrado exitosamente a las " + formatearFecha(vehiculo.getHoraIngreso()));


                        do {
                            try {
                                System.out.println("\n1. Ver vehículos dentro del parqueadero");
                                System.out.println("2. Regresar al menú principal");
                                System.out.print("Seleccione: ");
                                subOpcion = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Opción inválida. Debe ingresar un número.");
                                sc.nextLine();
                                continue;
                            }

                            if (subOpcion == 1) {
                                System.out.println("\n--- Vehículos en el parqueadero ---");
                                for (Vehiculo v : vehiculosDentro) {
                                    System.out.println("Tipo: " + v.getClass().getSimpleName());
                                    System.out.println("Nombre: " + v.getPersona().getNombre());
                                    System.out.println("ID: " + v.getPersona().getId());
                                    System.out.println("Color: " + v.getColor());
                                    if (v instanceof VehiculoConPlaca)
                                        System.out.println("Placa: " + ((VehiculoConPlaca)v).getPlaca());
                                    else if (v instanceof VehiculoConSerial)
                                        System.out.println("Serial: " + ((VehiculoConSerial)v).getSerial());
                                    System.out.println("Hora de ingreso: " + formatearFecha(v.getHoraIngreso()));
                                    System.out.println("---------------------------");
                                }
                            } else if (subOpcion != 2) {
                                System.out.println("Opción inválida.");
                            }
                        } while (subOpcion != 2);
                        break;

                    case 2:
                        System.out.print("Nombre: ");
                        String nombre2 = sc.nextLine();
                        if (nombre2.isBlank()) {
                            System.out.println("Error: El nombre no puede estar en blanco.");
                            break;
                        }

                        System.out.print("ID/Cédula: ");
                        String id2 = sc.nextLine();
                        if (id2.isBlank()) {
                            System.out.println("Error: El ID no puede estar en blanco.");
                            break;
                        }


                        if (personaYaIngresoComputador(computadoresDentro, id2)) {
                            System.out.println("Error: Ya hay un computador registrado con este ID.");
                            break;
                        }

                        System.out.print("Marca: ");
                        String marca = sc.nextLine();
                        System.out.print("Serial: ");
                        String serial = sc.nextLine();

                        if (!serial.matches("[A-Za-z0-9]{1,20}")) {
                            System.out.println("Serial inválido. Máximo 20 caracteres alfanuméricos.");
                            break;
                        }

                        Computador comp = new Computador(new Persona(nombre2, id2), marca, serial);
                        computadoresDentro.add(comp);
                        System.out.println("Computador registrado exitosamente a las " + formatearFecha(comp.getHoraIngreso()));


                        do {
                            try {
                                System.out.println("\n1. Ver computadores dentro de la universidad");
                                System.out.println("2. Regresar al menú principal");
                                subOpcion = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Opción inválida. Debe ingresar un número.");
                                sc.nextLine();
                                continue;
                            }

                            if (subOpcion == 1) {
                                System.out.println("\n--- Computadores dentro ---");
                                for (Computador c : computadoresDentro) {
                                    System.out.println("Nombre: " + c.getPersona().getNombre());
                                    System.out.println("ID: " + c.getPersona().getId());
                                    System.out.println("Marca: " + c.getMarca());
                                    System.out.println("Serial: " + c.getSerial());
                                    System.out.println("Hora de ingreso: " + formatearFecha(c.getHoraIngreso()));
                                    System.out.println("---------------------------");
                                }
                            } else if (subOpcion != 2) {
                                System.out.println("Opción inválida.");
                            }
                        } while (subOpcion != 2);
                        break;

                    case 3:
                        System.out.print("Ingrese el ID del vehículo que desea hacer salir: ");
                        String idSalidaVehiculo = sc.nextLine();
                        Vehiculo vehiculoSaliente = null;
                        for (Vehiculo v : vehiculosDentro) {
                            if (v.getPersona().getId().equals(idSalidaVehiculo)) {
                                vehiculoSaliente = v;
                                break;
                            }
                        }
                        if (vehiculoSaliente != null) {
                            vehiculosDentro.remove(vehiculoSaliente);
                            String horaSalida = formatearFecha(LocalDateTime.now());
                            if (vehiculoSaliente instanceof VehiculoConPlaca) {
                                System.out.println("El vehículo con placa " + ((VehiculoConPlaca)vehiculoSaliente).getPlaca() + " ha salido exitosamente de la universidad a las " + horaSalida + ".");
                            } else if (vehiculoSaliente instanceof VehiculoConSerial) {
                                System.out.println("El vehículo con serial " + ((VehiculoConSerial)vehiculoSaliente).getSerial() + " ha salido exitosamente de la universidad a las " + horaSalida + ".");
                            }
                        } else {
                            System.out.println("No se encontró vehículo con ese ID.");
                        }
                        break;

                    case 4:
                        System.out.print("Ingrese el ID del computador que desea hacer salir: ");
                        Computador compSaliente = null;
                        String idSalidaComputador = sc.nextLine();
                        for (Computador c : computadoresDentro) {
                            if (c.getPersona().getId().equals(idSalidaComputador)) {
                                compSaliente = c;
                                break;
                            }
                        }
                        if (compSaliente != null) {
                            computadoresDentro.remove(compSaliente);
                            String horaSalida = formatearFecha(LocalDateTime.now());
                            System.out.println("El computador con serial " + compSaliente.getSerial() + " ha salido exitosamente de la universidad a las " + horaSalida + ".");
                        } else {
                            System.out.println("No se encontró computador con ese ID.");
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Debe ingresar un número.");
                sc.nextLine();
            }
        } while (opcionPrincipal != 5);

        sc.close();
    }
}
