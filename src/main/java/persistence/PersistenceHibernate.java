package persistence;

import entities.Client;
import entities.Order;
import jakarta.persistence.*;

import java.util.List;

public class PersistenceHibernate {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OrdersDB");
    private final EntityManager em;

    public PersistenceHibernate() {
        this.em = emf.createEntityManager();
    }

    public void registerClient(String name, String city) {
        em.getTransaction().begin();
        Client c = new Client(name, city);
        em.persist(c);
        em.getTransaction().commit();
    }

    public Client findClientById(int id) {
        return em.find(Client.class, id);
    }

    public List<Client> findClientByCity(String city) {
        return em.createQuery("SELECT c FROM Client c WHERE c.city LIKE :city", Client.class).setParameter("city", city).getResultList();
    }

    public void changeClientCity(int id, String newCity) {
        Client c = findClientById(id);
        assert c != null;
        c.setCity(newCity);
        em.getTransaction().begin();
        Query updateQuery = em.createQuery("UPDATE Client SET city = :city WHERE id = :id");
        updateQuery.setParameter("city", newCity);
        updateQuery.setParameter("id", id);
        updateQuery.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Client> getClientList() {
        return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    public void addClientOrder(Client client) {
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        Order o = new Order(client);
        em.persist(o);
        trx.commit();
    }

    public Order findOrderByClient(Client client) {
        return em.createQuery("SELECT o FROM Order o WHERE o.client = :idClient", Order.class).setParameter("idClient", client.getId()).getSingleResult();
    }
}
