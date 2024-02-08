package com.example.springbootcrud.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

    private final ModelMapper modelMapper;

    public ModelMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T mapSourceToTarget(Object source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
