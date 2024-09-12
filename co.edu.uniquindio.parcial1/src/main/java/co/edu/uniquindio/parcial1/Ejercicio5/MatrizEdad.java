package co.edu.uniquindio.parcial1.Ejercicio5;

import java.util.ArrayList;
import java.util.List;

public class MatrizEdad {
    public static void main(String[] args) {
        Persona[][] matriz = {
                {new Persona(5), new Persona(7), new Persona(1), new Persona(3)},
                {new Persona(6), new Persona(45), new Persona(13), new Persona(89)},
                {new Persona(2), new Persona(28), new Persona(496), new Persona(8128)},
                {new Persona(11), new Persona(4), new Persona(8), new Persona(89)},
                {new Persona(31), new Persona(37), new Persona(43), new Persona(10)}
        };

        // Inicializar listas para primos y perfectos
        List<Integer> listaPrimos = new ArrayList<>();
        List<Integer> listaPerfectos = new ArrayList<>();

        // Recorrer la matriz de manera recursiva
        recorrerMatriz(matriz, matriz.length - 1, 0, listaPrimos, listaPerfectos);

        // Imprimir resultados
        System.out.println("Números Primos: " + listaPrimos);
        System.out.println("Números Perfectos: " + listaPerfectos);
    }

    // Función recursiva para recorrer la matriz de personas
    static void recorrerMatriz(Persona[][] matriz, int i, int j, List<Integer> listaPrimos, List<Integer> listaPerfectos) {
        if (i < 0) {
            return;  // Condición base, si se sale del límite superior de la matriz
        }
        if (j >= matriz[i].length) {
            recorrerMatriz(matriz, i - 1, 0, listaPrimos, listaPerfectos); // Mover a la fila anterior
            return;
        }

        int edad = matriz[i][j].getEdad();  // Obtener la edad de la persona
        if (esPrimo(edad, 2)) {
            listaPrimos.add(edad);  // Agregar a lista de primos si es primo
        }
        if (esPerfecto(edad, 1, 0)) {
            listaPerfectos.add(edad);  // Agregar a lista de perfectos si es perfecto
        }

        recorrerMatriz(matriz, i, j + 1, listaPrimos, listaPerfectos);  // Avanzar a la siguiente columna
    }

    // Función recursiva para verificar si un número es primo
    static boolean esPrimo(int n, int divisor) {
        if (n < 2) return false;
        if (divisor > Math.sqrt(n)) return true;  // Condición base, si ya pasó la raíz cuadrada
        if (n % divisor == 0) return false;  // Si encuentra un divisor, no es primo
        return esPrimo(n, divisor + 1);  // Recursión con siguiente divisor
    }

    // Función recursiva para verificar si un número es perfecto
    static boolean esPerfecto(int n, int divisor, int suma) {
        if (divisor == n) return suma == n;  // Condición base, si la suma coincide con el número
        if (n % divisor == 0) suma += divisor;  // Si es divisor, sumarlo
        return esPerfecto(n, divisor + 1, suma);  // Recursión con siguiente divisor
    }
}
