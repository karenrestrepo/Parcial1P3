package co.edu.uniquindio.parcial1.EjercicioL;
import java.util.ArrayList;
import java.util.List;

public class Laberinto1 {
    public char[][] laberinto = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', ' ', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
    };

    private List<String> soluciones = new ArrayList<>();

    public static void main(String[] args) {
        Laberinto1 m = new Laberinto1();
        m.laberinto[1][1] = 'X'; // salida
        m.encuentraSoluciones(8, 1); // entrada
        m.imprimirSoluciones(0);
    }

    public void encuentraSoluciones(int x, int y) {
        char[][] copiaLaberinto = copiarLaberinto(laberinto, 0, 0);
        copiaLaberinto[x][y] = 'S';
        paso(x, y, copiaLaberinto);
    }

    private boolean paso(int x, int y, char[][] laberintoActual) {
        if (laberintoActual[x][y] == 'X') {
            soluciones.add(imprimirLaberinto(laberintoActual, 0, 0));
            return true;
        } else if (laberintoActual[x][y] == '#' || laberintoActual[x][y] == '*' || laberintoActual[x][y] == 'f') {
            return false;
        } else {
            char original = laberintoActual[x][y];
            if (original != 'S') {
                laberintoActual[x][y] = '*';
            }

            boolean exito = pasoRecursivo(x, y, laberintoActual, 0);

            if (!exito && original != 'S' && original != 'X') {
                laberintoActual[x][y] = 'f';
            }

            return exito;
        }
    }

    private boolean pasoRecursivo(int x, int y, char[][] laberintoActual, int direccion) {
        if (direccion > 3) {
            return false;
        } else {
            int newX = x;
            int newY = y;
            if (direccion == 0) newY++;      // DERECHA
            else if (direccion == 1) newX--; // ARRIBA
            else if (direccion == 2) newY--; // IZQUIERDA
            else if (direccion == 3) newX++; // ABAJO

            boolean exitoActual = paso(newX, newY, copiarLaberinto(laberintoActual, 0, 0));
            boolean exitoSiguiente = pasoRecursivo(x, y, laberintoActual, direccion + 1);

            return exitoActual || exitoSiguiente;
        }
    }

    private char[][] copiarLaberinto(char[][] original, int x, int y) {
        if (x >= original.length) {
            return new char[original.length][];
        } else if (y >= original[x].length) {
            char[][] parcial = copiarLaberinto(original, x + 1, 0);
            parcial[x] = new char[original[x].length];
            return parcial;
        } else {
            char[][] parcial = copiarLaberinto(original, x, y + 1);
            parcial[x][y] = original[x][y];
            return parcial;
        }
    }

    private String imprimirLaberinto(char[][] lab, int x, int y) {
        if (x >= lab.length) {
            return "";
        } else if (y >= lab[x].length) {
            return "\n" + imprimirLaberinto(lab, x + 1, 0);
        } else {
            return lab[x][y] + " " + imprimirLaberinto(lab, x, y + 1);
        }
    }

    private void imprimirSoluciones(int index) {
        if (index >= soluciones.size()) {
            System.out.println("Número de soluciones encontradas: " + soluciones.size());
        } else {
            System.out.println("Solución " + (index + 1) + ":");
            System.out.println(soluciones.get(index));
            imprimirSoluciones(index + 1);
        }
    }
}