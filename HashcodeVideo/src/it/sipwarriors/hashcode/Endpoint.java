package it.sipwarriors.hashcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Endpoint {

  private final int dataCenterLatancy;
  private Map<Integer, Integer> cacheServersLatency = new HashMap<>();
  private final Simulation simulation;
  private List<CacheServer> serverList = new ArrayList<>();
  private final ServersComparator comparator = new ServersComparator();

  private class ServersComparator implements Comparator<CacheServer> {

    @Override
    public int compare(CacheServer server1, CacheServer server2) {
      return getServerLatency(server1.getId()) - getServerLatency(server2.getId());
    }

  }

  public Map<Integer, Integer> getCacheServersLatency() {
    return cacheServersLatency;
  }

  public Endpoint(int dataCenterLatancy, Simulation simulation) {
    this.dataCenterLatancy = dataCenterLatancy;
    this.simulation = simulation;
  }

  public int getDataCenterLatancy() {
    return dataCenterLatancy;
  }

  public void setServerLatency(Integer id, Integer latency) {
    cacheServersLatency.put(id, latency);
    serverList.add(simulation.getCacheServersList().get(id.intValue()));
    Collections.sort(serverList, comparator);
  }

  public Integer getServerLatency(Integer cacheServerId) {
    return cacheServersLatency.get(cacheServerId);
  }

  public List<CacheServer> getConnectedServers() {
    return serverList;
  }

}
