package pl.java.scalatech.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.dict.CountryDict;

@Embeddable
@NoArgsConstructor
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 4270911938633237445L;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    private CountryDict country;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50,name="TOWN")
    private String town;

    @Size(min = 2, max = 50)
    @Pattern(regexp = ".+")
    @Column(name="STREET")
    private String street;

    @Column(name="STREET_NUMBER")
    private String streetNumber;

  
    @Column(name="ZIP_CODE",length=6)
    private String zipcode;

    @Embedded
    private Location location;

}