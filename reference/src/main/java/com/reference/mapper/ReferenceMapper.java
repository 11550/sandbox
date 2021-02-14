package com.reference.mapper;

import com.reference.domain.Reference;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReferenceMapper {

    @Insert("insert into reference(id, name, abbreviation) values(#{id},#{name},#{abbreviation})")
    Integer insert(Reference reference);

    @Select("select * from reference WHERE id=#{id}")
    Reference findById(Long id);

    @Select("select * from reference")
    List<Reference> findAll();

    @Delete("delete from reference where id=#{id}")
    void delete(Long id);

    @Update("Update reference set name=#{name}, abbreviation=#{abbreviation} where id=#{id}")
    void edit(Reference reference);
}
