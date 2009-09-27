package example.domain.web;

import example.domain.Document;
import example.domain.Document.Field;
import example.domain.Property;
import example.framework.Request;
import example.utils.Maps;

import java.util.Map;

public class DocumentUtils {

    public static void setProperties(Request request, Document document) {
        for (Field field : document.getFields()) {
            String value = request.getParameter(field.name());
            document.set(field, new Property(value));
        }
    }

    public static Map<String, PropertyWrapper> createDocumentModel(Document document) {
        Map<String, PropertyWrapper> model = Maps.create();
        for (Field field : document.getFields()) {
            model.put(field.name(), new PropertyWrapper(field.name(), document.get(field)));
        }
        return model;
    }
}
