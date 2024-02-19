package com.example.SpringBootTMS.work43;

import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String put(Order order) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            session.close();
            return "successful";
        } catch (RollbackException | NullPointerException e) {
            log.error(e.getMessage());
            return "it isn't successful";
        }
    }

    public Order read(Long id) {
        session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    public String delete(Long id) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.delete(order);
            transaction.commit();
            session.close();
            return "successful";
        } catch (RollbackException | NullPointerException e) {
            log.error(e.getMessage());
            return "it isn't successful";
        }
    }

    public String update(Long id, String address) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            order.setAddress(address);
            session.save(order);
            transaction.commit();
            session.close();
            return "successful";
        } catch (RollbackException | NullPointerException e) {
            log.error(e.getMessage());
            return "it isn't successful";
        }
    }
}
