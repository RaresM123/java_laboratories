
package rmunteanu.javaWs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rmunteanu.javaWs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _View_QNAME = new QName("http://javaWs.rmunteanu/", "view");
    private final static QName _OperationView_QNAME = new QName("http://javaWs.rmunteanu/", "operationView");
    private final static QName _ViewResponse_QNAME = new QName("http://javaWs.rmunteanu/", "viewResponse");
    private final static QName _OperationViewResponse_QNAME = new QName("http://javaWs.rmunteanu/", "operationViewResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rmunteanu.javaWs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OperationView }
     * 
     */
    public OperationView createOperationView() {
        return new OperationView();
    }

    /**
     * Create an instance of {@link View }
     * 
     */
    public View createView() {
        return new View();
    }

    /**
     * Create an instance of {@link ViewResponse }
     * 
     */
    public ViewResponse createViewResponse() {
        return new ViewResponse();
    }

    /**
     * Create an instance of {@link OperationViewResponse }
     * 
     */
    public OperationViewResponse createOperationViewResponse() {
        return new OperationViewResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link View }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaWs.rmunteanu/", name = "view")
    public JAXBElement<View> createView(View value) {
        return new JAXBElement<View>(_View_QNAME, View.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationView }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaWs.rmunteanu/", name = "operationView")
    public JAXBElement<OperationView> createOperationView(OperationView value) {
        return new JAXBElement<OperationView>(_OperationView_QNAME, OperationView.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaWs.rmunteanu/", name = "viewResponse")
    public JAXBElement<ViewResponse> createViewResponse(ViewResponse value) {
        return new JAXBElement<ViewResponse>(_ViewResponse_QNAME, ViewResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationViewResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaWs.rmunteanu/", name = "operationViewResponse")
    public JAXBElement<OperationViewResponse> createOperationViewResponse(OperationViewResponse value) {
        return new JAXBElement<OperationViewResponse>(_OperationViewResponse_QNAME, OperationViewResponse.class, null, value);
    }

}
