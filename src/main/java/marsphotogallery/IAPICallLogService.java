package marsphotogallery;

public interface IAPICallLogService {
    void addNewLog(String methodName, long responseTime);
} 
