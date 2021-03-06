package controller.Converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * The <strong><code>ListIndexConverter</code></strong> automatically converts
 * between the index of an object and the object itself based on its position in
 * a list that's provided to this converter.
 * <p>
 * This is a variant of the {@link SelectItemsIndexConverter}. See its Javadoc
 * for further documentation.
 * </p>
 *
 * @author Arjan Tijms
 */
@FacesConverter("omnifaces.ListIndexConverter")
public class ListIndexConverter implements Converter {

    private static final String ERROR_LIST_INDEX_BOUNDS
            = "Index {0} for value {1} in component {2} is out of bounds.";

    private static final String ERROR_VALUE_NOT_IN_LIST
            = "Object {0} in component {1} does not appear to be present in the given list.";

    private List<?> list;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int index = Integer.valueOf(value);
        if (index < 0 || index >= list.size()) {
            throw new ConverterException( 
                //createError(ERROR_LIST_INDEX_BOUNDS, index, value, component.getClientId(context))
                    );
        }
        return list.get(index);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                return i + "";
            }
        }
        throw new ConverterException( 
            //createError(ERROR_VALUE_NOT_IN_LIST, value == null ? "null" : value.toString(), component.getClientId(context))
                );
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
