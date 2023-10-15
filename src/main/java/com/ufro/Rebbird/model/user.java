package com.ufro.Rebbird.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.ufro.Rebbird.model.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre_usuario" }) })
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private int id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 15)
    private String username;

    @Column(name = "password_usuario", nullable = false, length = 255)
    private String password;

    @Column(name = "perfil_usuario_id", nullable = true)
    private Integer perfil;

    @ManyToOne
    @JoinColumn(name = "img_perfil_id", columnDefinition = "int default 1")
    private ProfileImg profileImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = true)
    private Role role;

    // TODO: Esta es la forma correcta de crear la relacion, pero da error por el
    // 'FetchType.LAZY'
    // el fix rapido es no colocarlo, en caso de error con las relaciones, revisar
    // aqui.

    // @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // private Set<Post> posts = new HashSet<>();

    // @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // private Set<Comment> comment = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setNombre(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}