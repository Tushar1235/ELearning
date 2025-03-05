package com.elearn.journey.start_learning.DTO;

import com.elearn.journey.start_learning.Entities.Category;
import com.elearn.journey.start_learning.Entities.Video;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CourseDto {
    @NotNull(message="Id is required")
    private int id;
    @NotEmpty(message = "title  is required")
    @Size(min = 3, max = 50,message = "title must be between 3 and 50 characters")
    private String title;
    private String shortDesc;
    private String longDesc;
    private long price;
    private boolean isLive;
    private String banner;
    private Date date;
    private List<VideoDto> videos = new ArrayList<>();


}
