package com.ecom.backend.service.facade;

import java.util.List;

public interface AbstractService <D,I>{
    D findById(I id);
    List<D> findAll();
    D save(D dto);
    D update(D dto, I id);
    void deleteById(I id);
}
