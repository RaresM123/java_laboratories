package rmunteanu.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
@ManagedBean(name = "filenameBean")
@RequestScoped
public class FileNameBean implements Serializable {
    private String filename;

    public FileNameBean(){};

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
