package co.edu.uniquindio.parcial1.Ejercicio2;

import java.util.ArrayList;

public class MatrizPalabras {
    public static void main(String[] args) {
        // Matriz de prueba
        String[][] matriz = {
                {"vacio", "carro", "baul", "perro"},
                {"colombia", "casa", "moto", "caza"},
                {"llanta", "reir", "archivo", "silla"},
                {"cocina", "ola", "ave", "freir"}
        };

        // Lista donde se almacenarán las palabras con vocales seguidas
        ArrayList<String> listaPalabras = new ArrayList<>();

        // Llamada inicial a la función recursiva para recorrer la matriz
        recorrerMatriz(matriz, 0, 0, listaPalabras);

        // Imprimir palabras que tienen dos vocales seguidas
        System.out.println("Palabras con dos vocales seguidas: " + listaPalabras);
    }

    // Función recursiva para recorrer la matriz
    public static void recorrerMatriz(String[][] matriz, int i, int j, ArrayList<String> listaPalabras) {
        // Caso base: Si hemos recorrido todas las filas
        if (i >= matriz.length) {
            return;
        }

        // Caso base: Si hemos recorrido todas las columnas, pasamos a la siguiente fila
        if (j >= matriz[i].length) {
            recorrerMatriz(matriz, i + 1, 0, listaPalabras); // Reiniciamos la columna y pasamos a la siguiente fila
            return;
        }

        // Verificamos si la palabra tiene dos vocales seguidas
        if (verificarVocalesSeguidas(matriz[i][j], 0)) {
            listaPalabras.add(matriz[i][j]); // Añadimos la palabra a la lista si cumple la condición
        }

        // Llamada recursiva a la siguiente columna
        recorrerMatriz(matriz, i, j + 1, listaPalabras);
    }

    // Función recursiva para verificar si una palabra tiene dos vocales seguidas
    public static boolean verificarVocalesSeguidas(String palabra, int index) {
        // Definir las vocales
        String vocales = "aeiou";

        // Caso base: Si llegamos al final de la palabra
        if (index >= palabra.length() - 1) {
            return false; // No se encontraron dos vocales seguidas
        }

        // Verificar si las letras en las posiciones 'index' y 'index + 1' son vocales
        if (vocales.contains(Character.toString(palabra.charAt(index))) &&
                vocales.contains(Character.toString(palabra.charAt(index + 1)))) {
            return true; // Se encontraron dos vocales seguidas
        }

        // Llamada recursiva para verificar las siguientes letras
        return verificarVocalesSeguidas(palabra, index + 1);
    }
}
