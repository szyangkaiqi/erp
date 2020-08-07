package com.cloud.erp.constant;

public interface BaseConstant {

    /** 上次请求地址 */
    String PREREQUEST = "PREREQUEST";
    /** 上次请求时间 */
    String PREREQUEST_TIME = "PREREQUEST_TIME";
    /** 非法请求次数 */
    String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";

    /**
     * 错误标识
     */
    enum ERROR_FLAG {
        // 成功
        Y,
        // 失败
        N,
        // 警告
        W,
        // 确认
        C
    }

    /*
    请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
     */
    enum HTTP_CODE {
        /**
         * 200请求成功
         */
        OK(200),
        /**
         * 207频繁操作
         */
        MULTI_STATUS(207),
        /**
         * 303登录失败
         */
        LOGIN_FAIL(303),
        /**
         * 400请求参数出错
         */
        BAD_REQUEST(400),
        /**
         * 401没有登录
         */
        UNAUTHORIZED(401),
        /**
         * 403没有权限
         */
        FORBIDDEN(403),
        /**
         * 404找不到页面
         */
        NOT_FOUND(404),
        /**
         * 408请求超时
         */
        REQUEST_TIMEOUT(408),
        /**
         * 409发生冲突
         */
        CONFLICT(409),
        /**
         * 410已被删除
         */
        GONE(410),
        /**
         * 423已被锁定
         */
        LOCKED(423),
        /**
         * 500服务器出错
         */
        INTERNAL_SERVER_ERROR(500);

        private final Integer value;

        HTTP_CODE(Integer value) {
            this.value = value;
        }

        /**
         * Return the integer value of this status code.
         */
        public Integer value() {
            return this.value;
        }

    }
}
