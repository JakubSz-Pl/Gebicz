def wczytaj_dane():
    try:
        plik = open("uczniowie.txt", "r")
        dane = plik.readlines()
        plik.close()

        for i in range(len(dane)):
            dane[i] = dane[i].strip()

        return dane

    except FileNotFoundError:
        print("Brak pliku, stworzono nowa liste")
        return []


def wyswietl_dane(dane):
    print("\nLista uczniów:")

    if len(dane) == 0:
        print("Lista jest pusta")
    else:
        for i in range(len(dane)):
            print(str(i + 1) + ". " + dane[i])


def sortuj_dane(dane):
    dane.lower()
    dane.sort()

    plik = open("uczniowie.txt", "w")

    for element in dane:    
        plik.write(element + "\n")

    plik.close()

    print("\nLista posortowana")
    wyswietl_dane(dane)


def usun_dane(dane):
    if len(dane) == 0:
        print("\nLista jest pusta")
        return

    wyswietl_dane(dane)

    try:
        numer = int(input("\nPodaj numer ucznia do usunięcia: "))

        if numer >= 1 and numer <= len(dane):
            usuniety = dane.pop(numer - 1)
            print("Usunięto ucznia:", usuniety)

            plik = open("uczniowie.txt", "w", encoding="utf-8")

            for element in dane:
                plik.write(element + "\n")

            plik.close()

        else:
            print("Nieprawidłowy numer ucznia")

    except ValueError:
        print("Podaj poprawny numer")


def main():
    dane = wczytaj_dane()

    while True:
        print("\n--- MENU ---")
        print("1. Wyświetl listę uczniów")
        print("2. Posortuj listę alfabetycznie")
        print("3. Usuń ucznia z listy")
        print("4. Zakończ program")

        wybor = input("Wybierz opcję: ")

        if wybor == "1":
            wyswietl_dane(dane)

        elif wybor == "2":
            sortuj_dane(dane)

        elif wybor == "3":
            usun_dane(dane)

        elif wybor == "4":
            print("Koniec programu")
            break

        else:
            print("Nieprawidłowa opcja. Spróbuj ponownie")


main()