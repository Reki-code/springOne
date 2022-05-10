package me.rekii.tacocloud.data;

import org.springframework.data.repository.CrudRepository;

import me.rekii.tacocloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
