/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protese.jpa.interfaces;

import java.lang.reflect.ParameterizedType;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import protese.dao.conexao.ConexaoJPA;

/**
 * Dao generico para utilizacao do hibernate
 *
 * @author Filipe Oliveira
 * @param <T> classe referencia para o generics do java
 */
public class Dao<T extends Entidade> {

    private Class<T> typeOfT;

    public Dao() {
        this.typeOfT = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Retorna o controlador de entidades do sistema
     *
     * @return controlador de entidades do sistema
     */
    public EntityManager getEM() {
        //Criacao do controlador de entidades
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("InnManagerPU");
        //return factory.createEntityManager();
        return ConexaoJPA.getInstance();
    }

    /**
     * Salva a entidade passada como parametro<BR>
     * Caso e entidade já existir ela sera atualizada, caso não ela sera criada.
     *
     * @param t entidade a ser salva
     * @return retorna a entidade salva com as devidas alterações <BR> s(caso
     * seja criada, volta com a chave primaria)
     * @throws Exception Erro 1: erro ao salvar entidade
     */
    public T gravar(T t) throws Exception {
        EntityManager em = getEM();

        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        if (t.getId() == null || t.getId() == 0) {
            em.persist(t);
        } else if (!em.contains(t)) {
            if (em.find(t.getClass(), t.getId()) == null) {
                throw new Exception("Erro 1: erro ao salvar " + t.getClass());
            }
            t = em.merge(t);
        }
        em.getTransaction().commit();

        return t;
    }

    public void executar(Query query) throws Exception {
        EntityManager em = getEM();

        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }

        query.executeUpdate();

        em.getTransaction().commit();
    }

    /**
     * Consulta entidade da Class passada como parametro por id
     *
     * @param clazz Classe da entidade a ser pesquisada
     * @param id id da entidade a ser pesquisada
     * @return entidade
     * @throws Exception Erro 2: erro ao retornar entidade
     */
    public T consultarId(Class<T> clazz, Long id) throws Exception {
        EntityManager em = getEM();
        T t = null;

        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        t = em.find(typeOfT, id);

        return t;
    }

    protected Query createQuery(String sql) {
        Query query = getEM().createQuery(sql);
        query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        return query;
    }

    protected Query createNativeQuery(String sql) {
        Query query = getEM().createNativeQuery(sql);
        //query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return query;
    }

    protected Query createNativeQuery(String sql, Class clzz) {
        Query query = getEM().createNativeQuery(sql, clzz);
        //query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return query;
    }

}
