package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.example.bean.Product;
import org.example.service.ProductService;
import org.example.utils.constants.HttpCode;
import org.example.utils.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid){
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为：{}", JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping("/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid,@PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为：商品id：{}，购买数量：{}",pid,count);
        int updateCount = productService.updateProductStockById(count,pid);
        Result<Integer> result = new Result<>(HttpCode.SUCESS, "执行成功", updateCount);
        return result;
    }
}
