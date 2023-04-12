package com.example.Active.Learning.project.course.service.unit;

import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.course.dto.request.unit.UnitRequest;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.dto.response.unit.UnitResponse;
import com.example.Active.Learning.project.course.interfaces.IUnitService;
import com.example.Active.Learning.project.course.models.unit.Unit;
import com.example.Active.Learning.project.course.repositories.unit.UnitRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UnitServiceImpl extends BaseImpl<Unit,UUID> implements IUnitService {

    @Autowired
    private final UnitRepository unitRepository;

    private final ModelMapper modelMapper;

    public UnitServiceImpl(BaseRepository<Unit, UUID> baseRepository, UnitRepository unitRepository, ModelMapper modelMapper) {
        super(baseRepository);
        this.unitRepository = unitRepository;
        this.modelMapper = modelMapper;
    }

    private Unit findUnit(UUID unitId){
        return this.findById(unitId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format(MessageResponse.USER_NOT_FOUND +" "+ unitId)));
    }


    /*
    * TODO
    *
    * Think about this function, do we really need it?,
    * Do we create unit independent?, if so find a way to link it to course
    * Probably ask a user to send also the course id =)
    *
    * We can have many units with same name belonging to different course,
    * Think about it
    *
    * */
    @Override
    public void saveUnit(@NonNull UnitRequest unitRequest) {
        try {
            findByName(unitRequest.getName());
        }catch (ResponseStatusException e){
            Unit unit = this.modelMapper.map(unitRequest, Unit.class);
            this.save(unit);
            return;
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
        throw new RuntimeException(MessageResponse.UNIT_ALREADY_EXIST);
    }

    @Override
    public List<UnitResponse> findAllUnits() {
        return this.findAll().stream().map(unit -> this.modelMapper.map(unit,UnitResponse.class)).toList();
    }

    @Override
    public UnitResponse findByName(@NonNull String unitName) {
        Unit unit =this.unitRepository.findByName(unitName)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, String.format(MessageResponse.UNIT_NOT_FOUND  +" "+ unitName)));
        return this.modelMapper.map(unit, UnitResponse.class);
    }

    @Override
    public UnitResponse findUnitById(@NonNull UUID unitId) {
        try{
            return this.modelMapper.map(findUnit(unitId), UnitResponse.class);
        }catch (ResponseStatusException e){
            throw new RuntimeException(MessageResponse.NOT_FOUND);
        }
    }
    @Override
    public UnitResponse updateUnit(@NonNull UUID unitId, @NonNull UnitRequest unitRequest) {
        Unit unit = findUnit(unitId);
        mapUnit(unit, unitRequest);
        return this.modelMapper.map(this.update(unit), UnitResponse.class);
    }

    private void mapUnit(Unit unit, UnitRequest unitRequest) {
        unit.setName(unitRequest.getName());
        unit.setDescription(unitRequest.getDescription());
        unit.setContentUrl(unit.getContentUrl());
    }

    @Override
    public void deleteById(@NonNull UUID unitId) {
        try{
            findUnitById(unitId);
        }catch (ResponseStatusException e){
            throw new RuntimeException(e.getMessage());
        }
        this.unitRepository.deleteById(unitId);
    }

    @Override
    public void delete(@NonNull UnitRequest unitRequest) {
        UnitResponse unitResponse = this.findByName(unitRequest.getName());
        if(unitResponse == null){
            throw new RuntimeException(MessageResponse.UNIT_NOT_FOUND);
        }
        Optional<Unit> unit =this.findAll().stream().filter(u
                        -> u.getName().equals(unitResponse.getName()))
                .findFirst();
        unit.ifPresent(this::delete);
    }
}
