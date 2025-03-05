package com.elearn.journey.start_learning.Controller;

import com.elearn.journey.start_learning.DTO.CategoryDto;
import com.elearn.journey.start_learning.DTO.VideoDto;
import com.elearn.journey.start_learning.Exception.Util.CustomMessages;
import com.elearn.journey.start_learning.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @GetMapping
    public ResponseEntity<List<VideoDto>> getAllVideos(){
        return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomMessages> addVideo(@RequestBody VideoDto videoDto){
        try{
            videoService.addVideo(videoDto);
            return  new ResponseEntity<>(new CustomMessages("Video added",true), HttpStatus.CREATED);
        } catch (RuntimeException e){
            return new ResponseEntity<>(new CustomMessages(e.getMessage(),false),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping({"/title"})
    public ResponseEntity<VideoDto> getCategory(@RequestParam("title") String title) {
        return new ResponseEntity<>(videoService.searchByTitle(title),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<VideoDto> updateCategory(@RequestBody VideoDto dto){
        return  new ResponseEntity<>(
                videoService.updateVideo(dto),HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<CustomMessages> deleteCategory(@RequestParam(value = "title") String title){
        try{
            videoService.deleteVideo(title);
            return new ResponseEntity<>(new CustomMessages("deleted",true), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(new CustomMessages(e.getMessage(),false),HttpStatus.BAD_REQUEST);
        }

    }
}
