package com.example.klaus404.expensemanager.model;

import com.example.klaus404.expensemanager.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;


    public void setName(String name) {
        this.name = name;
    }

    public ProductDto toDto(){
       return new ProductDto(this.id, this.user, this.name, this.value, this.quantity, this.description);
    }

}