package example.client.function;

import org.springframework.data.gemfire.function.annotation.Filter;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

import java.util.Set;

@OnRegion(region = "ReplicatedTrade")
public interface OnReplicatedRegionFunctions {

  @FunctionId("OnRegionFunction")
  Object executeOnRegionFunction(@Filter Set<Integer> keys, boolean unsharedResources);

  @FunctionId("OnRegionNoResultFunction")
  Object executeOnRegionNoResultFunction(@Filter Set<Integer> keys, boolean unsharedResources);
}