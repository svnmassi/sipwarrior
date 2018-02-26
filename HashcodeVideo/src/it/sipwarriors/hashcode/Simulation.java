package it.sipwarriors.hashcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giampaolo Rotini
 * @version 1.0
 * @date 12 gen 2017
 *
 */
public class Simulation {

  private int numVideos;
  private int numCache;
  private int numEndpoints;
  private int numRequests;
  private int cacheCapacity;
  private List<Video> videoList = new ArrayList<>();
  private List<CacheServer> cacheServersList = new ArrayList<>();
  private List<Endpoint> endpointsList = new ArrayList<>();

  public Simulation(String params) {
    String[] par = params.split(" ");
    numVideos = Integer.parseInt(par[0]);
    numEndpoints = Integer.parseInt(par[1]);
    numRequests = Integer.parseInt(par[2]);
    numCache = Integer.parseInt(par[3]);
    cacheCapacity = Integer.parseInt(par[4]);
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
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[2 * i + 1]));
        long points = simulation.start(writer);
        writer.flush();
        writer.close();
        System.out.println("Punteggio " + args[2 * i + 1] + ": " + points);
        total += points;
      }
      System.out.println("Punteggio totale: " + total);
    }
  }

  private static Simulation load(String filename) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader(filename));
    Simulation simulation = new Simulation(in.readLine());
    // Crea i video
    String riga = in.readLine();
    String[] par = riga.split(" ");
    for (String dimensione : par) {
      simulation.videoList.add(new Video(Integer.parseInt(dimensione)));
    }
    for (int i = 0; i < simulation.numEndpoints; i++) {
      riga = in.readLine();
      par = riga.split(" ");
      Endpoint endpoint = new Endpoint(Integer.parseInt(par[0]));
      int numCacheConnected = Integer.parseInt(par[1]);
    }
    in.close();
    return simulation;
  }

}
