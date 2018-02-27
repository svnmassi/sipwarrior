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

  public int getId() {
    return id;
  }

  public int getRemainingCapacity() {
    return remainingCapacity;
  }

  public List<Video> getVideoList() {
    return videoList;
  }

  public boolean canAddVideo(Video video) {
    return remainingCapacity >= video.getDimension();
  }

  public boolean contains(Video video) {
    for (Video videoCor : videoList) {
      if (videoCor.getId() == video.getId()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer(id);
    for (Video video : videoList) {
      sb.append(" ").append(video.getId());
    }
    return sb.toString();
  }

}
