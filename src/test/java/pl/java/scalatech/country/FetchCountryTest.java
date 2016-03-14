package pl.java.scalatech.country;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=FetchCountryTest.CountryConfig.class)
@Slf4j
public class FetchCountryTest {

    @Autowired
    private Resource country;
    
    private URI uri ;
    @Before
    @SneakyThrows        
    public void init(){
      uri =country.getURI();    
    }
    
    
    private static Stream<String> split(String line) {
        return Pattern.compile("\\s+").splitAsStream(line.trim());
    }
    
    @Test
    public void secondTest(){
        String line = "1    Afganistan     Afghanistan location map.svg    Azja    Kabul   652 230     30 419 928[1]   45,74";
        List<String> newL = Lists.newArrayList(line.split("\\s+"));
        
    }
    
    @SneakyThrows
    @Test
    public void test(){
        Function<String, Stream<String>> lineSplitter = line -> Pattern.compile("\\s+").splitAsStream(line);
        
        
        Stream<String> lines = Files.lines(Paths.get(uri));
        lines.flatMap(FetchCountryTest::split).forEach(System.out::println);
        lines.close();
    }
   
    
   // @Ignore
    @Test
    @SneakyThrows    
    public void shouldFetchAllCountry(){
        

            List<String> country = Files.readAllLines(Paths.get(uri));
            try (Stream<String> stream = Files.lines(Paths.get(uri))) {
                List<String> words = stream
                    
                    .map(String::trim)
                    
                    .flatMap(FetchCountryTest::split)
                    
                    .filter(s -> !s.matches("\\d+"))
                    .filter(s -> !s.matches("location"))
                    .filter(s -> !s.matches("map.svg"))
                    .filter(s -> !s.matches("\\d+\\,\\d+"))
                    .filter(s -> !s.contains("["))
                    .peek(l->log.info("--- {}",l))
                    .limit(24)
                    .collect(Collectors.toList());
            }
              

        
    }
    
    @Configuration
    static class CountryConfig{
        @Bean
        Resource country() {
            return new org.springframework.core.io.ClassPathResource("country.txt");
        }
    }
 
}
