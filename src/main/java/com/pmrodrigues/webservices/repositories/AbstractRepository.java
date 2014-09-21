package com.pmrodrigues.webservices.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Marceloo on 21/09/2014.
 */
public abstract class AbstractRepository<E> {


        private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

        private final Class<E> persistentClass; //NOPMD

        public AbstractRepository() {
            final ParameterizedType type = (ParameterizedType) this.getClass()
                    .getGenericSuperclass();
            this.persistentClass = (Class<E>) type.getActualTypeArguments()[0];
        }

        protected synchronized static final EntityManager getEntityManager() {

            if( ENTITY_MANAGER_FACTORY == null ){
                initialize();
            }
            return ENTITY_MANAGER_FACTORY.createEntityManager();
        }

        public void add(E e){
            EntityManager em = AbstractRepository.getEntityManager();
            em.persist(e);
        }

        private static void initialize() {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        }

}
