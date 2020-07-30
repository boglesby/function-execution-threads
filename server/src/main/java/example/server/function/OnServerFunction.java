package example.server.function;

import example.domain.Trade;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Random;

public class OnServerFunction implements Function {

  private static final Random random = new Random();

  @Override
  public void execute(FunctionContext context) {
    context.getCache().getLogger().info("Executing function=" + getId() + " at " + System.currentTimeMillis());

    // Update a random entry in the replicated region to show which thread processes the replication on the remote member
    Region region = context.getCache().getRegion("ReplicatedTrade");
    updateTrade(region, region.keySet().iterator().next());

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
}
