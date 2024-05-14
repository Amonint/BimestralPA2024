/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import BD.exceptions.IllegalOrphanException;
import BD.exceptions.NonexistentEntityException;
import Clases.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Direccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sofiv
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getDireccionCollection() == null) {
            cliente.setDireccionCollection(new ArrayList<Direccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Direccion> attachedDireccionCollection = new ArrayList<Direccion>();
            for (Direccion direccionCollectionDireccionToAttach : cliente.getDireccionCollection()) {
                direccionCollectionDireccionToAttach = em.getReference(direccionCollectionDireccionToAttach.getClass(), direccionCollectionDireccionToAttach.getIdDireccion());
                attachedDireccionCollection.add(direccionCollectionDireccionToAttach);
            }
            cliente.setDireccionCollection(attachedDireccionCollection);
            em.persist(cliente);
            for (Direccion direccionCollectionDireccion : cliente.getDireccionCollection()) {
                Cliente oldPkClienteOfDireccionCollectionDireccion = direccionCollectionDireccion.getPkCliente();
                direccionCollectionDireccion.setPkCliente(cliente);
                direccionCollectionDireccion = em.merge(direccionCollectionDireccion);
                if (oldPkClienteOfDireccionCollectionDireccion != null) {
                    oldPkClienteOfDireccionCollectionDireccion.getDireccionCollection().remove(direccionCollectionDireccion);
                    oldPkClienteOfDireccionCollectionDireccion = em.merge(oldPkClienteOfDireccionCollectionDireccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdClente());
            Collection<Direccion> direccionCollectionOld = persistentCliente.getDireccionCollection();
            Collection<Direccion> direccionCollectionNew = cliente.getDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (Direccion direccionCollectionOldDireccion : direccionCollectionOld) {
                if (!direccionCollectionNew.contains(direccionCollectionOldDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Direccion " + direccionCollectionOldDireccion + " since its pkCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Direccion> attachedDireccionCollectionNew = new ArrayList<Direccion>();
            for (Direccion direccionCollectionNewDireccionToAttach : direccionCollectionNew) {
                direccionCollectionNewDireccionToAttach = em.getReference(direccionCollectionNewDireccionToAttach.getClass(), direccionCollectionNewDireccionToAttach.getIdDireccion());
                attachedDireccionCollectionNew.add(direccionCollectionNewDireccionToAttach);
            }
            direccionCollectionNew = attachedDireccionCollectionNew;
            cliente.setDireccionCollection(direccionCollectionNew);
            cliente = em.merge(cliente);
            for (Direccion direccionCollectionNewDireccion : direccionCollectionNew) {
                if (!direccionCollectionOld.contains(direccionCollectionNewDireccion)) {
                    Cliente oldPkClienteOfDireccionCollectionNewDireccion = direccionCollectionNewDireccion.getPkCliente();
                    direccionCollectionNewDireccion.setPkCliente(cliente);
                    direccionCollectionNewDireccion = em.merge(direccionCollectionNewDireccion);
                    if (oldPkClienteOfDireccionCollectionNewDireccion != null && !oldPkClienteOfDireccionCollectionNewDireccion.equals(cliente)) {
                        oldPkClienteOfDireccionCollectionNewDireccion.getDireccionCollection().remove(direccionCollectionNewDireccion);
                        oldPkClienteOfDireccionCollectionNewDireccion = em.merge(oldPkClienteOfDireccionCollectionNewDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdClente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdClente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Direccion> direccionCollectionOrphanCheck = cliente.getDireccionCollection();
            for (Direccion direccionCollectionOrphanCheckDireccion : direccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Direccion " + direccionCollectionOrphanCheckDireccion + " in its direccionCollection field has a non-nullable pkCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
