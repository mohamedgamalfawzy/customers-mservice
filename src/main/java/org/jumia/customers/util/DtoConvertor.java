package org.jumia.customers.util;

import org.jumia.customers.dto.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoConvertor{

    @Autowired
    private ModelMapper modelMapper;

    public DtoConvertor(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }
    public <TEntity,DtoResult> DtoResult toDTO(TEntity entity,Class<DtoResult> outClass){
        return modelMapper.map(entity, outClass);
    }
    public <DtoInput,TEntity> TEntity toEntity(DtoInput dto,Class<TEntity> entity){
        return modelMapper.map(dto, entity);
    }
    public <T,V> Pagination<V> toPagination(Page<T> pageList, List<V> items){
        return new Pagination<V>(pageList.getNumber(),pageList.getSize(),pageList.getTotalPages(),(int)pageList.getTotalElements(),items);
    }
}
