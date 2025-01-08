package exercitiul4;

import java.util.ArrayList;
import java.util.Scanner;

class Per {
    String nume;
    String cnp;

    Per(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getVarsta() {
        int PrefixAN = (cnp.charAt(0) == '1' || cnp.charAt(0) == '2') ? 1900 : 2000;
        int AnNASTERE = PrefixAN + Integer.parseInt(cnp.substring(1, 3));
        int AnACTUAL = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return String.valueOf(AnACTUAL - AnNASTERE);
    }
}

public class Persoana {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Numar de persoane:");
        int n = sc.nextInt();

        String nume, cnp = "0";
        ArrayList<Per> lista = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("Introduceti numele persoanei " + i + ":");
            nume = sc.next();
            int c = 0;

            while (c != 1) {
                System.out.println("Introduceti CNP-ul pentru " + nume + ":");
                cnp = sc.next();

                if (cnp.length() != 13) {
                    System.out.println("CNP-ul trebuie sa aiba 13 caractere.");
                } else if (!cnp.matches("\\d+")) {
                    System.out.println("CNP-ul trebuie sa contina doar cifre.");
                } else if (cnp.charAt(0) == '1' || cnp.charAt(0) == '2' || cnp.charAt(0) == '5' || cnp.charAt(0) == '6') {
                    int suma = 0;
                    int[] coeficienti = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};

                    for (int j = 0; j < 12; j++) {
                        suma += Character.getNumericValue(cnp.charAt(j)) * coeficienti[j];
                    }

                    int cifraControl = suma % 11;
                    if (cifraControl == 10) cifraControl = 1;

                    if (cifraControl == Character.getNumericValue(cnp.charAt(12))) {
                        c = 1; // CNP valid
                    } else {
                        System.out.println("CNP-ul nu este valid (cifra de control este gresita).");
                    }
                } else {
                    System.out.println("CNP-ul trebuie sa inceapa cu 1, 2, 5 sau 6.");
                }
            }

            Per om = new Per(nume, cnp);
            lista.add(om);
        }

        System.out.println("\nLista persoanelor:");
        for (Per i : lista) {
            System.out.println("Nume: " + i.getNume() + " | CNP: " + i.getCnp() + " | Varsta: " + i.getVarsta());
        }

        sc.close();
    }
}
