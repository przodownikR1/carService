package pl.java.scalatech.domain.dict;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;

@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="CAR_MANUFACTORY")  
@AttributeOverrides({
    @AttributeOverride(name="name", column=@Column(name="CAR_MANUFACTORY_NAME")),
    @AttributeOverride(name="desc", column=@Column(name="CAR_MANUFACTORY_DICT"))
})
public class CarManufactory extends Dict{

    private static final long serialVersionUID = -870090462991585982L;

}
