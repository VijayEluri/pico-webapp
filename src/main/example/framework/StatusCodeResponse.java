package example.framework;

import static example.framework.ResponseUtils.addCookiesToResponse;
import static example.framework.ResponseUtils.addHeadersToResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusCodeResponse implements Response {

    private final StatusCode statusCode;

    public StatusCodeResponse(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (statusCode != null) {
            addHeadersToResponse(response, statusCode.getHeaders());
            addCookiesToResponse(response, statusCode.getCookies());
            sendError(response);
        }
    }

    private void sendError(HttpServletResponse response) throws IOException {
        String message = statusCode.getMessage();
        if (message != null) {
            response.sendError(statusCode.getCode(), message);
        } else {
            response.sendError(statusCode.getCode());
        }
    }
}
