package co.edu.uniquindio.parcial1.Ejercicio4;

public class Parqueadero {
    static final int FILAS = 12;
    static final int COLUMNAS = 7;

    static String[][] parqueadero = {
            {"L", "", "L", "D", "R1", "L", ""},
            {"L", "", "L", "O", "", "L", ""},
            {"", "", "", "O", "", "", ""},
            {"O", "", "", "O", "D", "D", ""},
            {"O", "", "", "", "", "L", ""},
            {"O", "", "O", "", "", "L", ""},
            {"O", "", "O", "", "O", "O", ""},
            {"", "", "O", "", "", "", ""},
            {"O", "", "O", "", "O", "", "O"},
            {"O", "", "R", "", "O", "", "R"},
            {"", "", "R", "", "O", "O", "R"},
            {"", "", "R", "", "", "", "R2"}
    };

    static boolean[][] visitado = new boolean[FILAS][COLUMNAS];

    // Movimiento: derecha, izquierda, abajo, arriba
    static int[] dirFila = {0, 0, 1, -1};
    static int[] dirColumna = {1, -1, 0, 0};

    public static void main(String[] args) {
        // Posición de la entrada (11, 0)
        int[] entrada = {11, 0};

        for (int carro = 1; carro <= 2; carro++) {
            System.out.println("Intentando parquear el carro " + carro + "...");
            if (moverCarro(entrada[0], entrada[1])) {
                System.out.println("Carro " + carro + " parqueado.");
                imprimirParqueadero();
                limpiarRuta();
            } else {
                System.out.println("No se pudo parquear el carro " + carro);
                break;
            }
        }
    }

    // Backtracking para mover el carro y parquearlo en R1 o R2
    static boolean moverCarro(int filaActual, int colActual) {
        // Verificar si estamos fuera de los límites
        if (filaActual < 0 || colActual < 0 || filaActual >= FILAS || colActual >= COLUMNAS) {
            return false;
        }

        // Verificar si hemos llegado a R1 o R2
        if (esEspacioReservado(filaActual, colActual)) {
            parqueadero[filaActual][colActual] = "E"; // Marcar espacio como estacionado 'E'
            return true;
        }

        // Verificar si la celda actual es válida
        if (!esValido(filaActual, colActual)) {
            return false;
        }

        // Marcar como visitado y registrar la ruta
        visitado[filaActual][colActual] = true;
        parqueadero[filaActual][colActual] = "-"; // Marcar la ruta

        // Intentar mover en las cuatro direcciones
        for (int i = 0; i < 4; i++) {
            int nuevaFila = filaActual + dirFila[i];
            int nuevaColumna = colActual + dirColumna[i];

            if (moverCarro(nuevaFila, nuevaColumna)) {
                return true;
            }
        }

        // Desmarcar si no se encuentra una ruta válida
        visitado[filaActual][colActual] = false;
        parqueadero[filaActual][colActual] = ""; // Limpiar el camino si no fue exitoso
        return false;
    }

    // Verificar si la celda es válida para moverse
    static boolean esValido(int fila, int columna) {
        // Verificar los límites y que no esté visitado
        if (fila < 0 || columna < 0 || fila >= FILAS || columna >= COLUMNAS || visitado[fila][columna]) {
            return false;
        }

        // Espacios válidos para moverse: vacíos "" o espacios reservados "R1" o "R2"
        return parqueadero[fila][columna].equals("") || parqueadero[fila][columna].equals("R1") || parqueadero[fila][columna].equals("R2");
    }

    // Verificar si la celda es un espacio reservado (R1 o R2)
    static boolean esEspacioReservado(int fila, int columna) {
        // Agregar verificación de límites
        if (fila < 0 || columna < 0 || fila >= FILAS || columna >= COLUMNAS) {
            return false;
        }
        return parqueadero[fila][columna].equals("R1") || parqueadero[fila][columna].equals("R2");
    }

    // Limpiar la ruta después de parquear el carro
    static void limpiarRuta() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (parqueadero[i][j].equals("-")) {
                    parqueadero[i][j] = ""; // Limpiar la ruta recorrida
                }
                visitado[i][j] = false; // Reiniciar el estado de las celdas visitadas
            }
        }
    }

    // Imprimir el estado actual del parqueadero
    static void imprimirParqueadero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print((parqueadero[i][j].isEmpty() ? " " : parqueadero[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}