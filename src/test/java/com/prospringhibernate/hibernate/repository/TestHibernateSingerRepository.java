package com.prospringhibernate.hibernate.repository;

import com.prospringhibernate.hibernate.config.HibernateConfig;
import com.prospringhibernate.hibernate.domain.Album;
import com.prospringhibernate.hibernate.domain.Instrument;
import com.prospringhibernate.hibernate.domain.Singer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 10:04
 **/



public class TestHibernateSingerRepository {

    ApplicationContext c;
    SingerRepository singerRepository;

    @Before
    public void setup() {
        c = new AnnotationConfigApplicationContext(HibernateConfig.class);
        singerRepository = c.getBean(SingerRepository.class);
    }

    @Test
    public void save() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate((new GregorianCalendar(1940, 8, 16).getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate((new GregorianCalendar(1961, 7, 18)).getTime());
        singer.addAlbum(album);

        singerRepository.save(singer);
        assertNotNull(singer.getId());

        Singer _singer = singerRepository.findById(singer.getId());
        assertNotNull(singer.getAlbums());
        assertEquals(singer.getAlbums().size(), 1);

         _singer.removeAlbum(album);
        singerRepository.save(_singer);
        _singer = singerRepository.findById(singer.getId());
        assertNotNull(_singer.getAlbums());
        assertEquals(_singer.getAlbums().size(), 0);

        Instrument instrument = new Instrument();
        instrument.setId("Violen");
        _singer.setInstruments(new HashSet<>(Arrays.asList(instrument)));
        singerRepository.save(_singer);
        _singer = singerRepository.findById(_singer.getId());
        assertEquals(_singer.getInstruments().size(),1);
        System.out.println(_singer.getInstruments());
    }

    @Test
    @Transactional
    public void findById() {
        Singer singer = singerRepository.findById(1l);
        assertEquals(singer.getFirstName(),"Siwoo");
    }
    @Transactional
    @Test
    public void findAll() {
        System.out.println(singerRepository.findAll());
        singerRepository.findAllWithAlbum().forEach(singer -> {
            System.out.println(singer);
            System.out.println("Albums ->");
            singer.getAlbums().forEach(album -> {
                System.out.println(album);
            });
        });
        assertNotNull(singerRepository.findAll());
        assertNotNull(singerRepository.findAllWithAlbum());

    }
}
