package com.app.notifications.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_details", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name"}, name = "first_name_unique")})
@Getter
@Setter
public class UserDetails extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "dob")
    private String dob;

    @NotNull
    @Column(name = "doj")
    private String doj;

    @NotNull
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    @Column(name = "contact_no")
    private String contactNo;

}
