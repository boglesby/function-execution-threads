package example.client.service;

import example.domain.CusipHelper;
import example.domain.Trade;
import example.client.function.OnServersFunctions;
import example.client.function.OnPartitionedRegionFunctions;
import example.client.function.OnReplicatedRegionFunctions;
import example.client.function.OnServerFunctions;
import example.client.repository.PartitionedTradeRepository;
import example.client.repository.ReplicatedTradeRepository;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TradeService {

  @Autowired
  private ReplicatedTradeRepository replicatedRepository;

  @Autowired
  private PartitionedTradeRepository partitionedRepository;

  @Autowired
  private OnServersFunctions onServersFunctions;

  @Autowired
  private OnServerFunctions onServerFunctions;

  @Autowired
  private OnReplicatedRegionFunctions onReplicatedRegionFunctions;

  @Autowired
  private OnPartitionedRegionFunctions onPartitionedRegionFunctions;

  @Autowired
  private GemFireCache cache;

  @Autowired
  @Qualifier("PartitionedTrade")
  private Region partitionedTradeRegion;

  private static final Random random = new Random();

  private static final Logger logger = LoggerFactory.getLogger(TradeService.class);

  public void put(int numEntries) {
    logger.info("Putting {} trades", numEntries);
    for (int i=0; i<numEntries; i++) {
      long createTime = System.currentTimeMillis();
      Trade trade = new Trade(i, CusipHelper.getCusip(), random.nextInt(100), new BigDecimal(BigInteger.valueOf(random.nextInt(100000)), 2));
      this.replicatedRepository.save(trade);
      this.partitionedRepository.save(trade);
      logger.info("Saved " + trade);
    }
  }

  public void executeOnServersFunction() {
    Object result = this.onServersFunctions.executeOnServersFunction();
    logger.info("Executed OnServersFunction result={}", result);
  }

  public void executeOnServerFunction() {
    Object result = this.onServerFunctions.executeOnServerFunction();
    logger.info("Executed OnServerFunction result={}", result);
  }

  public void executeReplicatedRegionFunction(Set<Integer> keys, boolean unsharedResources) {
    Object result = this.onReplicatedRegionFunctions.executeOnRegionFunction(keys, unsharedResources);
    logger.info("Executed replicated OnRegionFunction unsharedResources={}; result={}", unsharedResources, result);
  }

  public void executePartitionedRegionFunctionWithFilter(Set<Integer> keys, boolean unsharedResources) {
    dumpClientMetadata();
    Object result = this.onPartitionedRegionFunctions.executeOnRegionFunction(keys, unsharedResources);
    logger.info("Executed partitioned OnRegionFunction filter={}; unsharedResources={}; result={}", keys, unsharedResources, result);
  }

  public void executePartitionedRegionFunctionWithFilterNoResult(Set<Integer> keys, boolean unsharedResources) {
    dumpClientMetadata();
    Object result = this.onPartitionedRegionFunctions.executeOnRegionNoResultFunction(keys, unsharedResources);
    logger.info("Executed partitioned OnRegionNoResultFunction filter={}; unsharedResources={}; result={}", keys, unsharedResources, result);
  }

  private void dumpClientMetadata() {
    ClientMetadataHelper.printMetadata(this.cache, this.partitionedTradeRegion);
  }
}
