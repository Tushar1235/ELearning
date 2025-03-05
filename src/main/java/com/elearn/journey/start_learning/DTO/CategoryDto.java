package com.elearn.journey.start_learning.DTO;

import com.elearn.journey.start_learning.Entities.Course;
import com.elearn.journey.start_learning.Service.CourseService;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int id;
    @NotEmpty(message = "title is required")
    private String title;
    private Date createdDate;

    List<CourseDto> courses = new ArrayList<>();
}
