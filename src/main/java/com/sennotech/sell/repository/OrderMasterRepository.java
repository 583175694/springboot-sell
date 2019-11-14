package com.sennotech.sell.repository;
/*
 *   @author 吴少航
 *   @date 2019/10/11-16:12
 */


import com.sennotech.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //  根据买家openid查订单
    Page<OrderMaster> findByBuyerOpenid(String buyer, Pageable pageable);
}
