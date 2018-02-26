package it.sipwarriors.hashcode;

import java.util.ArrayList;
import java.util.List;

public class CacheServer {

  private int id;
  private int remainingCapacity;
  private List<Video> videoList = new ArrayList<>();

  public CacheServer(int capacity, int id) {
    this.remainingCapacity = capacity;
    this.id = id;
  }

  public void addVideo(Video video) {
    videoList.add(video);
    remainingCapacity -= video.getDimension();
  }

}
