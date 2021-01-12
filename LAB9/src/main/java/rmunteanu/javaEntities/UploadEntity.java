package rmunteanu.javaEntities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class UploadEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @NotNull(message = "generated id can't be null")
    private String randomGeneratedId;

    @Column(name="contentdocument")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] documentContent;

    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }

    @NotNull(message = "Name of the file can't be null")
    @Size(min = 5, max = 256, message
            = "Size of file name can't be lower than 5 or bigger than 256")
    private String fileName;

    @ManyToOne
    @JoinColumn( name = "userentity_id", referencedColumnName = "id", nullable = false )
    private UserEntity usr;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRandomGeneratedId() {
        return randomGeneratedId;
    }

    public void setRandomGeneratedId(String randomGeneratedId) {
        this.randomGeneratedId = randomGeneratedId;
    }

    public UserEntity getUsr() {
        return usr;
    }

    public void setUsr(UserEntity usr) {
        this.usr = usr;
    }
}
