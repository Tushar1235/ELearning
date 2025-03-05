package com.elearn.journey.start_learning.Service.Impl;

import com.elearn.journey.start_learning.DTO.VideoDto;
import com.elearn.journey.start_learning.Entities.Course;
import com.elearn.journey.start_learning.Entities.Video;
import com.elearn.journey.start_learning.Exception.ResourceNotFoundException;
import com.elearn.journey.start_learning.Repositories.CourseRepository;
import com.elearn.journey.start_learning.Repositories.VideoRepository;
import com.elearn.journey.start_learning.Service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VideoDto> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        List<VideoDto> videoDtos = videos.stream().map(video ->
             modelMapper.map(video, VideoDto.class)).collect(Collectors.toList());
        return videoDtos;
    }

    @Override
    public void addVideo(VideoDto videoDto) {

        Video video = modelMapper.map(videoDto, Video.class);
        // Ensure course is not null
        videoRepository.save(video);
    }

    @Override
    public VideoDto getVideo(int videoId) {
        return null;
    }

    @Override
    public VideoDto updateVideo(VideoDto videoDto) {
        Video video = videoRepository.findById(videoDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Video not found"));
        video = modelMapper.map(videoDto,Video.class);
        return modelMapper.map(video, VideoDto.class);

    }

    @Override
    public void deleteVideo(String title) {
        Video video = videoRepository.findByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("Video not found"));
        videoRepository.delete(video);
    }

    @Override
    public VideoDto searchByTitle(String title) {
        Video video = videoRepository.findByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("Video not found"));
        return modelMapper.map(video, VideoDto.class);
    }
}
