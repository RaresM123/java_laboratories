package rmunteanu.javaInterceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@LoggerClass
@Interceptor
public class InterceptorLoggerClass implements Serializable {
    private final Logger logger = Logger.getLogger(InterceptorLoggerClass.class
            .getName());
    private FileHandler fileHandler = null;
    private SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd_HH:mm:ss");

    public SimpleDateFormat getFormat() {
        return format;
    }

    public void setFormat(SimpleDateFormat format) {
        this.format = format;
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        logger.info("Method that was enterer: "
                + invocationContext.getMethod().getName() + " in class "
                + invocationContext.getMethod().getDeclaringClass().getName());
        return invocationContext.proceed();
    }

    public InterceptorLoggerClass() {

        try {
            fileHandler = new FileHandler("D:\\Tema8_"
                    + getFormat().format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    }

}