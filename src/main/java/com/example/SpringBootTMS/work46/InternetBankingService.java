package com.example.SpringBootTMS.work46;

import com.example.SpringBootTMS.work46.dto.CardDTO;
import com.example.SpringBootTMS.work46.dto.ClientDTO;
import com.example.SpringBootTMS.work46.dto.Remittance;
import com.example.SpringBootTMS.work46.exception.RemittanceException;
import com.example.SpringBootTMS.work46.model.Card;
import com.example.SpringBootTMS.work46.model.Client;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Service
public class InternetBankingService {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public Client getInformationClient(Long id) {
        try {
            session = sessionFactory.openSession();
            Client client = session.get(Client.class, id);
            session.close();
            return client;
        } catch (JDBCConnectionException e) {
            return null;
        }
    }

    public String insertCard(CardDTO cardDTO) {
        try {
            session = sessionFactory.openSession();
            Client client = session.get(Client.class, cardDTO.getClient_id());
            session.close();
            Card card = new Card();
            card.setAccount(cardDTO.getAccount());
            card.setSum(cardDTO.getSum());
            card.setClient(client);
            return insert(card);
        } catch (JDBCConnectionException e) {
            return "Not successful. " + e.getMessage();
        }
    }

    public String insertClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setLogin(clientDTO.getLogin());
        return insert(client);
    }

    private String insert(Object object) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            session.close();
            return "successful";
        } catch (NullPointerException | JDBCConnectionException | ConstraintViolationException e) {
            return "Not successful. " + e.getMessage();
        } catch (RollbackException e) {
            transaction.rollback();
            return "Not successful. " + e.getMessage();
        }
    }

    public Card getInformationCard(Long id) {
        try {
            session = sessionFactory.openSession();
            Card card = session.get(Card.class, id);
            session.close();
            return card;
        } catch (JDBCConnectionException e) {
            return null;
        }

    }

    public String makeRemittance(Remittance remittance) {
        Query query;
        Card to, from;
        try {
            if (remittance.getAccountTo().equals(remittance.getAccountFrom()))
                throw new RemittanceException("Account from equals account to");
            session = sessionFactory.openSession();
            query = session
                    .createQuery("from Card c where c.account=:account");
            query.setParameter("account", remittance.getAccountTo());
            to = (Card) query.uniqueResult();
            query.setParameter("account", remittance.getAccountFrom());
            from = (Card) query.uniqueResult();
            if (to == null || from == null)
                throw new RemittanceException("Оne of the accounts does not exist");
            else if (from.getSum() < remittance.getSum())
                throw new RemittanceException("There are not enough funds in the account");
            to.setSum(to.getSum() + remittance.getSum());
            from.setSum(from.getSum() - remittance.getSum());
            transaction = session.beginTransaction();
            session.save(to);
            session.save(from);
            transaction.commit();
            session.close();
            return "successful";
        } catch (NullPointerException | JDBCConnectionException | ConstraintViolationException |
                 RemittanceException e) {
            return "Not successful. " + e.getMessage();
        } catch (RollbackException e) {
            transaction.rollback();
            return "Not successful. " + e.getMessage();
        }
    }
}
