package pl.java.scalatech.domain.dict;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.Audit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public abstract class Dict extends Audit{
    private static final long serialVersionUID = -3252623313224997961L;
    
    protected String name;
  
    protected String desc;

}
