import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static final int CERO = 0;

    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;

    public static Random rng = new Random();
    public static int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner respuesta = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(respuesta);
            ejecutarOpcion(opcion, respuesta);
        } while (opcion != 3);
        respuesta.close();
    }

    public static void mostrarMenu() {
        System.out.println("Bienvenido a Casino Black Cat");
        System.out.println("1. Iniciar ronda de ruleta");
        System.out.println("2. Ver estadisticas");
        System.out.println("3. Salir");
    }

    public static int leerOpcion(Scanner in) {
        System.out.print("Escoja el número correspondiente a la opción deseada: ");
        return in.nextInt();
    }

    public static void ejecutarOpcion(int respuesta, Scanner in) {
        switch (respuesta) {
            case 1 -> iniciarRonda(in);
            case 2 -> mostrarEstadisticas();
            case 3 -> System.out.println("Gracias por jugar.");
            default -> System.out.println("La respuesta no es valida.");
        }
    }

    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);
        System.out.print("Ingrese el monto a apostar: ");
        int monto = in.nextInt();

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        System.out.println("Opciones de apuesta:");
        System.out.println("R. Rojo");
        System.out.println("N. Negro");
        System.out.println("P. Par");
        System.out.println("I. Impar");
        System.out.println("V. Verde (0)");
        System.out.print("Escoja su apuesta: ");
        return Character.toUpperCase(in.next().charAt(0));
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        return switch (tipo) {
            case 'R' -> esRojo(numero);
            case 'N' -> numero != CERO && !esRojo(numero);
            case 'P' -> numero != CERO && numero % 2 == 0;
            case 'I' -> numero != CERO && numero % 2 == 1;
            case 'V' -> numero == CERO;
            default -> false;
        };
    }

    public static boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (n == rojo) return true;
        }
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("La ruleta giró y salio: " + numero);
        if (acierto) {
            System.out.println("Felicidades Ganaste: " + monto*2);
        } else {
            System.out.println("Lo siento perdiste");
        }
    }

    public static void mostrarEstadisticas() {
        int totalJugado = 0, totalAciertos = 0, totalGanado = 0;
        for (int i = 0; i < historialSize; i++) {
            totalJugado += historialApuestas[i];
            if (historialAciertos[i]) {
                totalAciertos++;
                totalGanado += historialApuestas[i]; // sin multiplicar
            }
        }

        System.out.println("Estadisitcas de juego: ");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Total apostado: " + totalJugado);
        System.out.println("Total aciertos: " + totalAciertos);
        if (historialSize > 0) {
            double porcentaje = (totalAciertos * 100.0) / historialSize;
            System.out.printf("Porcentaje de acierto: %.2f%%\n", porcentaje);
        }
        int neto = totalGanado - totalJugado;
        System.out.println("Ganancia/Perdida neta: " + neto);
    }
}
