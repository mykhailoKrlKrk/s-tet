package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE masters SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "masters")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "qualification")
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Column(name = "cover_image", nullable = false)
    private String coverImage;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "masters_services",
            joinColumns = @JoinColumn(name = "master_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services = new HashSet<>();
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public void addService(Service service) {
        this.services.add(service);
        service.getMasters().add(this);
    }
}
