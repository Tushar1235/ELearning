package com.elearn.journey.start_learning.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class Video implements Serializable {
    @Id
    @Column(name = "video_id")
    private int id;
    private String title;
    @Column(name = "description")
    private String desc;
    private String filePath;
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
