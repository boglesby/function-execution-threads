package example.client.repository;

import example.domain.Trade;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.repository.GemfireRepository;

@Region("ReplicatedTrade")
public interface ReplicatedTradeRepository extends GemfireRepository<Trade, Integer> {
}