package example.client;

import example.client.service.TradeService;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.geode.boot.autoconfigure.ContinuousQueryAutoConfiguration;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(exclude = ContinuousQueryAutoConfiguration.class) // disable subscriptions
public class Client {

  @Autowired
  private TradeService service;

  public static void main(String[] args) {
    new SpringApplicationBuilder(Client.class)
      .build()
      .run(args);
  }

  @Bean
  ApplicationRunner runner() {
    return args -> {
      List<String> operations = args.getOptionValues("operation");
      String operation = operations.get(0);
      String parameter1 = (args.containsOption("parameter1")) ? args.getOptionValues("parameter1").get(0) : null;
      switch (operation) {
        case "load-regions":
          this.service.put(Integer.parseInt(parameter1));
          break;
        case "execute-on-server-function":
          this.service.executeOnServerFunction();
          break;
        case "execute-on-servers-function":
          this.service.executeOnServersFunction();
          break;
        case "execute-on-replicated-region-function":
          this.service.executeReplicatedRegionFunction(Collections.singleton(0),false);
          break;
        case "execute-on-replicated-region-function-unshared-resources":
          this.service.executeReplicatedRegionFunction(Collections.singleton(0),true);
          break;
        case "execute-on-partitioned-region-function-no-filter":
          this.service.executePartitionedRegionFunctionWithFilter(new HashSet<>(), false);
          break;
        case "execute-on-partitioned-region-function-no-filter-unshared-resources":
          this.service.executePartitionedRegionFunctionWithFilter(new HashSet<>(), true);
          break;
        case "execute-on-partitioned-region-function-one-filter":
          this.service.executePartitionedRegionFunctionWithFilter(Collections.singleton(0),false);
          break;
        case "execute-on-partitioned-region-function-one-filter-unshared-resources":
          this.service.executePartitionedRegionFunctionWithFilter(Collections.singleton(0),true);
          break;
        case "execute-on-partitioned-region-function-multiple-filters": {
          Set<Integer> keys = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toCollection(HashSet::new));
          this.service.executePartitionedRegionFunctionWithFilter(keys, false);
          break;
        }
        case "execute-on-partitioned-region-function-multiple-filters-unshared-resources": {
          Set<Integer> keys = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toCollection(HashSet::new));
          this.service.executePartitionedRegionFunctionWithFilter(keys, true);
          break;
        }
        case "execute-on-partitioned-region-function-multiple-filters-no-result": {
          Set<Integer> keys = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toCollection(HashSet::new));
          this.service.executePartitionedRegionFunctionWithFilterNoResult(keys, false);
          break;
        }
        case "execute-on-partitioned-region-function-multiple-filters-no-result-unshared-resources": {
          Set<Integer> keys = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toCollection(HashSet::new));
          this.service.executePartitionedRegionFunctionWithFilterNoResult(keys, true);
          break;
        }
    }};
  }

  @Bean("ReplicatedTrade")
  ClientRegionFactoryBean replicatedTradeRegion(GemFireCache cache) {
    ClientRegionFactoryBean<?, ?> regionFactory = new ClientRegionFactoryBean<>();
    regionFactory.setCache(cache);
    regionFactory.setShortcut(ClientRegionShortcut.PROXY);
    return regionFactory;
  }


  @Bean("PartitionedTrade")
  ClientRegionFactoryBean partitionedTradeRegion(GemFireCache cache) {
    ClientRegionFactoryBean<?, ?> regionFactory = new ClientRegionFactoryBean<>();
    regionFactory.setCache(cache);
    regionFactory.setShortcut(ClientRegionShortcut.PROXY);
    return regionFactory;
  }
}
