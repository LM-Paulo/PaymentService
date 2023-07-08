package br.com.alurafood.payment.service;

import br.com.alurafood.payment.Enum.Status;
import br.com.alurafood.payment.dto.PaymentDto;
import br.com.alurafood.payment.entity.PaymentEntity;
import br.com.alurafood.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository repository;

    private final ModelMapper modelMapper;


    public Page<PaymentDto> getAll(Pageable pageable){
        return repository.findAll(pageable).map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto getById(Long id){
        PaymentEntity payment = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return  modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto createPayment(PaymentDto dto){
        PaymentEntity payment = modelMapper.map(dto, PaymentEntity.class);
        payment.setStatus(Status.CRIADO);
        repository.save(payment);
        return  modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto updatePayment(Long id, PaymentDto dto){
        PaymentEntity payment = modelMapper.map(dto, PaymentEntity.class);
        payment.setId(id);
        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deletePayment(Long id){
        repository.deleteById(id);
    }







}
