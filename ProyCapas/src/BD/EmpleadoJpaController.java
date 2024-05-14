/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import BD.exceptions.IllegalOrphanException;
import BD.exceptions.NonexistentEntityException;
import Clases.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Entrega;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sofiv
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getEntregaCollection() == null) {
            empleado.setEntregaCollection(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Entrega> attachedEntregaCollection = new ArrayList<Entrega>();
            for (Entrega entregaCollectionEntregaToAttach : empleado.getEntregaCollection()) {
                entregaCollectionEntregaToAttach = em.getReference(entregaCollectionEntregaToAttach.getClass(), entregaCollectionEntregaToAttach.getIdEntrega());
                attachedEntregaCollection.add(entregaCollectionEntregaToAttach);
            }
            empleado.setEntregaCollection(attachedEntregaCollection);
            em.persist(empleado);
            for (Entrega entregaCollectionEntrega : empleado.getEntregaCollection()) {
                Empleado oldPkEmpleadoOfEntregaCollectionEntrega = entregaCollectionEntrega.getPkEmpleado();
                entregaCollectionEntrega.setPkEmpleado(empleado);
                entregaCollectionEntrega = em.merge(entregaCollectionEntrega);
                if (oldPkEmpleadoOfEntregaCollectionEntrega != null) {
                    oldPkEmpleadoOfEntregaCollectionEntrega.getEntregaCollection().remove(entregaCollectionEntrega);
                    oldPkEmpleadoOfEntregaCollectionEntrega = em.merge(oldPkEmpleadoOfEntregaCollectionEntrega);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
            Collection<Entrega> entregaCollectionOld = persistentEmpleado.getEntregaCollection();
            Collection<Entrega> entregaCollectionNew = empleado.getEntregaCollection();
            List<String> illegalOrphanMessages = null;
            for (Entrega entregaCollectionOldEntrega : entregaCollectionOld) {
                if (!entregaCollectionNew.contains(entregaCollectionOldEntrega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Entrega " + entregaCollectionOldEntrega + " since its pkEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Entrega> attachedEntregaCollectionNew = new ArrayList<Entrega>();
            for (Entrega entregaCollectionNewEntregaToAttach : entregaCollectionNew) {
                entregaCollectionNewEntregaToAttach = em.getReference(entregaCollectionNewEntregaToAttach.getClass(), entregaCollectionNewEntregaToAttach.getIdEntrega());
                attachedEntregaCollectionNew.add(entregaCollectionNewEntregaToAttach);
            }
            entregaCollectionNew = attachedEntregaCollectionNew;
            empleado.setEntregaCollection(entregaCollectionNew);
            empleado = em.merge(empleado);
            for (Entrega entregaCollectionNewEntrega : entregaCollectionNew) {
                if (!entregaCollectionOld.contains(entregaCollectionNewEntrega)) {
                    Empleado oldPkEmpleadoOfEntregaCollectionNewEntrega = entregaCollectionNewEntrega.getPkEmpleado();
                    entregaCollectionNewEntrega.setPkEmpleado(empleado);
                    entregaCollectionNewEntrega = em.merge(entregaCollectionNewEntrega);
                    if (oldPkEmpleadoOfEntregaCollectionNewEntrega != null && !oldPkEmpleadoOfEntregaCollectionNewEntrega.equals(empleado)) {
                        oldPkEmpleadoOfEntregaCollectionNewEntrega.getEntregaCollection().remove(entregaCollectionNewEntrega);
                        oldPkEmpleadoOfEntregaCollectionNewEntrega = em.merge(oldPkEmpleadoOfEntregaCollectionNewEntrega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdEmpleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Entrega> entregaCollectionOrphanCheck = empleado.getEntregaCollection();
            for (Entrega entregaCollectionOrphanCheckEntrega : entregaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Entrega " + entregaCollectionOrphanCheckEntrega + " in its entregaCollection field has a non-nullable pkEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
