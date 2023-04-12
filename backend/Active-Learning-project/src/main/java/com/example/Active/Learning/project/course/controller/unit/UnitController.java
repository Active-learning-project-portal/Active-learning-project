package com.example.Active.Learning.project.course.controller.unit;


import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.dto.request.unit.UnitRequest;
import com.example.Active.Learning.project.course.dto.response.unit.UnitResponse;
import com.example.Active.Learning.project.course.service.unit.UnitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPoints.UNITS)
@RequiredArgsConstructor
public class UnitController {

    private final UnitServiceImpl unitService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<UnitResponse>> getAllUnits(
    ) {
        return ResponseEntity.ok(unitService.findAllUnits());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getUnit(@PathVariable UUID id) {
        try {
            return ResponseEntity.accepted().body(unitService.findUnitById(id));
        }catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<?>  createUnit(@RequestBody UnitRequest unitRequest) {
        try {
            unitService.saveUnit(unitRequest);
            return ResponseEntity.accepted().body(MessageResponse.UNIT_CREATED_SUCCESSFUL);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateUnit(@PathVariable UUID id, @RequestBody UnitRequest unitRequest) {

        try {
            UnitResponse updatedUnit= unitService.updateUnit(id,unitRequest);
            return ResponseEntity.accepted().body(updatedUnit);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteUnitById(@PathVariable UUID id) {
        try{
            unitService.deleteById(id);
            return ResponseEntity.accepted().body(MessageResponse.UNIT_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteUnit(@RequestBody UnitRequest unitRequest) {
        try{
            unitService.delete(unitRequest);
            return ResponseEntity.accepted().body(MessageResponse.UNIT_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
