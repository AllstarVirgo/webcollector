package com.potato.demo.controller;

import com.google.gson.Gson;
import com.potato.demo.dao.impl.UserImpl;
import com.potato.demo.domain.User;
import com.potato.demo.utils.CaptchaUtil;
import com.potato.demo.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    private Logger logger=LoggerFactory.getLogger(this.getClass());

    CaptchaUtil util = CaptchaUtil.Instance();

    private UserImpl userImpl=new UserImpl();

    @RequestMapping("/user/check.jpg")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        request.getSession().setAttribute("code", code);
        // 输出打web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login/verify",method = RequestMethod.POST)
    public void loginVerify(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Gson gson=new Gson();
        Map<String,String> resultMap=new HashMap<String,String>();

        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }

        String name,password,token;
        if(!wholeStr.equals("")){
            Map<String, String> map =gson.fromJson(wholeStr,Map.class);
            name=map.get("name");
            password=map.get("password");
            token=map.get("token");
            if(name==null||password==null||token==null){
                //跳转到登陆页面

            }else {
                //从数据库取出并对比用户名-密码
                String getPassword=userImpl.getPassword(name);
                if(!getPassword.equals(password)){
                    //提示用户密码错误
                    System.out.println("密码错误");
                }
                //验证码对比
                String getToken=util.getString();
                logger.debug(getToken);
                logger.debug(token);
                if(getToken.equals(token)){
                    //用户名，密码，验证码相同则跳转到Home页面
                    response("{\"status\":\""+Status.OK+"\"}",response);
                }else {
                    System.out.println("验证码不正确");
                    response("{\"status\":\""+Status.ERROR_VERIFICATION_CODE+"\"}",response);
                }
            }
        }else response("{\"status\":\""+Status.ERROR_KEY+"\"}",response);
    }


    private void response(String string,HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        out.write(string);
        out.close();
    }

}
