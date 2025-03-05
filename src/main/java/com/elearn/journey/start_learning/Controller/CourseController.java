package com.elearn.journey.start_learning.Controller;

import com.elearn.journey.start_learning.DTO.CategoryDto;
import com.elearn.journey.start_learning.DTO.CourseDto;
import com.elearn.journey.start_learning.Exception.Util.CustomMessages;
import com.elearn.journey.start_learning.Service.CourseService;
import com.elearn.journey.start_learning.Service.util.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<CustomPageResponse> getCourses(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber
            , @RequestParam(value = "pageSize", required = false,defaultValue = "10") int pageSize){
        return new ResponseEntity<>(service.getAllCourses(pageNumber,pageSize), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomMessages> addCourse(@Valid @RequestBody CourseDto courseDto) {
        //, BindingResult result)
        /*if(result.hasErrors()){
            return new ResponseEntity<>( new CustomMessages("invalid data", false), HttpStatus.BAD_REQUEST);
        }*/
        service.createCourses(courseDto);
        return new ResponseEntity<>( new CustomMessages("Course added", true), HttpStatus.CREATED);
    }

    @GetMapping("/{title}")
    public ResponseEntity<CourseDto> getCourse(@RequestParam String title) {
        return new ResponseEntity<>(service.searchByTitle(title), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto dto) {
        return new ResponseEntity<>(service.updateCourse(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{title}")
    public  ResponseEntity<CustomMessages> deleteCourse(@RequestParam String title) {
        service.deleteCourse(title);
        return new ResponseEntity<>( new CustomMessages("Course deleted", true), HttpStatus.OK);
    }
    @PostMapping("/{courseId}/courses/{videoId}")
    public ResponseEntity<CourseDto> addCourseToCategory(@PathVariable int courseId,
                                                           @PathVariable int videoId){
        return new ResponseEntity<>(service.addVideoToCourse(courseId,videoId), HttpStatus.OK);
    }
    @DeleteMapping("/{courseId}/courses/{videoId}")
    public ResponseEntity<CourseDto> removeVideoFromCourse(@PathVariable int courseId,
                                                         @PathVariable int videoId){
        return new ResponseEntity<>(service.removeVideoFromCourse(courseId,videoId), HttpStatus.OK);
    }
}
