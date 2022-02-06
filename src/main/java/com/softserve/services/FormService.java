package com.softserve.services;


import com.softserve.entity.Form;


import java.time.LocalDateTime;
import java.util.List;

public interface FormService {

    Form create(Form form);

    Form findById(Long id);

    Form readInfo(Long id);

    Form findByDate(LocalDateTime time);

    List<Form>findAll();

}
