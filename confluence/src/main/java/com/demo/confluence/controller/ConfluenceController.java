package com.demo.confluence.controller;

import com.demo.confluence.service.ConfluenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/confluence")
public class ConfluenceController {

    private final ConfluenceService confluenceService;

    public ConfluenceController(ConfluenceService confluenceService) {
        this.confluenceService = confluenceService;
    }

    @GetMapping("/search")
    public Mono<String> searchConfluence(@RequestParam String query) {
        return confluenceService.searchContent(query);
    }

    @GetMapping("/space/{spaceKey}/content")
    public Mono<String> getSpaceContent(@PathVariable String spaceKey) {
        return confluenceService.getSpaceContent(spaceKey);
    }

    @GetMapping("/space/{spaceKey}/content-expanded")
    public Mono<String> getSpaceContentExpanded(@PathVariable String spaceKey) {
        return confluenceService.getSpaceContentWithExpansion(spaceKey);
    }

    @GetMapping("/page/{pageId}/content-expanded") // New endpoint
    public Mono<String> getPageContentExpanded(@PathVariable String pageId) {
        return confluenceService.getPageContentWithExpansion(pageId);
    }
}
