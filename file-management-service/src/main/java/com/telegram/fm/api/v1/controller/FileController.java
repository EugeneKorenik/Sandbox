package com.telegram.fm.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getFile( @PathVariable("id") UUID id )
    {
        return ResponseEntity.ok("This is answer");
    }

}
