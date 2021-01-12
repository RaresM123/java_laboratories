package rmunteanu.javaRs;

import rmunteanu.javaEntities.UploadEntity;
import rmunteanu.javaInterfaces.InterfaceUpload;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/document_update")
public class UpdateDocumentService {
    @Inject
    private InterfaceUpload serviceUpload;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDocumentUploadFunction(UploadEntity uploadEntity)
    {
        try {
            serviceUpload.uploadUpdate(uploadEntity);
            return Response.ok().entity(uploadEntity).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

}
