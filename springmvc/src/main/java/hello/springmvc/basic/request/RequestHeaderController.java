package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/header")
    public String headers(
            HttpServletRequest req
            , HttpServletResponse resp
            , HttpMethod method
            , Locale locale
            , @RequestHeader MultiValueMap<String, String> headerMap
            , @RequestHeader("host") String host
            , @CookieValue(value = "myCookie", required = false) String cookie
            ) {

        log.info("request={}", req);
        log.info("response={}", resp);
        log.info("httpMethod={}", method);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
