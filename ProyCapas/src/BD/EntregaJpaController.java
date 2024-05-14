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
import Clases.Empleado;
import Clases.Entrega;
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
public class EntregaJpaController implements Serializable {

    public EntregaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entrega entrega) {
        if (entrega.getPaqueteCollection() == null) {
            entrega.setPaqueteCollection(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado pkEmpleado = entrega.getPkEmpleado();
            if (pkEmpleado != null) {
                pkEmpleado = em.getReference(pkEmpleado.getClass(), pkEmpleado.getIdEmpleado());
                entrega.setPkEmpleado(pkEmpleado);
            }
            Collection<Paquete> attachedPaqueteCollection = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionPaqueteToAttach : entrega.getPaqueteCollection()) {
                paqueteCollectionPaqueteToAttach = em.getReference(paqueteCollectionPaqueteToAttach.getClass(), paqueteCollectionPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollection.add(paqueteCollectionPaqueteToAttach);
            }
            entrega.setPaqueteCollection(attachedPaqueteCollection);
            em.persist(entrega);
            if (pkEmpleado != null) {
                pkEmpleado.getEntregaCollection().add(entrega);
                pkEmpleado = em.merge(pkEmpleado);
            }
            for (Paquete paqueteCollectionPaquete : entrega.getPaqueteCollection()) {
                Entrega oldPkEntregaOfPaqueteCollectionPaquete = paqueteCollectionPaquete.getPkEntrega();
                paqueteCollectionPaquete.setPkEntrega(entrega);
                paqueteCollectionPaquete = em.merge(paqueteCollectionPaquete);
                if (oldPkEntregaOfPaqueteCollectionPaquete != null) {
                    oldPkEntregaOfPaqueteCollectionPaquete.getPaqueteCollection().remove(paqueteCollectionPaquete);
                    oldPkEntregaOfPaqueteCollectionPaquete = em.merge(oldPkEntregaOfPaqueteCollectionPaquete);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entrega entrega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega persistentEntrega = em.find(Entrega.class, entrega.getIdEntrega());
            Empleado pkEmpleadoOld = persistentEntrega.getPkEmpleado();
            Empleado pkEmpleadoNew = entrega.getPkEmpleado();
            Collection<Paquete> paqueteCollectionOld = persistentEntrega.getPaqueteCollection();
            Collection<Paquete> paqueteCollectionNew = entrega.getPaqueteCollection();
            List<String> illegalOrphanMessages = null;
            for (Paquete paqueteCollectionOldPaquete : paqueteCollectionOld) {
                if (!paqueteCollectionNew.contains(paqueteCollectionOldPaquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paquete " + paqueteCollectionOldPaquete + " since its pkEntrega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pkEmpleadoNew != null) {
                pkEmpleadoNew = em.getReference(pkEmpleadoNew.getClass(), pkEmpleadoNew.getIdEmpleado());
                entrega.setPkEmpleado(pkEmpleadoNew);
            }
            Collection<Paquete> attachedPaqueteCollectionNew = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionNewPaqueteToAttach : paqueteCollectionNew) {
                paqueteCollectionNewPaqueteToAttach = em.getReference(paqueteCollectionNewPaqueteToAttach.getClass(), paqueteCollectionNewPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollectionNew.add(paqueteCollectionNewPaqueteToAttach);
            }
            paqueteCollectionNew = attachedPaqueteCollectionNew;
            entrega.setPaqueteCollection(paqueteCollectionNew);
            entrega = em.merge(entrega);
            if (pkEmpleadoOld != null && !pkEmpleadoOld.equals(pkEmpleadoNew)) {
                pkEmpleadoOld.getEntregaCollection().remove(entrega);
                pkEmpleadoOld = em.merge(pkEmpleadoOld);
            }
            if (pkEmpleadoNew != null && !pkEmpleadoNew.equals(pkEmpleadoOld)) {
                pkEmpleadoNew.getEntregaCollection().add(entrega);
                pkEmpleadoNew = em.merge(pkEmpleadoNew);
            }
            for (Paquete paqueteCollectionNewPaquete : paqueteCollectionNew) {
                if (!paqueteCollectionOld.contains(paqueteCollectionNewPaquete)) {
                    Entrega oldPkEntregaOfPaqueteCollectionNewPaquete = paqueteCollectionNewPaquete.getPkEntrega();
                    paqueteCollectionNewPaquete.setPkEntrega(entrega);
                    paqueteCollectionNewPaquete = em.merge(paqueteCollectionNewPaquete);
                    if (oldPkEntregaOfPaqueteCollectionNewPaquete != null && !oldPkEntregaOfPaqueteCollectionNewPaquete.equals(entrega)) {
                        oldPkEntregaOfPaqueteCollectionNewPaquete.getPaqueteCollection().remove(paqueteCollectionNewPaquete);
                        oldPkEntregaOfPaqueteCollectionNewPaquete = em.merge(oldPkEntregaOfPaqueteCollectionNewPaquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entrega.getIdEntrega();
                if (findEntrega(id) == null) {
                    throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.");
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
            Entrega entrega;
            try {
                entrega = em.getReference(Entrega.class, id);
                entrega.getIdEntrega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Paquete> paqueteCollectionOrphanCheck = entrega.getPaqueteCollection();
            for (Paquete paqueteCollectionOrphanCheckPaquete : paqueteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entrega (" + entrega + ") cannot be destroyed since the Paquete " + paqueteCollectionOrphanCheckPaquete + " in its paqueteCollection field has a non-nullable pkEntrega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado pkEmpleado = entrega.getPkEmpleado();
            if (pkEmpleado != null) {
                pkEmpleado.getEntregaCollection().remove(entrega);
                pkEmpleado = em.merge(pkEmpleado);
            }
            em.remove(entrega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entrega> findEntregaEntities() {
        return findEntregaEntities(true, -1, -1);
    }

    public List<Entrega> findEntregaEntities(int maxResults, int firstResult) {
        return findEntregaEntities(false, maxResults, firstResult);
    }

    private List<Entrega> findEntregaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entrega.class));
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

    public Entrega findEntrega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entrega.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entrega> rt = cq.from(Entrega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
