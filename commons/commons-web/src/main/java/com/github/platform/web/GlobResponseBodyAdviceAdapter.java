package com.github.platform.web;

import com.github.platform.core.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by serv on 2017/2/17.
 */
@RestControllerAdvice
@ConfigurationProperties("rest")
public class GlobResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName()
            + ".ERROR";

    private boolean showException = true;
    private Logger logger = LoggerFactory.getLogger(GlobResponseBodyAdviceAdapter.class);

    @Value("${spring.application.name:null}")
    private String applicationName;

    public boolean isShowException() {
        return showException;
    }

    public void setShowException(boolean showException) {
        this.showException = showException;
    }

    public GlobResponseBodyAdviceAdapter() {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest httpRequest, ServerHttpResponse httpResponse) {

        HttpServletRequest request = ((ServletServerHttpRequest) httpRequest).getServletRequest();

        HttpServletResponse response = ((ServletServerHttpResponse) httpResponse).getServletResponse();

        if (response.getStatus() >= 400) {
            logger.error("request_uri: {}", getPath(request));

            Map<String, Object> resp = new LinkedHashMap<>();
            resp.put("success", false);
            resp.put("status", response.getStatus());
            resp.put("errCode", "exception");
            //转换异常
            Throwable throwable = getError(request);
            if (throwable == null) {
                if (body instanceof Map) {
                    resp.put("errMsg", ((Map) body).get("error"));
                    resp.put("data", ((Map) body).get("message"));
                    return resp;
                } else {
                    return body;
                }
            }

//            if (throwable instanceof HystrixRuntimeException && throwable.getCause() != null) {
//                throwable = throwable.getCause();
//            } else if (throwable instanceof NestedServletException && throwable.getCause() != null) {
//                throwable = throwable.getCause();
//            }

            //自定义code异常
            if (throwable instanceof BusinessException) {
                logger.error(throwable.getMessage());
                //自定义异常status为200
                resp.put("status", 200);
                resp.put("errCode", ((BusinessException) throwable).getErrCode());
            } else {
                logger.error(throwable.getMessage(), throwable);
                resp.put("errCode", null);
            }


            String errMsg = throwable.getMessage();
            if (throwable instanceof HttpMessageNotWritableException) {
                resp.put("status", 200);
                errMsg = ((HttpMessageNotWritableException) throwable).getRootCause().getMessage();
            }
            if (throwable instanceof HttpMessageNotReadableException) {
                resp.put("status", 200);
                errMsg = "请求中包含错误格式的数据,请检查";
            }
            if (throwable instanceof NullPointerException) {
                resp.put("status", 200);
                errMsg = "系统发生了未知的错误";
            }
            if (throwable.getClass().getName().equals("com.netflix.zuul.exception.ZuulException")) {
                resp.put("status", 200);
                errMsg = "请求服务暂时不可用,请稍后重试";
            }

            resp.put("errMsg", errMsg);
            resp.put("data", body);
            resp.put("exceptionType", throwable.getClass().getName());
            resp.put("exception", showException ? throwable : null);


//            //feign 请求
//            String clientType = request.getHeader(CLIENT_TYPE);
//            if (clientType != null && FEIGN_REQUEST_TYPE.equals(clientType)) {
//                resp.put(EXCEPTION_SERIALIZABLE, Base64Utils.encodeToString(SerializationUtils.serialize(throwable)));
//            } else {
//                response.setStatus(HttpServletResponse.SC_OK);
//            }
//
//            //bboss 请求
//            if (applicationName != null && applicationName.equals("bboss-web")) {
//                response.setStatus(500);
//                resp.put("message", throwable.getMessage());
//            }


            return resp;
        }
        return body;
    }


    private String getPath(HttpServletRequest request) {
        return (String) request.getAttribute("javax.servlet.error.request_uri");
    }

    public Throwable getError(HttpServletRequest request) {
        Throwable exception = (Throwable) request.getAttribute(ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
        return exception;
    }


}
