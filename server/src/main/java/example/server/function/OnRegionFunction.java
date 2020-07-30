package example.server.function;

import example.domain.Trade;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.distributed.DistributedSystem;
import org.apache.geode.distributed.internal.DistributionMessageObserver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class OnRegionFunction implements Function {

  private static final Random random = new Random();

  public OnRegionFunction() {
    DistributionMessageObserver.setInstance(new FunctionDistributionMessageObserver(CacheFactory.getAnyInstance()));
  }

  @Override
  public void execute(FunctionContext context) {
    // Get the filter
    RegionFunctionContext rfc = (RegionFunctionContext) context;
    Set<?> keys = rfc.getFilter();
    if (keys == null) {
      // No filter case. Get a random local key.
      keys = Collections.singleton(PartitionRegionHelper.getLocalDataForContext(rfc).keySet().iterator().next());
    }
    Object[] arguments = (Object[]) context.getArguments();
    boolean unsharedResources = (Boolean) arguments[0];
    Region region = rfc.getDataSet();
    StringBuilder builder = new StringBuilder();
    builder
      .append("Executing function=")
      .append(getId())
      .append("; numKeys=")
      .append(keys.size())
      .append(" keys=")
      .append(keys)
      .append("; region=")
      .append(region.getFullPath())
      .append("; unsharedResources=")
      .append(unsharedResources)
      .append(" at ")
      .append(System.currentTimeMillis());
    context.getCache().getLogger().info(builder.toString());

    // Update the entries
    try {
      if (unsharedResources) {
        DistributedSystem.setThreadsSocketPolicy(false);
      }
      keys.forEach(key -> updateTrade(region, key));
    } finally {
      if (unsharedResources) {
        DistributedSystem.setThreadsSocketPolicy(true);
      }
    }

    // Send the response
    context.getCache().getLogger().info("Executing function=" + getId() + " completed at " + System.currentTimeMillis());
    context.getResultSender().lastResult(true);
  }

  private void updateTrade(Region region, Object key) {
    Trade trade = (Trade) region.get(key);
    if (trade != null) {
      trade.setShares(random.nextInt(100));
      trade.setPrice(new BigDecimal(BigInteger.valueOf(random.nextInt(100000)), 2));
      region.put(key, trade);
    }
  }

  @Override
  public String getId() {
    return getClass().getSimpleName();
  }

  @Override
  public boolean optimizeForWrite() {
    return true;
  }
}
