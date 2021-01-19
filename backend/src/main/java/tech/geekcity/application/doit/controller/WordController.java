package tech.geekcity.application.doit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.geekcity.application.doit.entity.Word;
import tech.geekcity.application.doit.service.WordManagerService;

import java.util.List;

@RestController
@RequestMapping("/api/word")
public class WordController {
    private final WordManagerService wordManagerService;

    @Autowired
    public WordController(WordManagerService wordManagerService) {
        this.wordManagerService = wordManagerService;
    }

    @RequestMapping("/all")
    public Response all(@RequestParam("limit") int limit) {
        List<Word> taskList = wordManagerService.wordList(limit);
        return Response.Builder.newInstance()
                .data(taskList)
                .build();
    }

    @RequestMapping("/add")
    public Response add(
            @RequestParam("word") String word,
            @RequestParam("description") String description
    ) {
        Word wordAdded = wordManagerService.add(Word.Builder.newInstance()
                .word(word)
                .description(description)
                .build());
        return Response.Builder.newInstance()
                .data(wordAdded)
                .build();
    }
}
