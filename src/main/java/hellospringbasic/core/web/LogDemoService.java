package hellospringbasic.core.web;

import hellospringbasic.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    public final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log("service = " + id);
    }
}
