package org.example.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.bean.Order;
import org.example.bean.OrderItem;
import org.example.bean.Product;
import org.example.bean.User;
import org.example.order.dao.OrderItemMapper;
import org.example.order.dao.OrderMapper;
import org.example.order.service.OrderService;
import org.example.order.vo.OrderVo;
import org.example.utils.constants.HttpCode;
import org.example.utils.resp.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderVo orderVo) {
        if(orderVo == null){
            throw new RuntimeException("参数异常：" + JSONObject.toJSONString(orderVo));
        }
        User user = restTemplate.getForObject("http://localhost:8060/user/get/"+orderVo.getUserId(),User.class);
        if(user == null){
            throw new RuntimeException("未获取到用户信息：" + JSONObject.toJSONString(orderVo));
        }
        Product product = restTemplate.getForObject("http://locahost:8070/product/get/"+orderVo.getProId(),Product.class);
        if(product == null){
            throw new RuntimeException("未获取到产品信息："+JSONObject.toJSONString(orderVo));
        }
        if(product.getProStock() < orderVo.getNumber()){
            throw new RuntimeException("商品库存不足："+ JSONObject.toJSONString(orderVo));
        }
        Order order = new Order();
        order.setAddress(orderVo.getAddress());
        order.setPhone(orderVo.getPhone());
        order.setTotalPrice(orderVo.getTotalPrice());
        order.setUserId(orderVo.getUserId());
        order.setUsername(orderVo.getUsername());
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderVo.getNumber());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject("http://localhost:8070/product/update_count/"+orderVo.getProId()
            + orderVo.getNumber(), Result.class);
        if(result.getCode() != HttpCode.SUCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }
}
