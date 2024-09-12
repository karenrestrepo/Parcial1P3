package co.edu.uniquindio.parcial1.EjercicioL;

public class Laberinto {

    public char[][] laberinto={
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

    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args) {
        Laberinto m = new Laberinto(); 												// construimos un objeto de la clase Laberinto por defecto
        m.laberinto[1][1] = 'X'; 													// introducimos en este caso, la salida (X) en las coordenadas (1,1)
        m.resuelve(8, 1); 															// ahora, introducimos la entrada (S) en las coordenadas (8,1) y llamamos al algoritmo
    }

    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public void resuelve(int x, int y) { 				// permite introducir unas coordenadas (x, y)
        paso(x, y); 									// intentará resolver el laberinto en estas coordenadas
    }

    private boolean paso(int x, int y) {
        if (laberinto[x][y] == 'X') { // si hemos llegado a X, es una solución
            System.out.println("Solución encontrada:\n" + imprimirLaberinto()); // imprime el estado actual del laberinto
            return false; // continúa buscando más soluciones
        }

        if (laberinto[x][y] == '#' || laberinto[x][y] == '*' || laberinto[x][y] == 'f') { // si llegamos a una pared o al mismo punto,
            return false; // entonces el laberinto no puede resolverse desde aquí
        }

        laberinto[x][y] = '*'; // marcamos la posición como visitada

        paso(x, y + 1); // intentamos ir hacia la DERECHA
        paso(x - 1, y); // intentamos ir hacia ARRIBA
        paso(x, y - 1); // intentamos ir hacia la IZQUIERDA
        paso(x + 1, y); // intentamos ir hacia ABAJO

        laberinto[x][y] = 'f'; // marcamos la posición como fallida si no es una solución válida

        return false; // seguimos explorando otros caminos
    }

    private String imprimirLaberinto() { // método para generar la cadena del laberinto actual
        String salida = "";
        for (int x = 0; x < laberinto.length; x++) {
            for (int y = 0; y < laberinto[x].length; y++) {
                salida += laberinto[x][y] + " ";
            }
            salida += "\n";
        }
        return salida;
    }
}
