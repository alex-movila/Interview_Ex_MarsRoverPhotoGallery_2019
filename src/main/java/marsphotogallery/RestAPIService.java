package marsphotogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.util.List;
@Service
public class RestAPIService implements IRestAPIService{

    private static final String PHOTO_SEARCH_URL =
            "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol={sol}&page={page}&api_key={key}";

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private IAPICallLogService apiCallLogService;
    
    public List<MarsPhoto> fetchNewPhotoList(long sol, long page) {

        long before = Instant.now().toEpochMilli();
        MarsPhotoList response =
                restTemplate.getForObject(
                        PHOTO_SEARCH_URL, MarsPhotoList.class, sol, page, Application.getKey());
        if (response != null) {
            long after = Instant.now().toEpochMilli();
            apiCallLogService.addNewLog(getParsedUrl(sol, page, Application.getKey()), after - before);
            return response.getPhotos();
        } else throw new NullPointerException("Response is null!");
    }

    private String getParsedUrl(Object... uriVariables) {
        URI expanded = restTemplate.getUriTemplateHandler().expand(PHOTO_SEARCH_URL, uriVariables);
        return expanded.toString();
    }
}
