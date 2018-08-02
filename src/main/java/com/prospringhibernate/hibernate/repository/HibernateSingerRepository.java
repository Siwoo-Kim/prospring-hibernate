package com.prospringhibernate.hibernate.repository;

import com.prospringhibernate.hibernate.domain.Singer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 11:27
 **/


@Transactional
@Repository
public class HibernateSingerRepository implements SingerRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("select s from Singer s")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum() {
        return sessionFactory
                .getCurrentSession()
                .getNamedQuery("Singer.findAllWithAssociations")
                .getResultList();
    }

    @Override
    public Singer findById(Long id) {
        return (Singer) sessionFactory
                .getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        System.out.println("Id genereated and inserting to object by Hiberante: " + singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {

    }
}
