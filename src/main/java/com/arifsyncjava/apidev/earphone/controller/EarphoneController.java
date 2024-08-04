package com.arifsyncjava.apidev.earphone.controller;

import com.arifsyncjava.apidev.earphone.model.Earphone;
import com.arifsyncjava.apidev.earphone.request.CreateRequest;
import com.arifsyncjava.apidev.earphone.request.UpdateRequest;
import com.arifsyncjava.apidev.earphone.request.UpdateRequestBody;
import com.arifsyncjava.apidev.earphone.service.CreateEarphoneService;
import com.arifsyncjava.apidev.earphone.service.DeleteEarphoneService;
import com.arifsyncjava.apidev.earphone.service.GetEarphoneService;
import com.arifsyncjava.apidev.earphone.service.UpdateEarphoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "/earphones")
public class EarphoneController {

    private final GetEarphoneService getEarphoneService;
    private final CreateEarphoneService createEarphoneService;
    private final UpdateEarphoneService updateEarphoneService;
    private final DeleteEarphoneService deleteEarphoneService;


    public EarphoneController(GetEarphoneService getEarphoneService, CreateEarphoneService createEarphoneService, UpdateEarphoneService updateEarphoneService, DeleteEarphoneService deleteEarphoneService) {
        this.getEarphoneService = getEarphoneService;
        this.createEarphoneService = createEarphoneService;
        this.updateEarphoneService = updateEarphoneService;
        this.deleteEarphoneService = deleteEarphoneService;
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<Earphone> read ( @PathVariable Long id) {
             return getEarphoneService.execute(id);
    }

    @PostMapping
    public ResponseEntity<Earphone> create (@RequestBody @Valid CreateRequest request) {
        return createEarphoneService.execute(request);
    }

    @PutMapping (path = "/{id}")
    public ResponseEntity<Earphone> update (
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestBody request) {
        return updateEarphoneService.execute(new UpdateRequest(id, request));
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id ) {
        return deleteEarphoneService.execute(id);
    }


















}
