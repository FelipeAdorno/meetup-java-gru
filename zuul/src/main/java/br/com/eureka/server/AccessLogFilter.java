package br.com.eureka.server;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
            log.info("http-method = " + request.getMethod() +
                    ", user-ip = " + request.getRemoteAddr() +
                    ", body = " + extractPostRequestBody(request) +
                    ", url = " + request.getRequestURL() +
                    ", response-body = " + RequestContext.getCurrentContext().getResponseBody() +
                    ", response-status = " + response.getStatus());
        return null;
    }

    private String extractPostRequestBody(HttpServletRequest request) {
        String value = "";
        if (!HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())
                && request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                value = IOUtils.toString(request.getInputStream(), "UTF-8");
            } catch (IOException e) {
                log.error("error on parse body request: ", e);
            }
        } else {
            value = "body to large";
        }
        return value;
    }
}