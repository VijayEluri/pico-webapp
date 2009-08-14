package example.framework.template;

import org.antlr.stringtemplate.AttributeRenderer;

public class WebAttributeRenderer implements AttributeRenderer {

    private final WebFormat defaultFormat;

    public WebAttributeRenderer() {
        this(WebFormat.HTML);
    }

    public WebAttributeRenderer(WebFormat defaultFormat) {
        this.defaultFormat = defaultFormat;
    }

    public String toString(Object obj) {
        return defaultFormat.format(obj);
    }

    public String toString(Object obj, String formatName) {
        return WebFormat.fromName(formatName).format(obj);
    }
}
