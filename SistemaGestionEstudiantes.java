import java.util.ArrayList;
import java.util.Scanner;

class Estudiante {
    private String nombre;
    private int edad;
    private double promedio;

    public Estudiante(String nombre, int edad, double promedio) {
        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    
    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
    
    @Override
    public String toString() {
        return String.format("Nombre: %s | Edad: %d | Promedio: %.2f", 
                           nombre, edad, promedio);
    }
}

public class GestionEstudiantes {
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            
            switch (opcion) {
                case 1:
                    agregarEstudiante();
                    break;
                case 2:
                    listarEstudiantes();
                    break;
                case 3:
                    actualizarEstudiante();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\nSISTEMA DE GESTIÓN DE ESTUDIANTES");
        System.out.println("1. Agregar estudiante.");
        System.out.println("2. Listar estudiantes.");
        System.out.println("3. Actualizar estudiante.");
        System.out.println("4. Eliminar estudiante.");
        System.out.println("5. Salir.");
    }
    
    private static void agregarEstudiante() {
        System.out.println("\nAGREGAR ESTUDIANTE");
        String nombre = leerCadena("Nombre: ");
        int edad = leerEntero("Edad: ");
        double promedio = leerDecimal("Promedio: ");
        
        estudiantes.add(new Estudiante(nombre, edad, promedio));
        System.out.println("Estudiante agregado exitosamente.");
    }
    
    private static void listarEstudiantes() {
        System.out.println("\nLISTA DE ESTUDIANTES");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i));
        }
    }
    
    private static void actualizarEstudiante() {
        System.out.println("\nACTUALIZAR ESTUDIANTE");
        listarEstudiantes();
        
        if (estudiantes.isEmpty()) return;
        
        int indice = leerEntero("Número del estudiante a actualizar: ") - 1;
        
        if (indice < 0 || indice >= estudiantes.size()) {
            System.out.println("Número inválido.");
            return;
        }
        
        Estudiante est = estudiantes.get(indice);
        System.out.println("Estudiante actual: " + est);
        
        String nombre = leerCadena("Nuevo nombre (Enter para mantener actual): ");
        if (!nombre.isEmpty()) est.setNombre(nombre);
        
        int edad = leerEntero("Nueva edad (0 para mantener actual): ");
        if (edad > 0) est.setEdad(edad);
        
        double promedio = leerDecimal("Nuevo promedio (-1 para mantener actual): ");
        if (promedio >= 0) est.setPromedio(promedio);
        
        System.out.println("¡Estudiante actualizado exitosamente!");
    }
    
    private static void eliminarEstudiante() {
        System.out.println("\nELIMINAR ESTUDIANTE");
        listarEstudiantes();
        
        if (estudiantes.isEmpty()) return;
        
        int indice = leerEntero("Número del estudiante a eliminar: ") - 1;
        
        if (indice < 0 || indice >= estudiantes.size()) {
            System.out.println("Número inválido.");
            return;
        }
        
        Estudiante eliminado = estudiantes.remove(indice);
        System.out.println("Estudiante eliminado: " + eliminado.getNombre());
    }
    
    // Métodos auxiliares para entrada de datos.
    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }
    
    private static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número decimal válido.");
            }
        }
    }
}