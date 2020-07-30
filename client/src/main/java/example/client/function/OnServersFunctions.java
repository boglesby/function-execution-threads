package example.client.function;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServers;

@OnServers(resultCollector = "onServersResultCollector")
public interface OnServersFunctions {

  @FunctionId("OnServerFunction")
  Object executeOnServersFunction();
}
