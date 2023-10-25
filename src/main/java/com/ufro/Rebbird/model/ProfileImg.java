package com.ufro.Rebbird.model;

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
@Table(name = "img_perfil")
public class ProfileImg {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_perfil_id")
    private int id;

    @Column(name = "link_img", nullable = false, length = 70)
    private String link;
}
