package me.rekii.tacocloud.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import me.rekii.tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    @SuppressWarnings("unchecked")
    Ingredient save(Ingredient ingredient);
}
