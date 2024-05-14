/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import BD.exceptions.IllegalOrphanException;
import BD.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Entrega;
import Clases.Estado;
import Clases.Paquete;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sofiv
 */
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getEstadoCollection() == null) {
            paquete.setEstadoCollection(new ArrayList<Estado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega pkEntrega = paquete.getPkEntrega();
            if (pkEntrega != null) {
                pkEntrega = em.getReference(pkEntrega.getClass(), pkEntrega.getIdEntrega());
                paquete.setPkEntrega(pkEntrega);
            }
            Collection<Estado> attachedEstadoCollection = new ArrayList<Estado>();
            for (Estado estadoCollectionEstadoToAttach : paquete.getEstadoCollection()) {
                estadoCollectionEstadoToAttach = em.getReference(estadoCollectionEstadoToAttach.getClass(), estadoCollectionEstadoToAttach.getIdEstado());
                attachedEstadoCollection.add(estadoCollectionEstadoToAttach);
            }
            paquete.setEstadoCollection(attachedEstadoCollection);
            em.persist(paquete);
            if (pkEntrega != null) {
                pkEntrega.getPaqueteCollection().add(paquete);
                pkEntrega = em.merge(pkEntrega);
            }
            for (Estado estadoCollectionEstado : paquete.getEstadoCollection()) {
                Paquete oldPkPaqueteOfEstadoCollectionEstado = estadoCollectionEstado.getPkPaquete();
                estadoCollectionEstado.setPkPaquete(paquete);
                estadoCollectionEstado = em.merge(estadoCollectionEstado);
                if (oldPkPaqueteOfEstadoCollectionEstado != null) {
                    oldPkPaqueteOfEstadoCollectionEstado.getEstadoCollection().remove(estadoCollectionEstado);
                    oldPkPaqueteOfEstadoCollectionEstado = em.merge(oldPkPaqueteOfEstadoCollectionEstado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getIdPaquete());
            Entrega pkEntregaOld = persistentPaquete.getPkEntrega();
            Entrega pkEntregaNew = paquete.getPkEntrega();
            Collection<Estado> estadoCollectionOld = persistentPaquete.getEstadoCollection();
            Collection<Estado> estadoCollectionNew = paquete.getEstadoCollection();
            List<String> illegalOrphanMessages = null;
            for (Estado estadoCollectionOldEstado : estadoCollectionOld) {
                if (!estadoCollectionNew.contains(estadoCollectionOldEstado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estado " + estadoCollectionOldEstado + " since its pkPaquete field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pkEntregaNew != null) {
                pkEntregaNew = em.getReference(pkEntregaNew.getClass(), pkEntregaNew.getIdEntrega());
                paquete.setPkEntrega(pkEntregaNew);
            }
            Collection<Estado> attachedEstadoCollectionNew = new ArrayList<Estado>();
            for (Estado estadoCollectionNewEstadoToAttach : estadoCollectionNew) {
                estadoCollectionNewEstadoToAttach = em.getReference(estadoCollectionNewEstadoToAttach.getClass(), estadoCollectionNewEstadoToAttach.getIdEstado());
                attachedEstadoCollectionNew.add(estadoCollectionNewEstadoToAttach);
            }
            estadoCollectionNew = attachedEstadoCollectionNew;
            paquete.setEstadoCollection(estadoCollectionNew);
            paquete = em.merge(paquete);
            if (pkEntregaOld != null && !pkEntregaOld.equals(pkEntregaNew)) {
                pkEntregaOld.getPaqueteCollection().remove(paquete);
                pkEntregaOld = em.merge(pkEntregaOld);
            }
            if (pkEntregaNew != null && !pkEntregaNew.equals(pkEntregaOld)) {
                pkEntregaNew.getPaqueteCollection().add(paquete);
                pkEntregaNew = em.merge(pkEntregaNew);
            }
            for (Estado estadoCollectionNewEstado : estadoCollectionNew) {
                if (!estadoCollectionOld.contains(estadoCollectionNewEstado)) {
                    Paquete oldPkPaqueteOfEstadoCollectionNewEstado = estadoCollectionNewEstado.getPkPaquete();
                    estadoCollectionNewEstado.setPkPaquete(paquete);
                    estadoCollectionNewEstado = em.merge(estadoCollectionNewEstado);
                    if (oldPkPaqueteOfEstadoCollectionNewEstado != null && !oldPkPaqueteOfEstadoCollectionNewEstado.equals(paquete)) {
                        oldPkPaqueteOfEstadoCollectionNewEstado.getEstadoCollection().remove(estadoCollectionNewEstado);
                        oldPkPaqueteOfEstadoCollectionNewEstado = em.merge(oldPkPaqueteOfEstadoCollectionNewEstado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paquete.getIdPaquete();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
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
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getIdPaquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Estado> estadoCollectionOrphanCheck = paquete.getEstadoCollection();
            for (Estado estadoCollectionOrphanCheckEstado : estadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquete (" + paquete + ") cannot be destroyed since the Estado " + estadoCollectionOrphanCheckEstado + " in its estadoCollection field has a non-nullable pkPaquete field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Entrega pkEntrega = paquete.getPkEntrega();
            if (pkEntrega != null) {
                pkEntrega.getPaqueteCollection().remove(paquete);
                pkEntrega = em.merge(pkEntrega);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
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

    public Paquete findPaquete(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
