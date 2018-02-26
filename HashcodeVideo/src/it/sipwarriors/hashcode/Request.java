package it.sipwarriors.hashcode;

public class Request {

  private final Video video;
  private final Endpoint endpoint;
  private final int number;

  public Request(Video video, Endpoint endpoint, int number) {
    super();
    this.video = video;
    this.endpoint = endpoint;
    this.number = number;
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

}
