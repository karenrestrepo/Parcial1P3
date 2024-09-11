package co.edu.uniquindio.parcial1.Ejercicio3;

public class MatrizTranspuesta {
    public static void main(String[] args) {
        int[][] matriz = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
        };
        int[][] transpuesta = intercambiarFC(matriz);
        imprimirTranspuesta(transpuesta);
    }

    private static int[][] intercambiarFC(int[][] matriz) {
        // Crear la matriz transpuesta
        int[][] transpuesta = new int[matriz[0].length][matriz.length];
        // Llamar a la función recursiva para llenar la matriz transpuesta
        intercambiarFC(matriz, transpuesta, 0, 0);
        return transpuesta;
    }

    private static void intercambiarFC(int[][] matriz, int[][] transpuesta, int m, int n) {
        // Base case: Si hemos recorrido todas las filas, regresamos
        if (m >= matriz.length) {
            return;
        }

        // Llenar la columna correspondiente en la matriz transpuesta
        llenarColumnas(matriz, transpuesta, m, n);

        // Recursión para pasar a la siguiente columna
        if (n + 1 < matriz[0].length) {
            intercambiarFC(matriz, transpuesta, m, n + 1);
        } else {
            // Recursión para pasar a la siguiente fila
            intercambiarFC(matriz, transpuesta, m + 1, 0);
        }
    }

    private static void llenarColumnas(int[][] matriz, int[][] transpuesta, int m, int n) {
        // Base case: Si hemos recorrido todas las filas de una columna, regresamos
        if (n >= matriz[0].length) {
            return;
        }

        // Asignar el valor de la matriz original a la transpuesta
        transpuesta[n][m] = matriz[m][n];

        // Recursión para llenar la siguiente fila de la columna
        llenarColumnas(matriz, transpuesta, m, n + 1);
    }

    private static void imprimirTranspuesta(int[][] transpuesta) {
        // Iniciar la impresión de la matriz desde la primera fila y columna
        imprimirFila(transpuesta, 0);
    }

    private static void imprimirFila(int[][] transpuesta, int filaIndex) {
        // Caso base: Si hemos recorrido todas las filas, terminamos
        if (filaIndex >= transpuesta.length) {
            return;
        }

        // Imprimir cada valor en la fila actual
        imprimirValor(transpuesta, filaIndex, 0);

        // Pasar a la siguiente fila
        System.out.println();
        imprimirFila(transpuesta, filaIndex + 1);
    }

    private static void imprimirValor(int[][] transpuesta, int filaIndex, int colIndex) {
        // Caso base: Si hemos recorrido todas las columnas, regresamos
        if (colIndex >= transpuesta[filaIndex].length) {
            return;
        }

        // Imprimir el valor actual
        System.out.print(transpuesta[filaIndex][colIndex] + " ");

        // Imprimir el siguiente valor en la misma fila
        imprimirValor(transpuesta, filaIndex, colIndex + 1);
    }


}
