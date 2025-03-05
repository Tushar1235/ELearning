package com.elearn.journey.start_learning.Service.Impl;

import com.elearn.journey.start_learning.DTO.CategoryDto;
import com.elearn.journey.start_learning.DTO.CourseDto;
import com.elearn.journey.start_learning.Entities.Category;
import com.elearn.journey.start_learning.Entities.Course;
import com.elearn.journey.start_learning.Entities.Video;
import com.elearn.journey.start_learning.Exception.ResourceNotFoundException;
import com.elearn.journey.start_learning.Repositories.CourseRepository;
import com.elearn.journey.start_learning.Repositories.VideoRepository;
import com.elearn.journey.start_learning.Service.CourseService;
import com.elearn.journey.start_learning.Service.util.CustomPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CustomPageResponse getAllCourses(int pageNumber, int pageSize) {
        Sort sort = Sort.by("title").ascending();
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, sort);
        Page<Course> pageCourse = courseRepository.findAll(pageRequest);
        List<Course> courses = pageCourse.getContent();
        List<CourseDto> courseDtos = courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
        CustomPageResponse customPageResponse = new CustomPageResponse();
        customPageResponse.setPageNumber(pageCourse.getNumber());
        customPageResponse.setPageSize(pageCourse.getSize());
        customPageResponse.setTotalElements(pageCourse.getTotalElements());
        customPageResponse.setLast(pageCourse.isLast());
        customPageResponse.setTotalPages(pageCourse.getTotalPages());
        customPageResponse.setCourses(courseDtos);
        return customPageResponse;
    }

    @Override
    public void createCourses(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        courseRepository.save(course);
    }

    @Override
    public CourseDto getCourse(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course Not Found"));
        CourseDto courseDto =modelMapper.map(course, CourseDto.class);
        return courseDto;
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        courseRepository.save(course);
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    public void deleteCourse(String title) {
        Course course = courseRepository.findByTitle(title).orElseThrow(()-> new ResourceNotFoundException("Course Not Found"));
        courseRepository.delete(course);
    }

    @Override
    public CourseDto searchByTitle(String title) {
        Course course = courseRepository.findByTitle(title).orElseThrow(()-> new ResourceNotFoundException("Course Not Found"));
        return  modelMapper.map(course, CourseDto.class);
    }
    @Override
    public CourseDto addVideoToCourse(int courseId, int videoId) {

        // Fetch Course (Fixing the incorrect ID usage)
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found with ID: " + courseId));
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found with ID: " + videoId));

        // Associate the course with category
        course.getVideos().add(video); // Fix: Ensure Course is correctly linked to Category
        video.setCourse(course); // Add to category's list

        // Save both course and category
        courseRepository.save(course);  // Save course first to avoid transient issues
        videoRepository.save(video);

        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public CourseDto removeVideoFromCourse(int courseId, int videoId) {

        // Fetch Course and Video
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found with ID: " + courseId));
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video Not Found with ID: " + videoId));

        // Break the relationship
        if (course.getVideos().contains(video)) {
            course.getVideos().remove(video); // Remove from course's list
            video.setCourse(null); // Unlink the video
            videoRepository.save(video); // Update video in DB
        } else {
            throw new ResourceNotFoundException("Video is not linked to this Course.");
        }

        // Save the updated course
        courseRepository.save(course);

        return modelMapper.map(course, CourseDto.class);
    }
}
