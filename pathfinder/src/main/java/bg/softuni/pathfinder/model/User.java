package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private Level level;

    public User() {
        this.roles = new HashSet<>();
    }

    public Long getId () {

        return id;
    }

    public User setId (Long id) {

        this.id = id;
        return this;
    }

    public String getUsername () {

        return username;
    }

    public User setUsername (String username) {

        this.username = username;
        return this;
    }

    public String getPassword () {

        return password;
    }

    public User setPassword (String password) {

        this.password = password;
        return this;
    }

    public String getFullName () {

        return fullName;
    }

    public User setFullName (String fullName) {

        this.fullName = fullName;
        return this;
    }

    public Integer getAge () {

        return age;
    }

    public User setAge (Integer age) {

        this.age = age;
        return this;
    }

    public String getEmail () {

        return email;
    }

    public User setEmail (String email) {

        this.email = email;
        return this;
    }

    public Set<Role> getRoles () {

        return roles;
    }

    public User setRoles (Set<Role> roles) {

        this.roles = roles;
        return this;
    }

    public Level getLevel () {

        return level;
    }

    public User setLevel (Level level) {

        this.level = level;
        return this;
    }
}

