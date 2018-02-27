package it.sipwarriors.hashcode;

public class Request {

  private final Video video;
  private final Endpoint endpoint;
  private final int number;
  private final Simulation simulation;

  public Request(Video video, Endpoint endpoint, int number, Simulation simulation) {
    this.video = video;
    this.endpoint = endpoint;
    this.number = number;
    this.simulation = simulation;
  }

  public Video getVideo() {
    return video;
  }

  public Endpoint getEndpoint() {
    return endpoint;
  }

  public int getNumber() {
    return number;
  }

  public int getMinLatency() {
    int minLatency = endpoint.getDataCenterLatancy();
    for (Integer cacheId : endpoint.getCacheServersLatency().keySet()) {
      CacheServer cacheServer = simulation.getCacheServersList().get(cacheId.intValue());
      if (cacheServer.contains(video) && minLatency > endpoint.getServerLatency(cacheId).intValue()) {
        minLatency = endpoint.getServerLatency(cacheId).intValue();
      }
    }
    return minLatency;
  }

}
