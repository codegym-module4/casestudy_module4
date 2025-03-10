package com.codegym.casestudy_module4.ulti;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component()
public class GlobalConstants {
    public static final Map<String, String> RECEIPT_TYPES = Map.of(
            "1", "Bán lẻ",
            "2", "Bán theo đơn",
            "3", "Bán sỉ"
    );

    public static final Map<String, String> RECEIPT_SORTBY = Map.of(
            "code", "Mã Hóa Đơn",
            "customer.name", "Tên khách hàng",
            "createdAt", "Thời gian lập",
            "employee.fullName", "Người lập",
            "total", "Tổng tiền"
    );
}
