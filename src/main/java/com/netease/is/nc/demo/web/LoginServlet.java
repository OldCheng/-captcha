package com.netease.is.nc.demo.web;

import com.netease.is.nc.sdk.NECaptchaVerifier;
import com.netease.is.nc.sdk.NESecretPair;
import com.netease.is.nc.sdk.entity.VerifyResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by captcha_dev on 16-10-9.
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -3185301474503659058L;
//    private static final String captchaId = "YOUR_CAPTCHA_ID"; // 验证码id
//    private static final String secretId = "YOUR_SECRET_ID"; // 密钥对id
//    private static final String secretKey = "YOUR_SECRET_KEY"; // 密钥对key

    private static final String captchaId = "435ec0e3442a49e59ad58cd82ffdadd4"; // 验证码id
    private static final String secretId = "934ae3c426d3c82470b2917b45923fdc"; // 密钥对id
    private static final String secretKey = "46794bda58d853972931659cf0d7e388"; // 密钥对key

    private final NECaptchaVerifier verifier = new NECaptchaVerifier(captchaId, new NESecretPair(secretId, secretKey));

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String validate = request.getParameter(NECaptchaVerifier.REQ_VALIDATE); // 从请求体里获得验证码validate数据
        String validate1 = "-y5PnBp-HKpciTmVYfoFBFdEdw08poDuwXdYdWf0MOWw9qeWDP2PZOKGZq6_vgq6jt2ynYYeY_FcU8XYjjQdnGpeqvFTS1iI2_zX-0iqCiUSCEL7qDxNpG.n8gSp9eGMnRHetExSC92jiu0AHDKjbMyl5VWSvyWooQ8-hxutDYe7_m7840P-H9GkPLp5P6tmk21bNG_oWdF6ktY..B1PT25IV_DmwKMRxPhRvrgDnOVoYr4zZJuCiOko46b0UlpSJXMLN_z7KdWSJ55wxzEcaGcNnyDLh7-wfzpJuVzEuWjKS7t9QyU9lic08eQmm4eZzCT2GHJ2aMYmRTqirjH5J_K-_ghlWu5foyNwFKc-v85lzDh6btKju8QWUQM.-EEKsmgS8NVUO7qPJ9X9LdWi2bHb_2TQrn40ByWlpvdcogBKTliayBAJGTurB_pfDrM0.OqjsVrCPhv8JbG2iuHhx2ET-nwplnKTKQWXAopD7wHQReriqa7tNaJScWN3";
        String user = "{'id':'123456'}";

        VerifyResult result = verifier.verify(validate, user); // 发起二次校验

        System.out.println(String.format("validate = %s,  isValid = %s , msg = %s ", validate, result.isResult(),
                result.getMsg()));
        if (result.isResult()) {
            response.sendRedirect("/success.jsp");
        } else {
            response.sendRedirect("/fail.jsp");
        }
    }
}
