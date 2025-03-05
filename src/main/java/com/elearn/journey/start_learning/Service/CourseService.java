package com.elearn.journey.start_learning.Service;

import com.elearn.journey.start_learning.DTO.CourseDto;
import com.elearn.journey.start_learning.Service.util.CustomPageResponse;

import java.util.List;

public interface CourseService {
    public CustomPageResponse getAllCourses(int pageNumber, int pageSize);
    public void createCourses(CourseDto courseDto);
    public CourseDto getCourse(int courseId);
    public CourseDto updateCourse(CourseDto courseDto);
    public void deleteCourse(String title);
    public CourseDto searchByTitle(String title);
    public CourseDto addVideoToCourse(int courseId, int videoId);
    public CourseDto removeVideoFromCourse(int courseId, int videoId);

}
