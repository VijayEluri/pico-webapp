package example.framework.application.error;

import example.framework.Presenter;
import example.framework.Request;
import example.framework.Response;
import example.framework.RouteMapping;
import example.framework.template.Template;
import example.framework.template.TemplateFactory;

@RouteMapping("/error/{errorRef}")
public class ErrorReferencePresenter implements Presenter {

    private final TemplateFactory templateFactory;

    public ErrorReferencePresenter(TemplateFactory templateFactory) {
        this.templateFactory = templateFactory;
    }

    public Response display(Request request) {
        Template template = templateFactory.create("error", "error");
        template.set("errorRef", request.getPathVariable("errorRef"));
        return template;
    }
}
