package com.elearn.journey.start_learning.DTO;

import com.elearn.journey.start_learning.Entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private int id;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;
}
