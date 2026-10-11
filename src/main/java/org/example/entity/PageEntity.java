package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "page")
@Data
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long uuid;

    String pageName;
}
