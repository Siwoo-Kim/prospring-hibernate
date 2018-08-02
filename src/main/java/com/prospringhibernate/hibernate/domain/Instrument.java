package com.prospringhibernate.hibernate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 11:21
 **/

@Getter @Setter
@ToString(exclude = "singers")
@Entity
public class Instrument {

    @Id
    @Column(name = "instrument_id")
    private String id;

    @ManyToMany
    @JoinTable(name = "singer_instrument",
    joinColumns = @JoinColumn(name = "instrument_id"),
    inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private Set<Singer> singers = new HashSet<>();

}
