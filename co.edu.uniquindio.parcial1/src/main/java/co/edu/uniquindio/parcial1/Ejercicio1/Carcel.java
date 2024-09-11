package co.edu.uniquindio.parcial1.Ejercicio1;

public class Carcel {
    public static void main(String[] args) {
        // Definimos la cárcel
        char[][] carcel = {
                {' ', 'P', 'X', ' ', 'P', 'P', ' ', 'P'},
                {' ', 'P', 'P', ' ', 'P', 'P', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', 'P', ' ', 'P', 'P', ' '},
                {'P', ' ', 'X', 'P', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G'},
                {'P', 'X', 'P', ' ', 'P', 'P', ' ', 'P'}
        };

        // Array de direcciones para moverse: derecha, abajo, izquierda, arriba
        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Creamos una matriz de visitado
        boolean[][] visitado = new boolean[carcel.length][carcel[0].length];

        // Contador de presos encontrados en el recorrido
        int totalPresosEncontrados = contarPresos(0, 0, carcel, visitado, direcciones);

        // Imprimir el resultado final
        imprimirResultado(totalPresosEncontrados);
    }

    // Función para verificar si una posición es válida
    public static boolean esValida(int i, int j, char[][] carcel, boolean[][] visitado) {
        return (i >= 0 && i < carcel.length && j >= 0 && j < carcel[0].length && carcel[i][j] != 'X' && !visitado[i][j]);
    }

    // Función recursiva para moverse en las direcciones y contar presos
    public static int moverEnDireccion(int i, int j, int direccionIndex, char[][] carcel, boolean[][] visitado, int[][] direcciones) {
        // Si ya hemos intentado todas las direcciones, retornamos 0 (no hay más caminos).
        if (direccionIndex == direcciones.length) {
            return 0;
        }

        // Calculamos la nueva posición en base a la dirección actual.
        int nuevaI = i + direcciones[direccionIndex][0];
        int nuevaJ = j + direcciones[direccionIndex][1];
        int presos = 0;

        // Verificamos si la nueva posición es válida (dentro de los límites y no visitada).
        if (esValida(nuevaI, nuevaJ, carcel, visitado)) {
            visitado[nuevaI][nuevaJ] = true; // Marcamos como visitado
            // Contamos los presos encontrados en la nueva posición y continuamos recursivamente.
            presos = contarPresos(nuevaI, nuevaJ, carcel, visitado, direcciones);
        }

        // Llamamos recursivamente para la siguiente dirección, sumando los presos encontrados.
        return presos + moverEnDireccion(i, j, direccionIndex + 1, carcel, visitado, direcciones);
    }

    // Función recursiva para el backtracking
    public static int contarPresos(int i, int j, char[][] carcel, boolean[][] visitado, int[][] direcciones) {
        // Si llegamos al punto de control (5, 7), paramos el recuento.
        if (i == 5 && j == 7) {
            return 0; // Llegamos al punto de control, termina el recuento
        }

        visitado[i][j] = true; // Marcamos la celda actual como visitada
        int presosEncontrados = carcel[i][j] == 'P' ? 1 : 0; // Contamos preso si existe

        // Iniciar el movimiento en la primera dirección
        presosEncontrados += moverEnDireccion(i, j, 0, carcel, visitado, direcciones);

        return presosEncontrados;
    }

    private static void imprimirResultado(int totalPresosEncontrados) {
        // Verificamos si se escaparon presos
        int totalPresosEnCarcel = 26; // Se espera que haya 26 presos en total
        if (totalPresosEncontrados < totalPresosEnCarcel) {
            int presosEscapados = totalPresosEnCarcel - totalPresosEncontrados;
            System.out.println("Se escaparon " + presosEscapados + " presos.");
        } else if (totalPresosEncontrados == totalPresosEnCarcel) {
            System.out.println("No se ha escapado ningún preso.");
        }
    }
}
