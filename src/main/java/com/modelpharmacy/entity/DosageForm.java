package com.modelpharmacy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DosageForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dosageFormName;
    private String description;
    private String packSize;
    private Integer packQuantity;
    private Integer unit;
    private Double ipPrice;
    private Double tpPrice;
    private Double mrpPrice;
    private String shelfLocation;

    @Column(name = "df_image", length = 5000)
    private byte[] dfImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tg_id", nullable = false)
    private TherapeuticGroup therapeuticGroup;
}
