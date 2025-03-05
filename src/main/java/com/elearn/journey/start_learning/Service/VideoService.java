package com.elearn.journey.start_learning.Service;

import com.elearn.journey.start_learning.DTO.CourseDto;
import com.elearn.journey.start_learning.DTO.VideoDto;

import java.util.List;

public interface VideoService {
    public List<VideoDto> getAllVideos();
    public void addVideo(VideoDto VideoDto);
    public VideoDto getVideo(int videoId);
    public VideoDto updateVideo(VideoDto VideoDto);
    public void deleteVideo(String title);
    public VideoDto searchByTitle(String title);
}
