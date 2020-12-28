package com.zzy.servlet;

import com.zzy.factory.BeanFactory;
import com.zzy.service.TransferService;
import com.zzy.utils.JsonUtils;
import com.zzy.service.TransferServiceImpl;
import com.zzy.pojo.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/27 11:17
 */
@WebServlet(urlPatterns = "/transferServlet",name = "transferServlet")
public class MyServlet extends HttpServlet {
    private TransferService transferService = (TransferService) BeanFactory.getBean("transferService");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("Hello Servlet3.0");
        System.out.println("uuuuuuuuuuuuu");
        //doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("uuuuuuuuuuuuu");
        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");

        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);

        Result result = new Result();

        try {

            // 2. 调用service层方法
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
        }

        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
