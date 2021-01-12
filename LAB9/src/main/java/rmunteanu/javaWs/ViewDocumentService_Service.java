
package rmunteanu.javaWs;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ViewDocumentService", targetNamespace = "http://javaWs.rmunteanu", wsdlLocation = "file:/D:/FAC_MASTER/AN1/SEM1/JAVA/tema8/src/main/java/rmunteanu/javaWs/ViewDocumentService.wsdl")
public class ViewDocumentService_Service
    extends Service
{

    private final static URL VIEWDOCUMENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException VIEWDOCUMENTSERVICE_EXCEPTION;
    private final static QName VIEWDOCUMENTSERVICE_QNAME = new QName("http://javaWs.rmunteanu", "ViewDocumentService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/FAC_MASTER/AN1/SEM1/JAVA/tema8/src/main/java/rmunteanu/javaWs/ViewDocumentService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        VIEWDOCUMENTSERVICE_WSDL_LOCATION = url;
        VIEWDOCUMENTSERVICE_EXCEPTION = e;
    }

    public ViewDocumentService_Service() {
        super(__getWsdlLocation(), VIEWDOCUMENTSERVICE_QNAME);
    }

    public ViewDocumentService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), VIEWDOCUMENTSERVICE_QNAME, features);
    }

    public ViewDocumentService_Service(URL wsdlLocation) {
        super(wsdlLocation, VIEWDOCUMENTSERVICE_QNAME);
    }

    public ViewDocumentService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, VIEWDOCUMENTSERVICE_QNAME, features);
    }

    public ViewDocumentService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ViewDocumentService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ViewDocumentService
     */
    @WebEndpoint(name = "ViewDocumentService")
    public ViewDocumentService getViewDocumentService() {
        return super.getPort(new QName("http://javaWs.rmunteanu", "ViewDocumentService"), ViewDocumentService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ViewDocumentService
     */
    @WebEndpoint(name = "ViewDocumentService")
    public ViewDocumentService getViewDocumentService(WebServiceFeature... features) {
        return super.getPort(new QName("http://javaWs.rmunteanu", "ViewDocumentService"), ViewDocumentService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (VIEWDOCUMENTSERVICE_EXCEPTION!= null) {
            throw VIEWDOCUMENTSERVICE_EXCEPTION;
        }
        return VIEWDOCUMENTSERVICE_WSDL_LOCATION;
    }

}
