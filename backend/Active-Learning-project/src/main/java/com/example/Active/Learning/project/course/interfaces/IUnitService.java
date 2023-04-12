package com.example.Active.Learning.project.course.interfaces;

import com.example.Active.Learning.project.course.dto.request.unit.UnitRequest;
import com.example.Active.Learning.project.course.dto.response.unit.UnitResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface IUnitService {
    void saveUnit(@NonNull UnitRequest unitRequest);
    List<UnitResponse> findAllUnits();
    UnitResponse findByName(@NonNull String unitName);
    UnitResponse findUnitById(@NonNull UUID unitId);
    UnitResponse updateUnit(@NonNull UUID unitId, @NonNull UnitRequest unitRequest);
    void deleteById(@NonNull UUID unitId);
    void delete(@NonNull UnitRequest unitRequest);

}
