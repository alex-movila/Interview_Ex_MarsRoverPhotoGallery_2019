package marsphotogallery;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class MarsPhotoList {
  @Getter @Setter
  private List<MarsPhoto> photos;

  public MarsPhotoList() {
    photos = new ArrayList<>();
  }
}
