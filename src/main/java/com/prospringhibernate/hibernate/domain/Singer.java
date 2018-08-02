package com.prospringhibernate.hibernate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 11:15
 **/

@Getter @Setter @ToString(exclude = {"instruments","albums"})
@Entity
@NamedQueries({
        @NamedQuery(name = "Singer.findAllWithAssociations",
        query = "select distinct s from Singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i "),
        @NamedQuery(name = "Singer.findById",
        query = "select distinct s from Singer s " +
                "left join fetch s.albums a " +
                "left join fetch s.instruments i " +
                "where s.id = :id ")
})
public class Singer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
    @Version
    private int version;
    @OneToMany(mappedBy = "singer",
    orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "singer_instrument",
    joinColumns = @JoinColumn(name = "singer_id"),
    inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private Set<Instrument> instruments = new HashSet<>();

    public void removeAlbum(Album album) {
        albums.remove(album);
    }

    public void addAlbum(Album album) {
        albums.add(album);
        if(album.getSinger() != this) {
            album.setSinger(this);
        }
    }
}
