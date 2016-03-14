package pl.java.scalatech.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="CAR_SERVICES")
public class CarService extends Audit implements Compute{

    private static final long serialVersionUID = 8970143412608389412L;
    public static final int MAX_NAME_LENGTH = 30;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SERVICE_CARS", joinColumns = { @JoinColumn(name = "SERVICE_ID") }, inverseJoinColumns = { @JoinColumn(name = "CAR_ID") })
    // @Valid
    private List<Car> cars ;
    @Column(name="SERVICE_DESC")
    private String desc;
    @Column(name="COMPUTED")
    private boolean computed;
    
    @OneToMany
    @JoinColumn(name="CAR_SERVICE_ID")
    private List<Task> tasks;
    
    
    @Embedded
  //  @Valid
  // @NotNull
    @AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "SERVICE_COUNRTY") ),
            @AttributeOverride(name = "street", column = @Column(name = "SERVICE_STREET") ),
            @AttributeOverride(name = "town", column = @Column(name = "SERVICE_TOWN") ),
            @AttributeOverride(name = "streetNumber", column = @Column(name = "SERVICE_STREET_NUMBER") ),
            @AttributeOverride(name = "location.latitude", column = @Column(name = "SERVICE_LATITUDE") ),
            @AttributeOverride(name = "location.longitude", column = @Column(name = "SERVICE_LONGITUDE") ),
            @AttributeOverride(name = "zipCode", column = @Column(name = "SERVICE_ZIP_CODE") )

    })
    private Address address;
    
 
}
