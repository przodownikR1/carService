package pl.java.scalatech.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.types.BodyType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@Table(name="CARS")
@EntityListeners(AuditingEntityListener.class)
public class Car extends Audit implements Compute{
    private static final long serialVersionUID = 3915980909758028913L;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="WANT_TO_SELL")
    private boolean wantToSell;
    
    @ElementCollection
    @CollectionTable(name="pictures",joinColumns=@JoinColumn(name="CAR_ID"))
    private List<String> pictureUrls;
    @Column(name="PRIVATE_INFO")
    private boolean privateInfo;
    @Column(name="COMPUTED")
    private boolean computed;
    @Enumerated
    @Column(name="BODY_TYPE")
    private BodyType bodyType;
    
    @Column(name="MILEAGE")
    private int mileAge;
    
    
}
