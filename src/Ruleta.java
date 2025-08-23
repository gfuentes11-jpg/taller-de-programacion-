import java.util.Random;
import java.util.Scanner;
public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
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
        mostrarMenu();
        Scanner respuesta = new Scanner(System.in);
        int opcion = leerOpcion(respuesta);
        ejecutarOpcion(opcion, respuesta);
        leerTipoApuesta(respuesta);
    }



    public static void mostrarMenu() {
        System.out.println("Bienvenido a Casino Black Cat");
        System.out.println("Desea apostar en el juego de la ruleta");
        System.out.println("1. si.");
        System.out.println("2. no.");



    }

    public static int leerOpcion(Scanner in) {
        System.out.println("Escoja el numero correspondientes a la opcion deseada: ");
        int respuesta = in.nextInt();

        return respuesta;


    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1 -> iniciarRonda(in);
            case 2 -> System.out.println("Gracias por jugar.");
        }
    }


    public static void iniciarRonda(Scanner in) {
        System.out.println("Opciones para apostar");
        System.out.println("1. Rojo");
        System.out.println("2. negro");
        System.out.println("3. par");
        System.out.println("4. impar");
        System.out.println("5. cero/verde");
    }

    public static char leerTipoApuesta(Scanner in) {
        System.out.println("escoga su apuesta segun el numero correspondiente: ");
        char respuesta = in.next().charAt(0);;
        return respuesta;
    }

    public static int girarRuleta() {
        return 0;
    }
    public static boolean evaluarResultado(int numero, char tipo) {
        return false;
    }
    public static boolean esRojo(int n) {
        return false;
    }
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {}

    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {}

    public static void mostrarEstadisticas() {}
}

