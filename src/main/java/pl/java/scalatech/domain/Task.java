package pl.java.scalatech.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.dict.TaskDict;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="TASKS")
public class Task extends Audit implements Compute{

    private static final long serialVersionUID = -2981823859308225136L;
  
    
    @ManyToOne
    @JoinColumn(name="TASK_NAME_ID")
    private TaskDict task;
    
    @Column(name="PRICE_FROM")
    private BigDecimal priceFrom;
    @Column(name="PRICE_TO")
    private BigDecimal priceTo;
    
    @Column(name="COMPUTED")
    private boolean computed;
}
