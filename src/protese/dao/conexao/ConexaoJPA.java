package protese.dao.conexao;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Filipe
 */
public class ConexaoJPA {

    private static EntityManager uniqueInstance;

    private ConexaoJPA() {
    }

    public static synchronized EntityManager getInstance() {
        if (uniqueInstance == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("protesePU");
            uniqueInstance = factory.createEntityManager();
        }

        Cache cache = uniqueInstance.getEntityManagerFactory().getCache();
        cache.evictAll();

        return uniqueInstance;
    }

}
