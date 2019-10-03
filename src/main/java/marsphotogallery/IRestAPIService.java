package marsphotogallery;

import java.util.List;

public interface IRestAPIService {
    public List<MarsPhoto> fetchNewPhotoList(long sol, long page);
}
