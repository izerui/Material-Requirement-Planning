package com.ierp2.mrp.configuration.jpa.factory;

import com.ierp2.mrp.configuration.jpa.PlatformJpaRepository;
import com.ierp2.mrp.configuration.jpa.impl.PlatformRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by serv on 14-5-29.
 */
public class PlatformJpaRepositoryFactoryBean<T extends JpaRepository<S,ID>,S,ID extends Serializable> extends JpaRepositoryFactoryBean<T,S,ID> {


    public PlatformJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new PlatformRepositoryFactory(entityManager);
    }


    private static class PlatformRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public PlatformRepositoryFactory(EntityManager entityManager) {
            super(entityManager);

            this.entityManager = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {

            PlatformRepositoryImpl platformRepository = new PlatformRepositoryImpl((Class<T>) information.getDomainType(), entityManager);

            return platformRepository;
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return PlatformJpaRepository.class;
        }
    }


}
