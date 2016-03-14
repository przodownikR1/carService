package pl.java.scalatech.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.dict.CarManufactory;
import pl.java.scalatech.domain.dict.CountryDict;
import pl.java.scalatech.domain.types.FuelType;

@Embeddable
@NoArgsConstructor
@Data
public class CarModel {

    @ManyToOne
    private CountryDict country;

    @ManyToOne
    private CarManufactory manufactory;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "MODEL_TYPE")
    private String modelType;

    private String engine;

    private FuelType fuelType;

}
