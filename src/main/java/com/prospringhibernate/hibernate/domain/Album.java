package com.prospringhibernate.hibernate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 11:21
 **/


@Getter @Setter @ToString
@EqualsAndHashCode(of = {"id","title"})
@Entity
public class Album {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;
    private String title;
    @Column(name = "release_date")
    private Date releaseDate;
    @Version
    private int version;
}
