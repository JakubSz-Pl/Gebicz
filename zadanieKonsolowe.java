
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void wypelnijTablice(int[] tablica, int n) {
        Random losowanie = new Random();

        for (int i = 0; i < n; i++) {
            tablica[i] = losowanie.nextInt(100) + 1;
        }
    }

    public static int wyszukajZWartownikiem(int[] tablica, int n, int szukana) {
        tablica[n] = szukana;

        int i = 0;

        while (tablica[i] != szukana) {
            i++;
        }

        if (i == n) {
            return -1;
        }

        return i;
    }

    public static void wyswietlTablice(int[] tablica, int n) {
        System.out.println("Zawartosc tablicy:");

        for (int i = 0; i < n; i++) {
            System.out.print(tablica[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int N = 50;
        int[] tablica = new int[N + 1];

        wypelnijTablice(tablica, N);

        System.out.print("Podaj liczbe calkowita do wyszukania: ");
        int szukana = scanner.nextInt();

        wyswietlTablice(tablica, N);

        int indeks = wyszukajZWartownikiem(tablica, N, szukana);

        if (indeks != -1) {
            System.out.println("Znaleziono liczbe " + szukana);
            System.out.println("Indeks pierwszego wystapienia: " + indeks);
        } else {
            System.out.println("Nie znaleziono liczby " + szukana + " w tablicy.");
        }

        scanner.close();
    }
}
