package pl.java.scalatech.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
@Embeddable
public class Location {

    @Column(name = "latitude", nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Double latitude;

    @Column(name = "longitude", nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private Double longitude;
}
