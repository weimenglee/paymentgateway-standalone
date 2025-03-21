package com.multicode.payments.control;

import com.multicode.payments.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HealthCheckController {

    @GetMapping("/health")
    public String systemIsHealthy() {
        return "{\"status\":\"ok\"}";
    }

    @PostMapping("/restart")
    public String restartSystem(@RequestBody Map<String, String> body, HttpServletRequest request) {
        if (body.containsKey("pw")) {
            if (!body.get("pw").equals("restartnow")) {
                return "{\"status\":\"error\", \"message\":\"Invalid password\"}";
            }
            else {
                String ipAddress = request.getRemoteAddr();
                System.out.println("Restart requested by: " + ipAddress);
                System.exit(0);
                return "{\"status\":\"ok\"}";
            }
        }
        else {
            return "{\"status\":\"error\", \"message\":\"No password supplied\"}";
        }

    }


}
