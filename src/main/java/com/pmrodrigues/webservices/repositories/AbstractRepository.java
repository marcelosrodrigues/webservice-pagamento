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
            String databaseUrl = System.getenv("DATABASE_URL");
            StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
            String jdbc = getJDBCURL(databaseUrl);
            String dbVendor = st.nextToken(); //if DATABASE_URL is set
            String userName = st.nextToken();
            String password = st.nextToken();

            Map<String,String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.url", databaseUrl );
            properties.put("javax.persistence.jdbc.user", userName );
            properties.put("javax.persistence.jdbc.password", password );
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default", properties);
        }

        private static String getJDBCURL(String databaseUrl) {
            StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
            String host = st.nextToken();
            String port = st.nextToken();
            String databaseName = st.nextToken();
            return String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
        }



}
