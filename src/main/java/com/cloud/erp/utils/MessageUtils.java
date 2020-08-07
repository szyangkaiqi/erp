package com.cloud.erp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化工具类
 */
@Component
public class MessageUtils {

    protected static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        return get(msgKey, null);
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey, Object[] args) {
        try {
            return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return msgKey;
        }
    }

}