import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ViewDocumentService", targetNamespace = "http://javaWs.rmunteanu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ViewDocumentService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "view", targetNamespace = "http://javaWs.rmunteanu/", className = "rmunteanu.javaWs.View")
    @ResponseWrapper(localName = "viewResponse", targetNamespace = "http://javaWs.rmunteanu/", className = "rmunteanu.javaWs.ViewResponse")
    @Action(input = "http://javaWs.rmunteanu/ViewDocumentService/viewRequest", output = "http://javaWs.rmunteanu/ViewDocumentService/viewResponse")
    public List<String> view(
            @WebParam(name = "arg0", targetNamespace = "")
                    List<String> arg0);

    /**
     * 
     * @param id
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "operationView", targetNamespace = "http://javaWs.rmunteanu/", className = "rmunteanu.javaWs.OperationView")
    @ResponseWrapper(localName = "operationViewResponse", targetNamespace = "http://javaWs.rmunteanu/", className = "rmunteanu.javaWs.OperationViewResponse")
    @Action(input = "http://javaWs.rmunteanu/ViewDocumentService/operationViewRequest", output = "http://javaWs.rmunteanu/ViewDocumentService/operationViewResponse")
    public List<String> operationView(
            @WebParam(name = "id", targetNamespace = "")
                    Long id);

}
