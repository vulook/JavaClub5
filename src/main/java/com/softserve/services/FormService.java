package com.softserve.services;


import com.softserve.entity.Form;
import com.softserve.entity.Form;


import java.time.LocalDateTime;
import java.util.List;

public interface FormService {

    Form create(Form book);

    Form findByID(Long id);

    Form delete(Long id);
    List<Form> getAll();
    List<Form> findAllByID();

}
