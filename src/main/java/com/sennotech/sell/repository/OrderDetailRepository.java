package com.sennotech.sell.repository;
/*
 *   @author 吴少航
 *   @date 2019/10/11-16:12
 */

import com.sennotech.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
