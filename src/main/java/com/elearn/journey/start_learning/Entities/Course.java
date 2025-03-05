package com.elearn.journey.start_learning.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id")
    private int id;
    private String title;
    private String shortDesc;
    private String longDesc;
    private long price;
    @Column(nullable = false)
    private boolean isLive;
    private String banner;
    private Date date;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true) // Allow null initially
    private Category category;


}
