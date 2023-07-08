package br.com.alurafood.payment.dto;

import br.com.alurafood.payment.Enum.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long formOfPaymentId;
}
