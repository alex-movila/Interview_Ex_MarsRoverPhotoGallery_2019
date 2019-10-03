package marsphotogallery;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="API_CALLS_LOG")
public class APICallLogRec implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="article_id")
    private long id;

    @Column(name="method_name")
    private String methodName;
    @Column(name="response_time")
    private long responseTime;
    
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime localLogDateTime;
}
