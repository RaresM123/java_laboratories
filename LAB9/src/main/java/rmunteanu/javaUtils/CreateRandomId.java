package rmunteanu.javaUtils;

import javax.enterprise.inject.Produces;
import java.io.Serializable;
import java.util.UUID;

public class CreateRandomId implements Serializable {
    public
    @Produces
    @UniqueAdnotation.Unique
    String NewRandomID() {
        return UUID.randomUUID().toString();
    }
}
