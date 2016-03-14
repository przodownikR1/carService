package pl.java.scalatech.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="ROLES")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = -804077594557972107L;
    @Column(name="ROLE_NAME")
    private String name;
    
    @Column(name = "ROLE_DESC")
    private String desc;
}
