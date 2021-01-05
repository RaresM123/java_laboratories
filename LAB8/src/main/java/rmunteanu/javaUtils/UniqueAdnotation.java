package rmunteanu.javaUtils;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class UniqueAdnotation {
    @Target( { TYPE, METHOD, PARAMETER, FIELD })
    @Retention(RUNTIME)
    @Documented
    @Qualifier
    public @interface Unique {

    }
}
