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

    // Movimiento: derecha, abajo, arriba, izquierda
    static int[] dirFila = {0, 1, -1, 0};
    static int[] dirColumna = {1, 0, 0, -1};

    public static void main(String[] args) {
        // Posición de la entrada (11, 0)
        int[] entrada = {11, 0};
        estacionarCarro(entrada, 1);
    }

    static void estacionarCarro(int[] entrada, int carro) {
        if (carro > 2) return;

        System.out.println("Intentando parquear el carro " + carro + "...");
        if (moverCarro(entrada[0], entrada[1])) {
            System.out.println("Carro " + carro + " parqueado.");
            imprimirParqueadero();
            limpiarRuta();
        } else {
            System.out.println("No se pudo parquear el carro " + carro);
            return;
        }

        estacionarCarro(entrada, carro + 1);
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
        parqueadero[filaActual][colActual] = "+"; // Marcar la ruta

        // Intentar mover en las cuatro direcciones sin el ciclo for
        if (moverCarro(filaActual + dirFila[0], colActual + dirColumna[0]) ||
                moverCarro(filaActual + dirFila[1], colActual + dirColumna[1]) ||
                moverCarro(filaActual + dirFila[2], colActual + dirColumna[2]) ||
                moverCarro(filaActual + dirFila[3], colActual + dirColumna[3])) {
            return true;
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
        limpiarRecursivo(0, 0);
    }

    static void limpiarRecursivo(int fila, int columna) {
        if (fila >= FILAS) {
            return;
        }
        if (columna >= COLUMNAS) {
            limpiarRecursivo(fila + 1, 0); // Cambiar de fila
            return;
        }

        if (parqueadero[fila][columna].equals("+")) {
            parqueadero[fila][columna] = ""; // Limpiar la ruta recorrida
        }
        visitado[fila][columna] = false; // Reiniciar el estado de las celdas visitadas

        limpiarRecursivo(fila, columna + 1); // Pasar a la siguiente columna
    }

    // Imprimir el estado actual del parqueadero
    static void imprimirParqueadero() {
        imprimirRecursivo(0, 0);
    }

    static void imprimirRecursivo(int fila, int columna) {
        if (fila >= FILAS) {
            return;
        }
        if (columna >= COLUMNAS) {
            System.out.println();
            imprimirRecursivo(fila + 1, 0); // Cambiar de fila
            return;
        }

        System.out.print((parqueadero[fila][columna].isEmpty() ? " " : parqueadero[fila][columna]) + " ");
        imprimirRecursivo(fila, columna + 1); // Pasar a la siguiente columna
    }
}