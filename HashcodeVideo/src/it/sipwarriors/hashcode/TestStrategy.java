package it.sipwarriors.hashcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestStrategy implements StrategyInterface {

  private class RequestsComparator implements Comparator<Request> {

    @Override
    public int compare(Request req1, Request req2) {
      return req2.getNumber() - req1.getNumber();
    }

  }

  @Override
  public void analyze(Simulation simulation) {
    Collections.sort(simulation.getRequestsList(), new RequestsComparator());
    for (Request request : simulation.getRequestsList()) {
      List<CacheServer> connectedServers = request.getEndpoint().getConnectedServers();
      for (CacheServer cacheServer : connectedServers) {
        if (cacheServer.contains(request.getVideo())) {
          break;
        } else if (cacheServer.canAddVideo(request.getVideo())) {
          cacheServer.addVideo(request.getVideo());
          break;
        }
      }
    }

  }

}
