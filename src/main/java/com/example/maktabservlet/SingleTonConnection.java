package com.example.maktabservlet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class SingleTonConnection {

    private static final SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {
            throw new ExceptionInInitializerError(th);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//    private SingleTonConnection(){}
//    private static class LazyHolder{
//        static SessionFactory INSTANCE;
//
//        static {
//            var registry = new StandardServiceRegistryBuilder()
//                    .configure()
//                    .build();
//
//            INSTANCE = new MetadataSources(registry)
//                    .addAnnotatedClass(PersonEntity.class)
//                    .buildMetadata()
//                    .buildSessionFactory();
//        }
//    }
//    public static SessionFactory getInstance(){
//        return LazyHolder.INSTANCE;
//    }
}
