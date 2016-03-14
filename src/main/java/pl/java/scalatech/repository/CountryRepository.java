package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.dict.CountryDict;

public interface CountryRepository extends JpaRepository<CountryDict, Long>{

}
