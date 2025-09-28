package com.doctor.file_processor.middleware;

import com.doctor.file_processor.domain.entity.AccessInfo;
import com.doctor.file_processor.service.AccessInfoService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@WebFilter
@Slf4j
public class RateLimitFilter implements Filter {

    private AccessInfoService accessInfoService;

    public RateLimitFilter(AccessInfoService accessInfoService) {
        this.accessInfoService = accessInfoService;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization logic, if any
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        log.info("Rate limiting filter");
        AccessInfo accessInfo = AccessInfo.builder()
                .created(new Date())
                .ip(request.getRemoteAddr())
                .build();

        accessInfoService.updateAccessInfo(accessInfo);
    }

    @Override
    public void destroy() {
        // Cleanup logic, if any
    }
}