package pl.java.scalatech.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CountryWrapper {
        private String name;
        private String code;

        public CountryWrapper createCountry(String s) {
            String array[] = s.split(",");
            return new CountryWrapper(array[0], array[1]);

        }

    }