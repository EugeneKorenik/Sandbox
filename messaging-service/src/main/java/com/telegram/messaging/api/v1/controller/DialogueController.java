package com.telegram.messaging.api.v1.controller;

import com.telegram.messaging.api.v1.dto.DialogueDto;
import com.telegram.messaging.api.v1.mapper.DialogueMapper;
import com.telegram.messaging.entity.Dialogue;
import com.telegram.messaging.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/dialogues",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class DialogueController {

    private final DialogueService dialogueService;
    private final DialogueMapper dialogueMapper;

    @Autowired
    public DialogueController(DialogueService dialogueService,
                              DialogueMapper dialogueMapper) {
        this.dialogueService = dialogueService;
        this.dialogueMapper = dialogueMapper;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<DialogueDto>> findAllByAccountId(@PathVariable("accountId") UUID accountId) {
        List<Dialogue> dialogues = dialogueService.findAllByAccountId(accountId);
        List<DialogueDto> dialoguesDto = dialogueMapper.toDto(dialogues);
        return ResponseEntity.ok(dialoguesDto);
    }

    @DeleteMapping("/{dialogueId}")
    public ResponseEntity<Void> deleteDialogue(@PathVariable("dialogueId") UUID dialogueId) {
        dialogueService.deleteForAll(dialogueId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteForAccount(@RequestParam("dialogueId") UUID dialogueId,
                                                 @RequestParam("accountId") UUID accountId) {
        dialogueService.deleteForWatcher(dialogueId, accountId);
        return ResponseEntity.noContent().build();
    }

}
