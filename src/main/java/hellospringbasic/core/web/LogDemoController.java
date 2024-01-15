package hellospringbasic.core.web;

import hellospringbasic.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest req) {
        String reqUrl = req.getRequestURL().toString();

        myLogger.setRequestUrl(reqUrl);
        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "ok";
    }
}
