package it.sipwarriors.hashcode;

import java.util.HashMap;
import java.util.Map;

public class Endpoint {

  private int dataCenterLatancy;
  private Map<Integer, Integer> cacheServersLatency = new HashMap<>();

  public Endpoint(int dataCenterLatancy) {
    this.dataCenterLatancy = dataCenterLatancy;
  }

  public void setServerLatency(Integer id, Integer latency) {
    cacheServersLatency.put(id, latency);
  }

}
