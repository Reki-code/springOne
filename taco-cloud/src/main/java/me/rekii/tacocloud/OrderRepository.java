package me.rekii.tacocloud;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    @SuppressWarnings("unchecked")
    TacoOrder save(TacoOrder order);
}
