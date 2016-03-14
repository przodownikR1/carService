package pl.java.scalatech.domain.dict;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.EqualsAndHashCode;
@Entity
@EqualsAndHashCode(callSuper=true)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="TASK_NAMES")
@AttributeOverrides({
    @AttributeOverride(name="name", column=@Column(name="TASK_NAME")),
    @AttributeOverride(name="desc", column=@Column(name="TASK_DICT"))
})
public class TaskDict extends Dict{  
    private static final long serialVersionUID = 770691972311906293L;
    
}
