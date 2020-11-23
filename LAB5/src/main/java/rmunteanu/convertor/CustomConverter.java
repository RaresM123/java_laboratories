package rmunteanu.convertor;

import rmunteanu.model.LocationBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("locationConverter")
public class CustomConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        LocationBean location = new LocationBean();
        if(s.contains("ROU")){
            s = s.replace("ROU", "Romania");
            location.setName(s);
        }
        if(s.contains("UK")){
            s = s.replace("UK", "United Kingdom");
            location.setName(s);
        }
        if(s.contains("US")){
            s = s.replace("US", "United States");
            location.setName(s);
        }
        return location;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return  String.valueOf(o);
    }
}
