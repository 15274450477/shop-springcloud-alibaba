package org.example.order.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.OrderService;
import org.example.order.vo.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/sub")
    public String submitOrder(OrderVo orderVo){
         log.info("提交订单时传递的参数：{}"+ JSONObject.toJSONString(orderVo));
         orderService.saveOrder(orderVo);
         return "success";
    }
}
