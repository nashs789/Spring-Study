package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("info = {}, {}", username, age);

        resp.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(
              @RequestParam("username") String username
            , @RequestParam("age") int age
    ) {
        log.info("info = {}, {}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(
              @RequestParam String username
            , @RequestParam int age
    ) {
        log.info("info = {}, {}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(
              String username
            , int age
    ) {
        log.info("info = {}, {}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(
              @RequestParam(required = true) String username
            , @RequestParam(required = true) int age
    ) {
        log.info("info = {}, {}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
              @RequestParam(defaultValue = "spring") String username
            , @RequestParam(defaultValue = "30") int age
    ) {
        log.info("info = {}, {}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("info = {}, {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }
}
