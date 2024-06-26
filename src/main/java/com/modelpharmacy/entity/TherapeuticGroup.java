package com.modelpharmacy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapeuticGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genericName;
    private String brandName;
    private String companyName;
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "therapeuticGroup")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DosageForm> dosageForm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tc_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TherapeuticClass therapeuticClass;

}
