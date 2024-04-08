package com.Unimagda.STienda.Service;

import org.springframework.data.domain.Page;


import java.util.Optional;

public interface Service<S, M, E>{
Page<M>findAll();
Optional<M>findById(Long id);
M save(S s);
void deleteById(Long id);
}
