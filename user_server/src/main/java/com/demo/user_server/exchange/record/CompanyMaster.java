package com.demo.user_server.exchange.record;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompanyMaster(Long id, String name, String website, LocalDateTime foundedDate, String industry,
		Integer status, String ceoName, String description, BigDecimal revenue) {

}
