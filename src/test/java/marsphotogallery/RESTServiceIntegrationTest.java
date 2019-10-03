package marsphotogallery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RESTServiceIntegrationTest {
    @Autowired
    private IRestAPIService restAPIService;

    @Test
    public void fetchNewPhotoListForSol0AndPage0(){
        List<MarsPhoto> result = restAPIService.fetchNewPhotoList( 0, 1);
        assertEquals(result.size(), 25);
    }

    @Test
    public void fetchNewPhotoListForSol4AndPage0(){
        List<MarsPhoto> result = restAPIService.fetchNewPhotoList( 4, 1);
        assertEquals(result.size(), 0);
    }

}
