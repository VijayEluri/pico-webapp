package example.domain.services;

import example.domain.Document;
import example.domain.Document.Field;
import example.domain.DocumentValidator;
import example.domain.Property;

public class SimpleDocumentValidator implements DocumentValidator {

    public void validate(Document document) {
        checkForError(document, Field.one);
        checkForError(document, Field.two);
    }

    private void checkForError(Document document, Field field) {
        Property property = document.get(field);
        String value = property.getValue();
        if ("error".equalsIgnoreCase(value)) {
            property = property.copyWithMessage("Oops - <error> was provided");
            document.set(field, property);
        }
    }
}
