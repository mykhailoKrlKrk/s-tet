package com.example.backend.controller;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comments  management", description = "Endpoints for comments")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000",
        "https://mykhailokrlkrk.github.io/s-tet/",
        "http://s-tet.byethost12.com/"},
        methods = {
                RequestMethod.GET,
                RequestMethod.DELETE,
                RequestMethod.PUT,
                RequestMethod.POST
        })
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Get all comments",
            description = "Get list of all available comments")
    public List<CommentDto> getAll() {
        return commentService.getAllComments();
    }

    @GetMapping("/{category}")
    @Operation(summary = "Get all comments by service",
            description = "Get list of all available comments by service")
    public List<CommentDto> getCommentsByCategory(@PathVariable String category) {
        return commentService.getCommentsByCategory(category);
    }

    @PostMapping("/{category}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create comment", description = "Create new comment in the DB")
    public CommentDto createService(@RequestBody @Valid CommentRequestDto requestDto,
                                    @PathVariable String category) {
        return commentService.create(requestDto, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete service", description = "Delete comment by specific id")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}
