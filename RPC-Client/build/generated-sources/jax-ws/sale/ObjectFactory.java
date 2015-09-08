
package sale;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sale package. 
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

    private final static QName _NewSale_QNAME = new QName("http://sale/", "newSale");
    private final static QName _GetSales_QNAME = new QName("http://sale/", "getSales");
    private final static QName _GetSalesResponse_QNAME = new QName("http://sale/", "getSalesResponse");
    private final static QName _NewSaleResponse_QNAME = new QName("http://sale/", "newSaleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sale
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewSale }
     * 
     */
    public NewSale createNewSale() {
        return new NewSale();
    }

    /**
     * Create an instance of {@link GetSales }
     * 
     */
    public GetSales createGetSales() {
        return new GetSales();
    }

    /**
     * Create an instance of {@link NewSaleResponse }
     * 
     */
    public NewSaleResponse createNewSaleResponse() {
        return new NewSaleResponse();
    }

    /**
     * Create an instance of {@link GetSalesResponse }
     * 
     */
    public GetSalesResponse createGetSalesResponse() {
        return new GetSalesResponse();
    }

    /**
     * Create an instance of {@link SaleItem }
     * 
     */
    public SaleItem createSaleItem() {
        return new SaleItem();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Sale }
     * 
     */
    public Sale createSale() {
        return new Sale();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewSale }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sale/", name = "newSale")
    public JAXBElement<NewSale> createNewSale(NewSale value) {
        return new JAXBElement<NewSale>(_NewSale_QNAME, NewSale.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sale/", name = "getSales")
    public JAXBElement<GetSales> createGetSales(GetSales value) {
        return new JAXBElement<GetSales>(_GetSales_QNAME, GetSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sale/", name = "getSalesResponse")
    public JAXBElement<GetSalesResponse> createGetSalesResponse(GetSalesResponse value) {
        return new JAXBElement<GetSalesResponse>(_GetSalesResponse_QNAME, GetSalesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewSaleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sale/", name = "newSaleResponse")
    public JAXBElement<NewSaleResponse> createNewSaleResponse(NewSaleResponse value) {
        return new JAXBElement<NewSaleResponse>(_NewSaleResponse_QNAME, NewSaleResponse.class, null, value);
    }

}
