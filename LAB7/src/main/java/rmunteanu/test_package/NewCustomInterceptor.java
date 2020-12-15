/*
 * Copyright (c) 2020.
 */

package rmunteanu.test_package;

import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Stateful
public class NewCustomInterceptor {
    // All the bean's methods will be intercepted by "log()"
    @AroundInvoke
    public Object log (InvocationContext ctx) throws Exception {
        String className = ctx.getTarget().getClass().getName();
        String methodName = ctx.getMethod().getName();
        String target = className + "." + methodName + "()";
        long t1 = System.currentTimeMillis();
        try {
            return ctx.proceed();
        } catch(Exception e) {
            throw e;
        } finally {
            long t2 = System.currentTimeMillis();
            System.out.println(target + " took " +
                    (t2-t1) + "ms to execute");
        }
    }
}
