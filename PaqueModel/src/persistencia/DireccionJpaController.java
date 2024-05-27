/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Cliente;
import Clases.Direccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author omerb
 */
public class DireccionJpaController implements Serializable {

    public DireccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public DireccionJpaController() {
        emf = Persistence.createEntityManagerFactory("PaqueModelPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Direccion direccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = direccion.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getCedula());
                direccion.setCliente(cliente);
            }
            em.persist(direccion);
            if (cliente != null) {
                cliente.getDirecciones().add(direccion);
                cliente = em.merge(cliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Direccion direccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion persistentDireccion = em.find(Direccion.class, direccion.getCodigo());
            Cliente clienteOld = persistentDireccion.getCliente();
            Cliente clienteNew = direccion.getCliente();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getCedula());
                direccion.setCliente(clienteNew);
            }
            direccion = em.merge(direccion);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getDirecciones().remove(direccion);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getDirecciones().add(direccion);
                clienteNew = em.merge(clienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String codigo = direccion.getCodigo();
                if (findDireccion(codigo) == null) {
                    throw new NonexistentEntityException("The direccion with id " + codigo + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String codigo) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion direccion;
            try {
                direccion = em.getReference(Direccion.class, codigo);
                direccion.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccion with id " + codigo + " no longer exists.", enfe);
            }
            Cliente cliente = direccion.getCliente();
            if (cliente != null) {
                cliente.getDirecciones().remove(direccion);
                cliente = em.merge(cliente);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Direccion> findDireccionEntities() {
        return findDireccionEntities(true, -1, -1);
    }

    public List<Direccion> findDireccionEntities(int maxResults, int firstResult) {
        return findDireccionEntities(false, maxResults, firstResult);
    }

    private List<Direccion> findDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Direccion.class));
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

    public Direccion findDireccion(String codigo) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Direccion.class, codigo);
        } finally {
            em.close();
        }
    }

    public int getDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Direccion> rt = cq.from(Direccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void addDireccionCliente(Cliente cliente, Direccion nuevaDireccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Verificar que el cliente no sea nulo
            if (cliente == null) {
                throw new NonexistentEntityException("The cliente is null.");
            }

            // Asociar la nueva dirección con el cliente
            nuevaDireccion.setCliente(cliente);
            em.persist(nuevaDireccion);

            // Actualizar la lista de direcciones del cliente
            cliente.getDirecciones().add(nuevaDireccion);
            em.merge(cliente);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public ArrayList<Direccion> findDireccionesByCedulaCliente(Cliente cli) {
        EntityManager em = getEntityManager();
        ArrayList<Direccion> direccionesCliente = new ArrayList<>();
        try {
            // Consulta para obtener las direcciones de un cliente específico
            TypedQuery<Direccion> query = em.createQuery(
                "SELECT d FROM Direccion d WHERE d.cliente.cedula = :cedulaCliente", Direccion.class);
            query.setParameter("cedulaCliente", cli.getCedula());
            List<Direccion> direcciones = query.getResultList();
            direccionesCliente.addAll(direcciones);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return direccionesCliente;
    }

}
