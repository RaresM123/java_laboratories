package rmunteanu.javaRs;

import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/document_delete")
public class DeleteDocumentService {
    @Inject
    private InterfaceUpload serviceUpload;
    @DELETE
    @Path("/{idParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDocumentFunction(@PathParam("idParam") BigDecimal idParam) {
        try {
            UploadEntity uploadEntity = serviceUpload.uploadFind(idParam);
            if (uploadEntity == null) {
                throw new Exception();
            }
            serviceUpload.uploadDelete(uploadEntity);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404).type(MediaType.APPLICATION_JSON).build();
        }
    }
}
