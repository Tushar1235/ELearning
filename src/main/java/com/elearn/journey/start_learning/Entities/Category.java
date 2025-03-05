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
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @Column(name = "category_id")
    private int id;
    private String title;
    private Date createdDate;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // Fix: match the field name in Course
    private List<Course> courses = new ArrayList<>();


    public void addCourseToCategory(Course course, Category category){
       courses.add(course);
    }

}
