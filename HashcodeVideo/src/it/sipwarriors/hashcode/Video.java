package it.sipwarriors.hashcode;

public class Video {

  private final int dimension;
  private final int id;

  public Video(int id, int dimension) {
    this.dimension = dimension;
    this.id = id;
  }

  public int getDimension() {
    return dimension;
  }

  public int getId() {
    return id;
  }

}
