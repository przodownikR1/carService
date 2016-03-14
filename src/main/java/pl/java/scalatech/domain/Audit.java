/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class Audit extends AbstractEntity{
    private static final long serialVersionUID = 2663518387056045900L;

    @CreatedDate
    @Convert(converter=LocalDateAttributeConverter.class)
    @Column(name="CREATED_DATE")
    private LocalDate createdDate = LocalDate.now();

    @LastModifiedDate
    @Convert(converter=LocalDateAttributeConverter.class)
    @Column(name="LAST_MODIFIED_DATE")
    private LocalDate lastModifiedDate = LocalDate.now();

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by_id")  
    private User createdBy;

    @LastModifiedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="last_modified_by_id")
    private User lastModifiedBy;

}
