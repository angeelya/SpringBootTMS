package com.example.SpringBootTMS.work43and44;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHQLService {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public List<Order> getAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Order");
        List<Order> orders= query.list();
        session.close();
        return orders;
    }

    public List<String> getCustomerName(String typeProduct) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select o.customerName from Order o where o.typeProduct=:type order by o.typeProduct");
        query.setParameter("type",typeProduct);
        List<String> names= query.list();
        session.close();
        return names;
    }

    public String insert(Order order) {
        String result = "Not successful. ";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("insert into Order (customerName,address,typeProduct,cost) select  :customerName,:address,:typeProduct,:cost");
            query.setParameter("customerName",order.getCustomerName());
            query.setParameter("address",order.getAddress());
            query.setParameter("typeProduct",order.getTypeProduct());
            query.setParameter("cost",order.getCost());
            if (query.executeUpdate() == 1) {
                result = "successful";
            }
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            return result += "\n"+e.getMessage();
        }
    }
    public String update(Long id,String address)
    {
        String result = "Not successful. ";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Order  set address =:address where id=:id");
            query.setParameter("address",address);
            query.setParameter("id",id);
            if (query.executeUpdate() == 1) {
                result = "successful";
            }
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            return result += "\n"+e.getMessage();
        }
    }

    public String delete(Long id) {
        String result = "Not successful. ";
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Order where id=:id");
            query.setParameter("id",id);
            if (query.executeUpdate() == 1) {
                result = "successful";
            }
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            return result += "\n"+e.getMessage();
        }
    }
    public List<Order> getOrderCostMore(Integer sum)
    {   session=sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> cq=cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root).where(cb.gt((root.get("cost")),sum)).orderBy(cb.asc(root.get("cost")));
        Query<Order> query = session.createQuery(cq);
        List<Order> results = query.getResultList();
        session.close();
        return results;
    }
}