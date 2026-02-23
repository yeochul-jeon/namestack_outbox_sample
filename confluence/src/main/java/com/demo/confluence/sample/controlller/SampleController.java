package com.demo.confluence.sample.controlller;


import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO 테스트 컨트롤러
@Slf4j
@RestController
@RequestMapping("/api/page")
public class SampleController {

    // 샘플 문서 저장소 (예시)
    private static final Map<String, Map<String, String>> SAMPLE_PAGES = Map.of(
        "page1", Map.of(
            "id", "page1",
            "title", "Sample Page 1",
            "content", "This is the content of sample page 1."
        ),
        "page2", Map.of(
            "id", "page2",
            "title", "Sample Page 2",
            "content", "Content for the second sample page."
        )
    );

    // 페이지 ID로 문서 조회 API (GET /api/pages/{pageId})
    @GetMapping("/{pageId}")
    public ResponseEntity<?> getPageById(@PathVariable String pageId) {
        if (SAMPLE_PAGES.containsKey(pageId)) {
            return ResponseEntity.ok(SAMPLE_PAGES.get(pageId));
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Page not found"));
        }
    }
}
