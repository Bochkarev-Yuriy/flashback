package ru.coc.flashback.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @TableGenerator(name = "user_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "user",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_entity_gen")
    @Column(name = "id")
    private Long id;

    @Size(min = 1)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 1)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "city")
    private String city;

    @Size(max = 50)
    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Size(min = 4, max = 32)
    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "last_visit")
    private Date lastVisitDate;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

}
