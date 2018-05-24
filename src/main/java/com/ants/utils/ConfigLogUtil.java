package com.ants.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class ConfigLogUtil {

    private static ConcurrentMap<String, LinkedHashMap<String, Object>> logMap = new ConcurrentHashMap<>();

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(ConfigLogUtil.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static void put(String key, Object value) {
        String callerClassName = getCallerClassName();
        LinkedHashMap<String, Object> map = logMap.getOrDefault(callerClassName, new LinkedHashMap<>());
        map.put(key, value);
        logMap.put(callerClassName, map);
    }

    public static void printLog() {
        log.info("########## 输出配置信息 start ##########");
        for (Map.Entry<String, LinkedHashMap<String, Object>> logEntry : logMap.entrySet()) {
            String logKey = logEntry.getKey();
            Map<String, Object> paramMap = logEntry.getValue();
            int maxKeyLength = 0;
            for (String s : paramMap.keySet()) {
                maxKeyLength = maxKeyLength < s.length() ? s.length() : maxKeyLength;
            }
            for (Map.Entry<String, Object> paramEntry : paramMap.entrySet()) {
                String key = paramEntry.getKey();
                Object value = paramEntry.getValue();
                StringBuffer logContent = new StringBuffer();
                logContent.append(String.format("%-" + maxKeyLength + "s", key));
                logContent.append(" : ");
                logContent.append(value);
                log.info("# {} : {}", logKey, logContent.toString());
            }

        }
        log.info("########## 输出配置信息  end  ##########");
    }
}
