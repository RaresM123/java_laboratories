package rmunteanu.javaEntities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @NotNull(message = "Name of the user can't be null")
    @Size(min = 5, max = 100, message
            = "Size of the name user can't be lower than 5 or bigger than 100")
    private String usr;

    @NotNull(message = "Password of the user can't be null")
    @Size(min = 5, max = 100, message
            = "Size of the password user can't be lower than 5 or bigger than 100")
    private String pwd;

    @NotNull(message = "Type of the user can't be null")
    private String type;

    @OneToMany( cascade = CascadeType.ALL,
            mappedBy = "user" )
    private List<UploadEntity> uploadedDocumentsList;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UploadEntity> getUploadedDocumentsList() {
        return uploadedDocumentsList;
    }

    public void setUploadedDocumentsList(List<UploadEntity> uploadedDocumentsList) {
        this.uploadedDocumentsList = uploadedDocumentsList;
    }
}
