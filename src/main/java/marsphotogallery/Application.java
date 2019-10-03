package marsphotogallery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    private static Application app = null;

    @Value("${key}")
    private String key;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args
        );
    }
    
    public static String getKey(){
        return app.key;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println( key);
        app = this;
    }
}


