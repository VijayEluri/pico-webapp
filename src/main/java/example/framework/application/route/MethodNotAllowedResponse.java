package example.framework.application.route;

import com.google.common.collect.Lists;
import example.framework.Header;
import example.framework.RequestMethod;
import example.framework.Response;
import example.framework.ResponseContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MethodNotAllowedResponse implements Response {

    private Set<RequestMethod> allowed;

    public MethodNotAllowedResponse(Set<RequestMethod> allowed) {
        this.allowed = allowed;
    }

    public void render(ResponseContext response) throws IOException {
        List<String> methods = allowedMethods();
        Collections.sort(methods);

        Header header = new Header();
        header.setField("Allow", StringUtils.join(methods, ","));
        response.setHeader(header);

        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    private List<String> allowedMethods() {
        List<String> names = Lists.newArrayList();
        for (RequestMethod method : allowed) {
            names.add(method.name());
        }
        return names;
    }
}
