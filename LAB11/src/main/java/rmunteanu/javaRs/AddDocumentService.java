package rmunteanu.javaRs;

import rmunteanu.javaBeans.ServiceUploadBean;
import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/document_add")
public class AddDocumentService {
    @Inject
    private InterfaceUpload serviceUpload;

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDocumentFunction(UploadEntity uploadEntity) {
        try {
            boolean serviceResponse = serviceUpload.addDocumentUpload(uploadEntity);
            if(serviceResponse) {
                return Response.ok().entity(uploadEntity).type(MediaType.APPLICATION_JSON).build();
            }else{
                return Response.status(404).entity(uploadEntity).type(MediaType.APPLICATION_JSON).build();
            }
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }
}
