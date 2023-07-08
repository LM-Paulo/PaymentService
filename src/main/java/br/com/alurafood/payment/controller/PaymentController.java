package br.com.alurafood.payment.controller;


import br.com.alurafood.payment.dto.PaymentDto;
import br.com.alurafood.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Payment")
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/ListPayment")
    public Page<PaymentDto> listAll(@PageableDefault(size = 10)Pageable pageable){
        return service.getAll(pageable);
    }

    @GetMapping("/listById/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable @NotNull Long id){
        PaymentDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto dto, UriComponentsBuilder uriComponentsBuilder){
        PaymentDto payment = service.createPayment(dto);
        URI address = uriComponentsBuilder.path("/payment/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(address).body(payment);
    }

    @PutMapping("/ToUpdate")
    public ResponseEntity<PaymentDto> ToUpdate(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto){
        PaymentDto updeted = service.updatePayment(id,dto);
        return ResponseEntity.ok(updeted);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PaymentDto> delete (@PathVariable @NotNull Long id){
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
