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
  private List<Request> requestsList = new ArrayList<>();

  public Simulation(String params) {
    String[] par = params.split(" ");
    numVideos = Integer.parseInt(par[0]);
    numEndpoints = Integer.parseInt(par[1]);
    numRequests = Integer.parseInt(par[2]);
    numCache = Integer.parseInt(par[3]);
    cacheCapacity = Integer.parseInt(par[4]);
    for (int i = 0; i < numCache; i++) {
      cacheServersList.add(new CacheServer(cacheCapacity, i));
    }
  }

  public long start(BufferedWriter writer) throws IOException {
    long points = 0;
    StrategyInterface strategy = new TestStrategy();
    strategy.analyze(this);
    List<CacheServer> cacheUtilizzate = new ArrayList<>();
    for (CacheServer cacheServer : cacheServersList) {
      if (!cacheServer.getVideoList().isEmpty()) {
        cacheUtilizzate.add(cacheServer);
      }
    }
    writer.write(cacheUtilizzate.size() + "\n");
    for (CacheServer cacheServer : cacheUtilizzate) {
      writer.write(cacheServer.toString() + "\n");
    }
    long numeratore = 0l;
    long denominatore = 0l;
    for (Request request : requestsList) {
      numeratore += request.getMinLatency() * request.getNumber();
      denominatore += request.getNumber();
    }
    return numeratore * 1000l / denominatore;
  }

  public static void main(String[] args) throws IOException {
    if (args != null && args.length > 1) {
      long total = 0l;
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
    int videoId = 0;
    for (String dimensione : par) {
      simulation.videoList.add(new Video(videoId++, Integer.parseInt(dimensione)));
    }
    // crea endpoints
    for (int i = 0; i < simulation.numEndpoints; i++) {
      riga = in.readLine();
      par = riga.split(" ");
      Endpoint endpoint = new Endpoint(Integer.parseInt(par[0]), simulation);
      int numCacheConnected = Integer.parseInt(par[1]);
      for (int j = 0; j < numCacheConnected; j++) {
        riga = in.readLine();
        par = riga.split(" ");
        endpoint.setServerLatency(new Integer(par[0]), new Integer(par[1]));
        simulation.endpointsList.add(endpoint);
      }
    }
    // crea requests
    for (int r = 0; r < simulation.numRequests; r++) {
      riga = in.readLine();
      par = riga.split(" ");
      videoId = Integer.parseInt(par[0]);
      int endpointId = Integer.parseInt(par[1]);
      Request request = new Request(simulation.videoList.get(videoId), simulation.endpointsList.get(endpointId), Integer.parseInt(par[2]), simulation);
      simulation.requestsList.add(request);
    }
    in.close();
    return simulation;
  }

  public int getNumVideos() {
    return numVideos;
  }

  public int getNumCache() {
    return numCache;
  }

  public int getNumEndpoints() {
    return numEndpoints;
  }

  public int getNumRequests() {
    return numRequests;
  }

  public int getCacheCapacity() {
    return cacheCapacity;
  }

  public List<Video> getVideoList() {
    return videoList;
  }

  public List<CacheServer> getCacheServersList() {
    return cacheServersList;
  }

  public List<Endpoint> getEndpointsList() {
    return endpointsList;
  }

  public List<Request> getRequestsList() {
    return requestsList;
  }

}
