package rmunteanu.javaDecorators;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Decorator
public abstract class TimeDecorator implements InterfaceUpload {
    @Inject
    @Delegate
    @Any
    InterfaceUpload interfaceUpload;

    public static final String InitialStartDate = "05/01/2021 00:00";
    public static final String FinalEndDate = "20/02/2021 00:00";

    public boolean addDocumentUpload(UploadEntity m) {
        try {
            Date start=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(InitialStartDate);
            Date end=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(FinalEndDate);
            Date currentDate = new Date(System.currentTimeMillis());
            if(currentDate.after(start) && currentDate.before(end)) {
                return interfaceUpload.addDocumentUpload(m);
            }
            return false;

        } catch (ParseException e) {
            return false;
        }
    }

    public abstract List<ServiceUploadBean> getDocumentsUploads();
}
