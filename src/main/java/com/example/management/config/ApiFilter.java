//package com.example.management.config;
//
//import com.example.management.utils.Constants;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.slf4j.MDC;
//import org.springframework.http.HttpStatus;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import static com.example.management.enums.EventFieldEnum.*;
//
//public class ApiFilter implements Filter {
//    private static final Logger logger = LogManager.getLogger(ApiFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
//            throws IOException, ServletException {
//        logger.debug("Inside apiFilter");
//        MDC.clear();
//        MDC.put(Constants.LogLevel.LOG_API_EVENT_FLAG,String.valueOf(true));
//        MDC.put("request_id", "128");
//        if(servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse){
//            long startTime=System.currentTimeMillis();
//            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            HttpServletResponse response= (HttpServletResponse) servletResponse;
//            MDC.put(REQUEST_URL.name(), request.getRequestURI());
//            logger.info(request.getRequestURI()+"Request url");
//            MDC.put(RESPONSE_CODE.name(), String.valueOf(HttpStatus.OK));
//            MDC.put(RESPONSE_STATUS.name(), String.valueOf(true));
//            chain.doFilter(request,response);
//            MDC.put(RESPONSE_TIME.name(),String.valueOf(System.currentTimeMillis()-startTime));
//            //Do not log for excluded APIs
////            if(BooleanUtils.toBoolean(MDC.get(Constants.LogLevel.LOG_API_EVENT_FLAG))) {
////                logProcessCompleteEvent();
////            }
//
//        }else{
//            chain.doFilter(servletRequest,servletResponse);
//        }
//    }
//
////    private void logProcessCompleteEvent() {
////        /**
////         * Consolidate event fields and log them.
////         */
////        try {
////            String attributes = MDC.get(ATTRIBUTES.name());
////            String[] attributesArray = null;
////            if(null != attributes) {
////                attributesArray = attributes.split(",");
////            }
////            CustomEventLogger.publishProcessCompleteEvent(MDC.get(TENANT_ID.name()), MDC.get("requestId"),
////                    MDC.get(REQUEST_URL.name()), Long.valueOf(MDC.get(RESPONSE_TIME.name())), MDC.get(RESPONSE_CODE.name()),
////                    MDC.get(RESPONSE_STATUS.name()), attributesArray);
////        } catch (Exception ex) {
////            logger.error("Unable to log event", ex);
////        }
////    }
//
//    @Override
//    public void destroy() {
//        MDC.clear();
//    }
//}
//
