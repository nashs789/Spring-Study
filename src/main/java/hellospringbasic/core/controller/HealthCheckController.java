package hellospringbasic.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class HealthCheckController {

    @Value("${server.env}")
    private String env;
    @Value("${server.port}")
    private String port;
    @Value("${server.address}")
    private String addr;
    @Value("${serverName}")
    private String name;

    @GetMapping("/hc")
    public ResponseEntity<?> healthCheck() {
        Map<String, String> res = new TreeMap<>();

        res.put("env", env);
        res.put("port", port);
        res.put("addr", addr);
        res.put("name", name);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/env")
    public ResponseEntity<?> getEnv() {
        return ResponseEntity.ok(env);
    }
}
