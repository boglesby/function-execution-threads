package example.client.function;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component("onServersResultCollector")
public class OnServersFunctionsResultCollector implements ResultCollector<Object, Set> {

  private Map serverResults = new ConcurrentHashMap<>();

  private Set finalResults;

  @Override
  public Set getResult() throws FunctionException {
    // See https://jira.spring.io/browse/DATAGEODE-295
    return this.finalResults;
  }

  @Override
  public Set getResult(long timeout, TimeUnit unit) throws FunctionException, InterruptedException {
    return getResult();
  }

  @Override
  public void addResult(DistributedMember member, Object result) {
    this.serverResults.put(member, result);
  }

  @Override
  public void endResults() {
    // This method is called once the function has completed running. Since the
    // ResultCollector is reused, the intermediate results need to be cleared
    // for the next execution.
    Map map = new HashMap(this.serverResults);
    this.finalResults = Collections.singleton(map);
    clearResults();
  }

  @Override
  public void clearResults() {
    this.serverResults.clear();
  }
}
