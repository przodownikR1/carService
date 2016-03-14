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
package pl.java.scalatech.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "USERS")
public class User extends AbstractEntity {

    private static final long serialVersionUID = -8920961125119379475L;
    @Column(name = "FIRST_NAME")
    private String firstname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWD")
    private String password;
    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    // @Valid
    private List<Role> roles;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<Car> cars;

    @Embedded
  //  @Valid
  // @NotNull
    @AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "USER_COUNRTY") ),
        @AttributeOverride(name = "street", column = @Column(name = "USER_STREET") ),
        @AttributeOverride(name = "town", column = @Column(name = "USER_TOWN") ),
        @AttributeOverride(name = "location.latitude", column = @Column(name = "USER_LATITUDE") ),
        @AttributeOverride(name = "location.longitude", column = @Column(name = "USER_LONGITUDE") )           

})
    private Address address;

}
