package com.example.bootcamp.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role_name", unique = true)
    @NotBlank(message = "Название роли не может быть пустой!")
    @Size(max = 100, message = "Максимальная длина роли 100 символов!")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Volunteer> volunteers;


    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
