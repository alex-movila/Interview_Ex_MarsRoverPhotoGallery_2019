package marsphotogallery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class APICallLogRepositoryIntegrationTest {
    @Autowired
    private APICallLogRepository apiCallLogRepository;

    @Test
    public void contextLoads() { }
    
    @Test
    public void save(){
        APICallLogRec newLogRec = new APICallLogRec();
        newLogRec.setMethodName("test");
        newLogRec.setResponseTime(100);

        Instant now = Instant.now();
        apiCallLogRepository.save(newLogRec);
        Optional<APICallLogRec> savedLogRecOpt = apiCallLogRepository.findById(newLogRec.getId());
        assertTrue(savedLogRecOpt.isPresent());
        APICallLogRec savedLogRec = savedLogRecOpt.orElseThrow(NullPointerException::new);
        assertEquals(savedLogRec.getMethodName(), "test");
        assertEquals(savedLogRec.getResponseTime(), 100);
        assertTrue(savedLogRec.getLocalLogDateTime().isAfter(now.atZone(ZoneId.systemDefault()).toLocalDateTime()));
        long duration = Duration.between(now.atZone(ZoneId.systemDefault()).toLocalDateTime(), savedLogRec.getLocalLogDateTime()).getSeconds();
        assertEquals(0, duration); 
                           
        apiCallLogRepository.deleteById(savedLogRec.getId());
        assertTrue( ((List<APICallLogRec>) apiCallLogRepository.findAll()).isEmpty());
    }
    
}
