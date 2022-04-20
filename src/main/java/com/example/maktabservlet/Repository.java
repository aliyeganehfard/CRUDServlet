package com.example.maktabservlet;

import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class Repository{
    private SessionFactory sessionFactory = SingleTonConnection.getSessionFactory();
    public void save(PersonEntity t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.save(t);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public void update(PersonEntity t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.update(t);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public void delete(PersonEntity t) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                session.delete(t);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    public PersonEntity findById(Integer id) {
        try (var session = sessionFactory.openSession()){
            return session.get(PersonEntity.class, id);
        }
    }
}
