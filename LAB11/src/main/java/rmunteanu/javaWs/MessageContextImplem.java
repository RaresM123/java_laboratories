package rmunteanu.javaWs;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaInterfaces.InterfaceUpload;
import rmunteanu.javaInterfaces.InterfaceUser;
import rmunteanu.javaServices.ServiceUpload;

import rmunteanu.javaEntities.UploadEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class MessageContextImplem implements LogicalHandler<LogicalMessageContext> {

    @Override
    public boolean handleMessage(LogicalMessageContext context) {

        Boolean outboundProperty = (Boolean)
                context.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!outboundProperty) {
            System.out.println("\nInbound message:");
        } else {
            System.out.println("\nOutbound message:");
        }
        return true;
    }

    @Override
    public boolean handleFault(LogicalMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
