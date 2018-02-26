package it.sipwarriors.hashcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Giampaolo Rotini
 * @version 1.0
 * @date 12 gen 2017
 *
 */
public class Simulation {

    public Simulation(String params) {
    }

    public long start(BufferedWriter writer) throws IOException {
        long points = 0;
        return points;
    }

    public static void main(String[] args) throws IOException {
        if (args != null && args.length > 1) {
            long total = 0;
            int numFiles = args.length / 2;
            for (int i = 0; i < numFiles; i++) {
                Simulation simulation = load(args[2 * i]);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(args[2 * i + 1]));
                long points = simulation.start(writer);
                writer.flush();
                writer.close();
                System.out.println(
                        "Punteggio " + args[2 * i + 1] + ": " + points);
                total += points;
            }
            System.out.println("Punteggio totale: " + total);
        }
    }

    private static Simulation load(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        Simulation simulation = new Simulation(in.readLine());
        // TODO: carica tutto
        in.close();
        return simulation;
    }

}
