package it.sipwarriors.hashcode;

public class Video implements Comparable<Video> {

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

@Override
public int compareTo(Video aVideo) {
	// TODO Auto-generated method stub
	return (id==aVideo.id && dimension == aVideo.dimension)?0:1;
}

}
