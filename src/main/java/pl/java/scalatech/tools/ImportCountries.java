package pl.java.scalatech.tools;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.SneakyThrows;

public class ImportCountries {

    @SneakyThrows
    public static List<CountryWrapper> retrieveCountry(URI uri) {

        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            return stream.skip(1).map(String::trim).map(l -> new CountryWrapper().createCountry(l)).collect(Collectors.toList());

        }
    }

}
