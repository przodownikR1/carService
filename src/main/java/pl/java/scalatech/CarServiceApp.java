/*
 * Copyright 2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.CountryRepository;
import pl.java.scalatech.repository.UserRepository;

@SpringBootApplication
@Slf4j
public class CarServiceApp {
    private final static String ANONYMOUS = "anonymous";

    @Bean
    Resource country() {
        return new org.springframework.core.io.ClassPathResource("country.txt");
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, CountryRepository countryRepository) {
        return (evt) ->

        Lists.newArrayList("przodownik", "kowalski", "nowak", "plank", "tyson", "holyfield", "jones", "obama", ANONYMOUS).forEach(a -> {
            User user = userRepository.save(User.builder().firstname(a).login(a).email(a + "@tlen.pl").login(a).enabled(true).build());
            log.info("{}", user);

        }

        );

    }

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApp.class, args);
    }
}
