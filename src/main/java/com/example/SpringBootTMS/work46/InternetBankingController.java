package com.example.SpringBootTMS.work46;

import com.example.SpringBootTMS.work46.dto.CardDTO;
import com.example.SpringBootTMS.work46.dto.ClientDTO;
import com.example.SpringBootTMS.work46.dto.Message;
import com.example.SpringBootTMS.work46.dto.Remittance;
import com.example.SpringBootTMS.work46.model.Card;
import com.example.SpringBootTMS.work46.model.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/banking/")
public class InternetBankingController {
    @Autowired
    private InternetBankingService service;

    @GetMapping("get/inform/client")
    public ResponseEntity getClient(@RequestParam("id") Long id) {
        Client client =service.getInformationClient(id);
        if(client==null)
            return new ResponseEntity(new Message(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(client,HttpStatus.OK);
    }

    @GetMapping("get/inform/card")
    public ResponseEntity getCard(@RequestParam("id") Long id) {
        Card card =service.getInformationCard(id);
        if(card==null)
            return new ResponseEntity(new Message(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(card,HttpStatus.OK);
    }

    @PostMapping("insert/client")
    public Message insertClient(@RequestBody @Valid ClientDTO client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Message(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". ")));
        }
        return new Message(service.insertClient(client));
    }

    @PostMapping("insert/card")
    public Message insertCard(@RequestBody @Valid CardDTO card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Message(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". ")));
        }
        return new Message(service.insertCard(card));
    }

    @PostMapping("remittance")
    public Message makeRemittance(@RequestBody @Valid Remittance remittance, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Message(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". ")));
        }
        return new Message(service.makeRemittance(remittance));
    }
}
