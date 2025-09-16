package com.example.cost.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double champ1;
    private Double champ2;
    private Double champ3;

    private Double total;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Double getChamp1() {
        return champ1;
    }

    public void setChamp1(Double champ1) {
        this.champ1 = champ1;
    }

    public Double getChamp2() {
        return champ2;
    }

    public void setChamp2(Double champ2) {
        this.champ2 = champ2;
    }

    public Double getChamp3() {
        return champ3;
    }

    public void setChamp3(Double champ3) {
        this.champ3 = champ3;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}