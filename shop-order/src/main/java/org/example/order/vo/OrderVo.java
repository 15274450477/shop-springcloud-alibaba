package org.example.order.vo;

import lombok.Data;
import org.example.bean.Order;

import java.math.BigDecimal;

@Data
public class OrderVo extends Order {
    /**
     * 商品id
     */
    private Long proId;
    /**
     * 购买数量
     */
    private Integer number;

    private Long orderId;
    private String proName;
    private BigDecimal proPrice;
}
