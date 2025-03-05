package com.elearn.journey.start_learning.Controller;

import com.elearn.journey.start_learning.DTO.CategoryDto;
import com.elearn.journey.start_learning.Exception.Util.CustomMessages;
import com.elearn.journey.start_learning.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(service.getAllCategory(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomMessages> addCategory(@RequestBody CategoryDto categoryDto){
        service.createCategory(categoryDto);
        return  new ResponseEntity<>(new CustomMessages("Category added",true), HttpStatus.CREATED);
    }

    @GetMapping({"/title"})
    public ResponseEntity<CategoryDto> getCategory(@RequestParam("title") String title) {
        return new ResponseEntity<>(service.searchByTitle(title),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto){
        return  new ResponseEntity<>(
                service.updateCategory(dto),HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<CustomMessages> deleteCategory(@RequestParam(value = "title") String title){
        try{
            service.deleteCategory(title);
            return new ResponseEntity<>(new CustomMessages("deleted",true), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(new CustomMessages(e.getMessage(),false),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<CategoryDto> addCourseToCategory(@PathVariable int categoryId,
                                                              @PathVariable int courseId){
        return new ResponseEntity<>(service.addCourseToCategory(categoryId,courseId), HttpStatus.OK);
    }

}
