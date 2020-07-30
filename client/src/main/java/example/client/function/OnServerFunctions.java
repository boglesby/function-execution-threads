package example.client.function;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnServer;

@OnServer
public interface OnServerFunctions {

  @FunctionId("OnServerFunction")
  Object executeOnServerFunction();
}
