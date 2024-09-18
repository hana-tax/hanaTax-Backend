package com.example.demo.domain.notification.mapper;

import com.example.demo.domain.notification.dto.OverTaxAlertDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OverTaxAlertMapper {
    @Insert("INSERT INTO OVERTAX_ALERT (alertId, alertMethod, alertDate, alertState, id) VALUES (OVERTAX_ALERT_SEQ.nextval, #{alertMethod}, #{alertDate}, #{alertState}, #{id})")
    void insertOverTaxAlert(OverTaxAlertDto alert);
}
