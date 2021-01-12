import java.util.List;

public class JavaWsClient {
    public static void main(String[] args) {
        try {
            JavaWsClient client = new JavaWsClient();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
            ViewDocumentService_Service service = new ViewDocumentService_Service();
            ViewDocumentService port = service.getViewDocumentService();
            System.out.println("Invoking the view operation on the port.");

            Long id;
            try{
                id = new Long(args[0]);
            }catch(Exception e){
                id=null;
            }

            List<String> response = port.view(id);
            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
