package me.rekii.tacocloud.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import me.rekii.tacocloud.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    @SuppressWarnings("unchecked")
    TacoOrder save(TacoOrder order);

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);
}