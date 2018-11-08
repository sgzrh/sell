package com.neuedu.sell.service;

import com.neuedu.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
     OrderDTO create(OrderDTO orderDTO);

    /**
     * 根据订单id查询
     * @param OrderId
     * @return
     */
     OrderDTO findOne(String OrderId);

    /**
     * 查询订单列表
     * @param pageable
     * @return
     */
     Page<OrderDTO> findList(Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
     OrderDTO cancle(OrderDTO orderDTO);

    /**
     * 完成订单
     * @param orderDTO
     * @return
     */
     OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
     OrderDTO paid(OrderDTO orderDTO);

}
