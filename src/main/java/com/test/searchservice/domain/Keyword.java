package com.test.searchservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tb_keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;
    @Column(nullable = false)
    private String keyword;
    @Column
    private Long cnt;
}
