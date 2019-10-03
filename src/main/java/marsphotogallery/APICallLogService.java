package marsphotogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APICallLogService implements IAPICallLogService {
    @Autowired
    private APICallLogRepository apiCallLogRepository;
    
    @Override
    public void addNewLog(String methodName, long responseTime) {
        APICallLogRec newLogRec = new APICallLogRec();
        newLogRec.setMethodName(methodName);
        newLogRec.setResponseTime(responseTime);
        
        apiCallLogRepository.save(newLogRec);
    }
} 
