package com.cloud.erp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cn.hutool.crypto.SecureUtil;

public class SecureUtilTest {

    @Test
    public void md5Test() throws Exception {
        Assertions.assertEquals(SecureUtil.md5("123"), SecureUtil.md5("123"));
        Assertions.assertEquals(SecureUtil.sha1(SecureUtil.md5("123")), SecureUtil.sha1(SecureUtil.md5("123")));

    }
}
