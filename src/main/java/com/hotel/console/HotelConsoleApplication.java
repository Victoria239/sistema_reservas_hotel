package com.hotel.console;

import com.hotel.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Punto de entrada en consola para el sistema de reservas del hotel.
 * Ofrece un menú sencillo para crear clientes, habitaciones, reservas y ejecutar check-in/check-out.
 */
public class HotelConsoleApplication {

    private static final Logger LOGGER = Logger.getLogger(HotelConsoleApplication.class.getName());
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Map<String, Habitacion> habitaciones = new HashMap<>();
    private final Map<String, Reserva> reservas = new HashMap<>();
    private final Map<String, CheckIn> checkIns = new HashMap<>();
    private final Map<String, CheckOut> checkOuts = new HashMap<>();

    public static void main(String[] args) {
        new HotelConsoleApplication().run();
    }

    private void run() {
        precargarHabitacionesDemo();
        boolean ejecutando = true;
        while (ejecutando) {
            try {
                mostrarMenu();
                int opcion = leerEntero("Seleccione una opción: ");
                ejecutando = procesarOpcion(opcion);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error durante la ejecución", e);
            }
        }
        LOGGER.info("¡Hasta pronto!");
    }

    private void mostrarMenu() {
        LOGGER.info("\n===== Sistema de Reservas - Consola =====");
        LOGGER.info("1. Registrar cliente");
        LOGGER.info("2. Registrar habitación estándar");
        LOGGER.info("3. Crear reserva");
        LOGGER.info("4. Confirmar reserva");
        LOGGER.info("5. Registrar check-in");
        LOGGER.info("6. Agregar huésped adicional al check-in");
        LOGGER.info("7. Registrar check-out");
        LOGGER.info("8. Listar reservas");
        LOGGER.info("9. Salir");
    }

    private boolean procesarOpcion(int opcion) {
        return switch (opcion) {
            case 1 -> { registrarCliente(); yield true; }
            case 2 -> { registrarHabitacionEstandar(); yield true; }
            case 3 -> { crearReserva(); yield true; }
            case 4 -> { confirmarReserva(); yield true; }
            case 5 -> { registrarCheckIn(); yield true; }
            case 6 -> { agregarHuespedAdicional(); yield true; }
            case 7 -> { registrarCheckOut(); yield true; }
            case 8 -> { listarReservas(); yield true; }
            case 9 -> false;
            default -> { LOGGER.warning("Opción inválida"); yield true; }
        };
    }

    private void registrarCliente() {
        String nombre = leerLinea("Nombre completo: ");
        String email = leerLinea("Email: ");
        if (clientes.values().stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email))) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email");
        }
        String telefono = leerLinea("Teléfono: ");
        String direccion = leerLinea("Dirección: ");

        Cliente cliente = new Cliente(UUID.randomUUID().toString(), nombre, email, telefono, direccion);
        clientes.put(cliente.getId(), cliente);
        LOGGER.info("Cliente registrado con ID: " + cliente.getId());
    }

    private void registrarHabitacionEstandar() {
        String numero = leerLinea("Número de habitación: ");
        if (habitaciones.containsKey(numero)) {
            throw new IllegalArgumentException("Ya existe una habitación con ese número");
        }
        double precio = leerDecimal("Precio por noche: ");
        int capacidad = leerEntero("Capacidad máxima: ");
        String descripcion = leerLinea("Descripción: ");
        boolean vista = leerBooleano("¿Tiene vista exterior? (s/n): ");
        boolean aire = leerBooleano("¿Tiene aire acondicionado? (s/n): ");
        boolean calefaccion = leerBooleano("¿Tiene calefacción? (s/n): ");

        Habitacion habitacion = new HabitacionEstandar(numero, precio, capacidad, descripcion, vista, aire, calefaccion);
        habitaciones.put(numero, habitacion);
        LOGGER.info("Habitación registrada correctamente");
    }

    private void crearReserva() {
        String clienteId = leerLinea("ID del cliente: ");
        Cliente cliente = clientes.get(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        String numeroHabitacion = leerLinea("Número de habitación: ");
        Habitacion habitacion = habitaciones.get(numeroHabitacion);
        if (habitacion == null) {
            throw new IllegalArgumentException("Habitación no encontrada");
        }
        LocalDate checkIn = leerFecha("Fecha de check-in (YYYY-MM-DD): ");
        LocalDate checkOut = leerFecha("Fecha de check-out (YYYY-MM-DD): ");
        int huespedes = leerEntero("Número de huéspedes: ");
        String notas = leerLinea("Notas adicionales: ");

        Reserva reserva = new Reserva(cliente, habitacion, checkIn, checkOut, huespedes, notas);
        reservas.put(reserva.getId(), reserva);
        habitacion.marcarComoOcupada();
        LOGGER.info("Reserva creada con ID: " + reserva.getId());
    }

    private void confirmarReserva() {
        Reserva reserva = obtenerReservaPorUsuario();
        reserva.confirmar();
        LOGGER.info("Reserva confirmada");
    }

    private void registrarCheckIn() {
        Reserva reserva = obtenerReservaPorUsuario();
        reserva.registrarCheckIn();
        if (checkIns.containsKey(reserva.getId())) {
            throw new IllegalStateException("La reserva ya tiene un check-in registrado");
        }
        Habitacion habitacion = reserva.getHabitacion();

        CheckIn checkIn = new CheckIn();
        checkIn.setReservaId(reserva.getId());
        checkIn.setHabitacionId(habitacion.getNumeroHabitacion());
        checkIn.setCapacidadMaxima(habitacion.getCapacidadMaxima());
        checkIn.setFechaHoraSalidaPrevista(reserva.getFechaCheckOut().atStartOfDay());

        Huesped titular = convertirClienteAHuesped(reserva.getCliente());
        titular.setTitular(true);
        checkIn.agregarHuesped(titular);
        reserva.setNumeroHuespedes(checkIn.getHuespedes().size());

        checkIns.put(reserva.getId(), checkIn);
        LOGGER.info("Check-in registrado con ID: " + checkIn.getId());
    }

    private void agregarHuespedAdicional() {
        String reservaId = leerLinea("ID de la reserva: ");
        CheckIn checkIn = checkIns.get(reservaId);
        if (checkIn == null) {
            throw new IllegalArgumentException("No existe un check-in para esa reserva");
        }
        Reserva reserva = reservas.get(reservaId);
        if (reserva == null) {
            throw new IllegalStateException("Reserva no encontrada");
        }

        Huesped huesped = new Huesped();
        huesped.setId(UUID.randomUUID().toString());
        huesped.setNombre(leerLinea("Nombre del huésped: "));
        huesped.setApellido(leerLinea("Apellido del huésped: "));
        huesped.setTipoDocumento(leerLinea("Tipo de documento: "));
        huesped.setNumeroDocumento(leerLinea("Número de documento: "));
        huesped.setEmail(leerLinea("Email del huésped: "));
        huesped.setTelefono(leerLinea("Teléfono del huésped: "));
        huesped.setTitular(leerBooleano("¿Es titular? (s/n): "));

        checkIn.agregarHuesped(huesped);
        reserva.setNumeroHuespedes(checkIn.getHuespedes().size());
        LOGGER.info("Huésped agregado correctamente");
    }

    private void registrarCheckOut() {
        Reserva reserva = obtenerReservaPorUsuario();
        CheckIn checkIn = checkIns.get(reserva.getId());
        if (checkIn == null) {
            throw new IllegalStateException("Debe existir un check-in antes de registrar el check-out");
        }
        reserva.registrarCheckOut();

        CheckOut checkOut = checkOuts.computeIfAbsent(reserva.getId(), id -> {
            CheckOut co = new CheckOut();
            co.setCheckInId(checkIn.getId());
            return co;
        });

        BigDecimal totalEstadia = BigDecimal.valueOf(reserva.getMontoTotal());
        BigDecimal totalServicios = leerBigDecimal("Total de servicios adicionales: ");
        String metodoPago = leerLinea("Método de pago: ");
        String referenciaPago = leerLinea("Referencia de pago: ");

        checkOut.liquidar(totalEstadia, totalServicios, metodoPago, referenciaPago);
        checkOuts.put(reserva.getId(), checkOut);
        reserva.getHabitacion().marcarComoDisponible();
        LOGGER.info("Check-out liquidado. Total a pagar: " + checkOut.getTotalGeneral());
    }

    private void listarReservas() {
        if (reservas.isEmpty()) {
            LOGGER.info("No hay reservas registradas");
            return;
        }
        reservas.values().forEach(reserva -> {
            LOGGER.info("---------------------------");
            LOGGER.info("ID: " + reserva.getId());
            LOGGER.info("Cliente: " + reserva.getCliente().getNombreCompleto());
            LOGGER.info("Habitación: " + reserva.getHabitacion().getNumeroHabitacion());
            LOGGER.info("Estado: " + reserva.getEstado());
            LOGGER.info("Check-in: " + reserva.getFechaCheckIn());
            LOGGER.info("Check-out: " + reserva.getFechaCheckOut());
            LOGGER.info("Monto total: " + reserva.getMontoTotal());
            if (checkIns.containsKey(reserva.getId())) {
                LOGGER.info("Huéspedes registrados: " + checkIns.get(reserva.getId()).getHuespedes().size());
            }
        });
    }

    private Reserva obtenerReservaPorUsuario() {
        String reservaId = leerLinea("ID de la reserva: ");
        Reserva reserva = reservas.get(reservaId);
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva no encontrada");
        }
        return reserva;
    }

    private void precargarHabitacionesDemo() {
        Habitacion estandar = new HabitacionEstandar("101", 80.0, 2,
                "Habitación estándar con vista", true, true, true);
        Habitacion suite = new Suite.Builder("201", 150.0, 4, "Suite ejecutiva")
                .conJacuzzi(true)
                .conMinibar(true)
                .conServicioHabitaciones(true)
                .conNumeroHabitaciones(2)
                .build();
        habitaciones.put(estandar.getNumeroHabitacion(), estandar);
        habitaciones.put(suite.getNumeroHabitacion(), suite);
    }

    private Huesped convertirClienteAHuesped(Cliente cliente) {
        Huesped huesped = new Huesped();
        huesped.setId(cliente.getId());
        huesped.setNombre(cliente.getNombre());
        huesped.setApellido("");
        huesped.setTipoDocumento("ID");
        huesped.setNumeroDocumento(cliente.getId());
        huesped.setEmail(cliente.getEmail());
        huesped.setTelefono(cliente.getTelefono());
        return huesped;
    }

    private String leerLinea(String mensaje) {
        LOGGER.info(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        return Integer.parseInt(leerLinea(mensaje));
    }

    private double leerDecimal(String mensaje) {
        return Double.parseDouble(leerLinea(mensaje));
    }

    private BigDecimal leerBigDecimal(String mensaje) {
        return new BigDecimal(leerLinea(mensaje));
    }

    private boolean leerBooleano(String mensaje) {
        String valor = leerLinea(mensaje).toLowerCase();
        return valor.startsWith("s");
    }

    private LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(leerLinea(mensaje), formatter);
    }
}
