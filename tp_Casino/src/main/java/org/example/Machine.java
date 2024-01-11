package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class Machine {
    private String[][] matrice;
    private List<List<String>> columns;
    private Map<String, Integer> gains = Map.of(
            "7", 300,
            "BAR", 100,
            "R", 15,
            "P", 15,
            "T", 15,
            "C", 8
    );

    private Map<String, Double> probaGains;
    public Map<String, Double> getProbaGains() {
        return probaGains;
    }



    public Machine(List<List<String>> columns) {


        this.columns = columns;
        this.matrice = new String[3][3];
        actualisationMatrice();

        this.probaGains = new HashMap<>();
        this.probaGains.put("7", 0.0);
        this.probaGains.put("BAR", 0.0);
        this.probaGains.put("R", 0.0);
        this.probaGains.put("P", 0.0);
        this.probaGains.put("T", 0.0);
        this.probaGains.put("C", 0.0);
    }

    public void actualisationMatrice() {
        Random random = new Random();

        for (int i = 0; i < columns.size(); i++) {
            List<String> column = columns.get(i);
            int randomIndex = random.nextInt(column.size());

            for (int j = 0; j < 3; j++) {
                int currentIndex = (randomIndex + j) % column.size();
                matrice[j][i] = column.get(currentIndex);
            }
        }
    }


    public void Affiche() {
        for (String[] row : matrice) {
            for (String symbol : row) {
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    public int verifWin(int jeton) {
        int totalGains = 0;

        if (jeton == 1) {
            // Vérifie la ligne du milieu horizontale
            if (matrice[1][0].equals(matrice[1][1]) && matrice[1][1].equals(matrice[1][2])) {
                totalGains += calculGain(matrice[1][1]);
            }
        }

        if (jeton == 2) {
            // Vérifie les lignes horizontales
            for (int i = 0; i < matrice.length; i++) {
                if (matrice[i][0].equals(matrice[i][1]) && matrice[i][1].equals(matrice[i][2])) {
                    totalGains += calculGain(matrice[i][0]);
                }
            }
        }

        if (jeton == 3) {

            for (int i = 0; i < matrice.length; i++) {
                String lettre1 = matrice[i][0];
                String lettre2 = matrice[i][1];
                String lettre3 = matrice[i][2];

                // Vérifie les lignes horizontales
                if (lettre1.equals(lettre2) && lettre2.equals(lettre3)) {
                    totalGains += calculGain(lettre1);
                }

                // Vérifie les diagonales
                if ((i == 0 && lettre1.equals(matrice[1][1]) && lettre1.equals(matrice[2][2])) ||
                        (i == 2 && lettre1.equals(matrice[1][1]) && lettre1.equals(matrice[0][2]))) {
                    totalGains += calculGain(lettre1);
                }
            }
        }

        return totalGains;
    }

    private int calculGain(String symbol) {
        Double valeur = this.probaGains.get(symbol);
        this.probaGains.put(symbol, valeur + 1.0);
        return gains.getOrDefault(symbol, 0);
    }

    public boolean verifGain(int gain){

        if(gain<=0){
            System.out.println("Tu ne peux plus jouer tu as plus de jetons");
            return false;
        }
        return true;

    }

}
