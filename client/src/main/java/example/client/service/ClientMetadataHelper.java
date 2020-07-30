package example.client.service;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;

import org.apache.geode.cache.client.internal.ClientMetadataService;

import org.apache.geode.distributed.internal.ServerLocation;

import org.apache.geode.internal.cache.GemFireCacheImpl;
import org.apache.geode.internal.cache.LocalRegion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientMetadataHelper {

  public static void printMetadata(GemFireCache cache, Region region) {
    ClientMetadataService service = ((GemFireCacheImpl) cache).getClientMetadataService();
    service.getClientPRMetadata((LocalRegion) region);
    HashMap<ServerLocation, HashSet<Integer>> servers = service.groupByServerToAllBuckets(region, true);
    if (servers == null) {
      cache.getLogger().warning("The client metadata contains no servers with primary buckets for region " + region.getFullPath());
    } else {
      dumpServerLocations(cache, region, servers);
    }
  }

  private static void dumpServerLocations(GemFireCache cache, Region region, Map<ServerLocation, HashSet<Integer>> servers) {
    StringBuilder builder = new StringBuilder();
    builder
      .append("\nThe client metadata contains the following ")
      .append(servers.size())
      .append(" servers with primary buckets for region ")
      .append(region.getFullPath())
      .append(":");
    for (Map.Entry entry : servers.entrySet()) {
      builder
        .append("\n\t")
        .append(entry.getKey())
        .append("->")
        .append(entry.getValue());
    }
    cache.getLogger().info(builder.toString());
  }
}