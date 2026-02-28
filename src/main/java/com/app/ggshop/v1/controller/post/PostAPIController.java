package com.app.ggshop.v1.controller.post;

import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.service.EvChargerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/**")
@RequiredArgsConstructor
@Slf4j
public class PostAPIController {
//    private final PostService postService;
    private final EvChargerService evChargerService;

    @GetMapping("list/{page}")
    public PostWithPagingDTO list(@PathVariable int page){
        return evChargerService.list(page);
    }
}