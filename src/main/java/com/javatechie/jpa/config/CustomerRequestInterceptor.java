package com.javatechie.jpa.config;

import com.javatechie.jpa.entity.AuditLog;
import com.javatechie.jpa.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class CustomerRequestInterceptor extends HandlerInterceptorAdapter {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Autowired
    private AuditRepository auditRepository;

    Integer auditId;
    AuditLog auditLogTemp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("preHandle:request:URI:"+request.getRequestURI());
        System.out.println("preHandle:request:remoteuser:"+request.getRemoteUser());
        System.out.println("preHandle:response:status:"+response.getStatus());
        startDateTime=LocalDateTime.now();
        System.out.println("StartTime:"+startDateTime);
        AuditLog auditLog = new AuditLog();
        auditLog.setUrl(request.getRequestURI());
        auditLog.setRequestor(request.getRemoteUser());
        auditLog.setStatus(Integer.toString(response.getStatus()));
        auditLog.setStartTime(startDateTime);
        auditLog.setEndTime(null);

        AuditLog auditResponse=auditRepository.save(auditLog);
        auditId =auditResponse.getAuditId();
        auditLogTemp = auditLog;
        return true;
    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
        System.out.println("afterCompletion:request:URI:"+request.getRequestURI());
        System.out.println("afterCompletion:request:remoteuser:"+request.getRemoteUser());
        System.out.println("afterCompletion:response:status:"+response.getStatus());
        endDateTime=LocalDateTime.now();
        System.out.println("EndTime:"+endDateTime);

        auditLogTemp.setEndTime(endDateTime);


        AuditLog auditResponse=auditRepository.save(auditLogTemp);

    }
}
