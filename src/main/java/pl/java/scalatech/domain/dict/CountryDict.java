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
@Table(name="COUNTRY")  
@AttributeOverrides({
    @AttributeOverride(name="name", column=@Column(name="COUNTRY_NAME")),
    @AttributeOverride(name="desc", column=@Column(name="COUNTRY_DICT"))
})
public class CountryDict extends Dict{
    
    private static final long serialVersionUID = 7230364957711046881L;
    
    @Column(name="COUNTRY_CODE")
    private String code;

}
