package org.academiadecodigo.javabank.session;

import org.academiadecodigo.javabank.model.Model;
import org.h2.engine.User;

import java.util.List;

public interface DAO<T extends Model> {

    // basic crud methods
    List<T> findAll();
    T findById(Integer id);
    T saveOrUpdate(T user);
    void delete(Integer id);


}
