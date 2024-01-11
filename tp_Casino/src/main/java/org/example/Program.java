package org.example;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Program {
    public static void main(String[] args) {
        ColumnsHandler columnsHandler = new ColumnsHandler();
        List<List<String>> columns = columnsHandler.deserializeColumns();
        Machine machine = new Machine(columns);
        int jetonsPossedes = 20; //On initialise le nombre de jetons de départ
        int partiejoue = 0;
        int partiegagne =0;
        int totaljetonjoue =0;
        int jetonpossedes2 =0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue au Casino de Céladopole !");
        boolean game = true;
        while (game) {
            System.out.println("\nJetons possédés : " + jetonsPossedes);
            System.out.print("Combien de jetons ? (1, 2 ou 3) (Appuyez sur 4 pour arrêter le jeu): ");
            int valeurChoisi = verifValue(scanner);

            if(valeurChoisi == 4)
            {
                System.out.println("Au revoir");
                break;
            }
            machine.actualisationMatrice();
            machine.Affiche();

            int gains = machine.verifWin(valeurChoisi);
            System.out.println("\nGains : " + gains);
            jetonpossedes2 =jetonsPossedes;
            jetonsPossedes += gains-valeurChoisi;

            game = machine.verifGain(jetonsPossedes);

            if(jetonsPossedes>jetonpossedes2)
            {
                totaljetonjoue+=1;
            }

            totaljetonjoue +=valeurChoisi;
            partiejoue +=1;
        }

        Stats stats = new Stats();

        stats.setJeton(jetonsPossedes);
        stats.setJetonDepense(totaljetonjoue);
        stats.setPartieGagne(partiegagne);
        stats.setPartieJoue(partiejoue);
        
        //Calcule la proba pour chaque lettre en divisant part le nombre de partie 
        for (Map.Entry<String, Double> entry : machine.getProbaGains().entrySet()) {
            entry.setValue(entry.getValue() / partiejoue);
        }

        stats.setProbaGains(machine.getProbaGains());
        
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("C:\\Users\\ouadi\\OneDrive\\Bureau\\tp_Casino\\src\\main\\java\\org\\example\\stats.json")) {
            gson.toJson(stats, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int verifValue(Scanner scanner) {
        while (true) {
            String valeur = scanner.nextLine();
            if (valeur.equals("1") || valeur.equals("2") || valeur.equals("3") || valeur.equals("4")) {
                return Integer.parseInt(valeur);
            } else {
                System.out.println("Veuillez saisir 1, 2 , 3 (Appuyez sur 4 pour arrêter le jeu) ");
            }
        }
    }
}
