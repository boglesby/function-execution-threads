package example.server.function;

import org.apache.geode.cache.Cache;
import org.apache.geode.distributed.internal.ClusterDistributionManager;
import org.apache.geode.distributed.internal.DistributionMessage;
import org.apache.geode.distributed.internal.DistributionMessageObserver;

public class FunctionDistributionMessageObserver extends DistributionMessageObserver {

  private final Cache cache;

  public FunctionDistributionMessageObserver(Cache cache) {
    this.cache = cache;
  }

  public void beforeProcessMessage(ClusterDistributionManager dm, DistributionMessage message) {
    log(message, "About to process");
  }
  
  public void afterProcessMessage(ClusterDistributionManager dm, DistributionMessage message) {
    log(message, "Processed");
  }
  
  public void beforeSendMessage(ClusterDistributionManager dm, DistributionMessage message) {
    log(message, "About to send");
  }
  
  private void log(DistributionMessage message, String operation) {
    if (!Thread.currentThread().getName().contains("Management Task")
      && !Thread.currentThread().getName().contains("ClientMembership Event Invoker")
      && !Thread.currentThread().getName().contains("Cache Server Load Polling Thread")
    ) {
      StringBuilder builder = new StringBuilder();
      builder
        .append(operation)
        .append(" a ")
        .append(message.getClass().getSimpleName());
      if (message.getSender() == null) {
        builder
          .append(" to ")
          .append(message.getRecipients());
      } else {
        builder
          .append(" from ")
          .append(message.getSender());
      }
      builder
        .append(" at ")
        .append(System.currentTimeMillis());
      this.cache.getLogger().info(builder.toString());
    }
  }
}