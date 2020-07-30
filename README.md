# Function Execution Threads
## Description

This project shows which thread on the server processes a Function execution request for a variety of common use cases.

It has tests for these use cases:

- onServer
- onServers
- onRegion (Replicated Region)
- onRegion (Replicated Region, Unshared Resources)
- onRegion (Partitioned Region, No Filter)
- onRegion (Partitioned Region, No Filter, Unshared Resources)
- onRegion (Partitioned Region, One Filter)
- onRegion (Partitioned Region, Multiple Filters)
- onRegion (Partitioned Region, Multiple Filters, Unshared Resources)

## Initialization
Modify the **GEODE** environment variable in the *setenv.sh* script to point to a Geode installation directory.
## Build
Build the Spring Boot Client Application and Geode Server Function and logger classes using gradle like:

```
./gradlew clean jar bootJar
```
## Run Tests
### Start and Configure Locator and Servers
Start and configure the locator and 2 servers using the *startandconfigure.sh* script like:

```
./startandconfigure.sh
```
### Load Entries
Load N Trade instances into the PartitionedTrade and ReplicatedTrade Regions using the *runclient.sh* script like below.

The parameters are:

- operation (load-regions)
- number of entries (113) (with Integer keys, this creates all the buckets)

```
./runclient.sh load-regions 113
```
### Execute onServer Use Case
Run the **onServer** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-server-function
```
### Execute onServers Use Case

Run the **onServers** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-servers-function
```
### Execute onRegion (Replicated Region) Use Case

Run the **onRegion (Replicated Region)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-replicated-region-function
```
### Execute onRegion (Replicated Region, Unshared Resources) Use Case

Run the **onRegion (Replicated Region, Unshared Resources)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-replicated-region-function-unshared-resources
```
### Execute onRegion (Partitioned Region, No Filter) Use Case

Run the **onRegion (Partitioned Region, No Filter)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-partitioned-region-function-no-filter
```
### Execute onRegion (Partitioned Region, No Filter, Unshared Resources) Use Case

Run the **onRegion (Partitioned Region, No Filter, Unshared Resources)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-partitioned-region-function-no-filter-unshared-resources
```
### Execute onRegion (Partitioned Region, One Filter) Use Case

Run the **onRegion (Partitioned Region, One Filter)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-partitioned-region-function-one-filter
```
### Execute onRegion (Partitioned Region, Multiple Filters) Use Case

Run the **onRegion (Partitioned Region, Multiple Filters)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-partitioned-region-function-multiple-filters
```
### Execute onRegion (Partitioned Region, Multiple Filters, Unshared Resources) Use Case

Run the **onRegion (Partitioned Region, Multiple Filters, Unshared Resources)** use case using the *runclient.sh* script like below.

```
./runclient.sh execute-on-partitioned-region-function-multiple-filters-unshared-resources
```
### Shutdown Locator and Servers
Execute the *shutdownall.sh* script to shutdown the servers and locators like:

```
./shutdownall.sh
```
### Remove Locator and Server Files
Execute the *cleanupfiles.sh* script to remove the server and locator files like:

```
./cleanupfiles.sh
```
## Example Sample Output
### Start and Configure Locator and Servers
Sample output from the *startandconfigure.sh* script is:

```
./startandconfigure.sh 
1. Executing - start locator --name=locator

............
Locator in <working-directory>/locator on xxx.xxx.x.xx[10334] as locator is currently online.
Process ID: 82122
Uptime: 13 seconds
Geode Version: 1.12.0
Java Version: 1.8.0_151
Log File: <working-directory>/locator/locator.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

Successfully connected to: JMX Manager [host=xxx.xxx.x.xx, port=1099]

Cluster configuration service is up and running.

2. Executing - set variable --name=APP_RESULT_VIEWER --value=any

Value for variable APP_RESULT_VIEWER is now: any.

3. Executing - start server --name=server-1 --server-port=0 --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false

........
Server in <working-directory>/server-1 on xxx.xxx.x.xx[53422] as server-1 is currently online.
Process ID: 82128
Uptime: 5 seconds
Geode Version: 1.12.0
Java Version: 1.8.0_151
Log File: <working-directory>/server-1/cacheserver.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

4. Executing - start server --name=server-2 --server-port=0 --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false

........
Server in <working-directory>/server-2 on xxx.xxx.x.xx[53445] as server-2 is currently online.
Process ID: 82129
Uptime: 6 seconds
Geode Version: 1.12.0
Java Version: 1.8.0_151
Log File: <working-directory>/server-2/cacheserver.log
JVM Arguments: <jvm-arguments>
Class-Path: <classpath>

5. Executing - list members

Member Count : 3

  Name   | Id
-------- | --------------------------------------------------------------
locator  | xxx.xxx.x.xx(locator:82122:locator)<ec><v0>:41000 [Coordinator]
server-1 | xxx.xxx.x.xx(server-1:82128)<v1>:41001
server-2 | xxx.xxx.x.xx(server-2:82129)<v2>:41002

6. Executing - create region --name=PartitionedTrade --type=PARTITION_REDUNDANT

 Member  | Status | Message
-------- | ------ | ------------------------------------------------
server-1 | OK     | Region "/PartitionedTrade" created on "server-1"
server-2 | OK     | Region "/PartitionedTrade" created on "server-2"

Cluster configuration for group 'cluster' is updated.

7. Executing - create region --name=ReplicatedTrade --type=REPLICATE

 Member  | Status | Message
-------- | ------ | -----------------------------------------------
server-1 | OK     | Region "/ReplicatedTrade" created on "server-1"
server-2 | OK     | Region "/ReplicatedTrade" created on "server-2"

Cluster configuration for group 'cluster' is updated.

8. Executing - list regions

List of regions
----------------
PartitionedTrade
ReplicatedTrade

9. Executing - deploy --jar=server/build/libs/server-0.0.1-SNAPSHOT.jar

 Member  |       Deployed JAR        | Deployed JAR Location
-------- | ------------------------- | ---------------------------------------------------------------------------------------------------
server-1 | server-0.0.1-SNAPSHOT.jar | <working-directory>/server-1/server-0.0.1-SNAPSHOT.v1.jar
server-2 | server-0.0.1-SNAPSHOT.jar | <working-directory>/server-2/server-0.0.1-SNAPSHOT.v1.jar

10. Executing - list functions

 Member  | Function
-------- | ------------------------
server-1 | OnRegionFunction
server-1 | OnRegionNoResultFunction
server-1 | OnServerFunction
server-2 | OnRegionFunction
server-2 | OnRegionNoResultFunction
server-2 | OnServerFunction

************************* Execution Summary ***********************
Script file: startandconfigure.gfsh

Command-1 : start locator --name=locator
Status    : PASSED

Command-2 : set variable --name=APP_RESULT_VIEWER --value=any
Status    : PASSED

Command-3 : start server --name=server-1 --server-port=0 --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false
Status    : PASSED

Command-4 : start server --name=server-2 --server-port=0 --statistic-archive-file=cacheserver.gfs --J=-Dgemfire.log-file=cacheserver.log --J=-Dgemfire.conserve-sockets=false
Status    : PASSED

Command-5 : list members
Status    : PASSED

Command-6 : create region --name=PartitionedTrade --type=PARTITION_REDUNDANT
Status    : PASSED

Command-7 : create region --name=ReplicatedTrade --type=REPLICATE
Status    : PASSED

Command-8 : list regions
Status    : PASSED

Command-9 : deploy --jar=server/build/libs/server-0.0.1-SNAPSHOT.jar
Status    : PASSED

Command-10 : list functions
Status     : PASSED
```
### Load Entries
Sample output from the *runclient.sh* script is:

```
./runclient.sh load-regions 113

2020-07-25 07:46:36.577  INFO 82157 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:46:40.828  INFO 82157 --- [           main] example.client.Client                    : Started Client in 4.905 seconds (JVM running for 5.5)
2020-07-25 07:46:40.831  INFO 82157 --- [           main] example.client.service.TradeService      : Putting 113 trades
2020-07-25 07:46:41.011  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=0, cusip=MCD, shares=25, price=507.85)
2020-07-25 07:46:41.072  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=1, cusip=PEP, shares=53, price=743.35)
2020-07-25 07:46:41.142  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=2, cusip=ADBE, shares=13, price=628.65)
2020-07-25 07:46:41.185  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=3, cusip=NVS, shares=46, price=796.89)
2020-07-25 07:46:41.234  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=4, cusip=JNJ, shares=24, price=94.40)
...
2020-07-25 07:46:44.621  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=108, cusip=PG, shares=25, price=53.94)
2020-07-25 07:46:44.644  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=109, cusip=UNP, shares=91, price=975.90)
2020-07-25 07:46:44.669  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=110, cusip=MA, shares=82, price=428.39)
2020-07-25 07:46:44.711  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=111, cusip=LLY, shares=50, price=760.72)
2020-07-25 07:46:44.739  INFO 82157 --- [           main] example.client.service.TradeService      : Saved Trade(id=112, cusip=AVGO, shares=75, price=834.82)
```
### Execute onServer Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-server-function
...
2020-07-25 07:47:59.857  INFO 82180 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:48:04.107  INFO 82180 --- [           main] example.client.Client                    : Started Client in 4.926 seconds (JVM running for 5.644)
2020-07-25 07:48:04.157  INFO 82180 --- [           main] example.client.service.TradeService      : Executed OnServerFunction result=[true]
```
This use case requires 2 threads:

- 1 ServerConnection on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives and processes the Function execution request:

```
[info 2020/07/25 07:48:04.138 HST <ServerConnection on port 53445 Thread 2> tid=0x4b] About to process a ExecuteFunction70 at 1595699284138

[info 2020/07/25 07:48:04.145 HST <ServerConnection on port 53445 Thread 2> tid=0x4b] Executing function=OnServerFunction at 1595699284145
```
The ServerConnection thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:48:04.151 HST <ServerConnection on port 53445 Thread 2> tid=0x4b] About to send a UpdateMessage to [192.168.1.3(server-2:82128)<v1>:41001] at 1595699284151
```
An unshared P2P message reader thread on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:48:04.153 HST <P2P message reader for 192.168.1.3(server-1:82129)<v2>:41002 unshared ordered uid=12 dom #1 port=53500> tid=0x3d] About to process a UpdateMessage from 192.168.1.3(server-1:82129)<v2>:41002 at 1595699284153

[info 2020/07/25 07:48:04.155 HST <P2P message reader for 192.168.1.3(server-1:82129)<v2>:41002 unshared ordered uid=12 dom #1 port=53500> tid=0x3d] Processed a UpdateMessage from 192.168.1.3(server-1:82129)<v2>:41002 at 1595699284154
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:48:04.155 HST <ServerConnection on port 53445 Thread 2> tid=0x4b] Executing function=OnServerFunction completed at 1595699284155

[info 2020/07/25 07:48:04.156 HST <ServerConnection on port 53445 Thread 2> tid=0x4b] Completed processing a ExecuteFunction70 at 1595699284156
```
### Execute onServers Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-servers-function
...
2020-07-25 07:49:26.109  INFO 82206 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:49:30.415  INFO 82206 --- [           main] example.client.Client                    : Started Client in 4.969 seconds (JVM running for 5.666)
2020-07-25 07:49:30.497  INFO 82206 --- [           main] example.client.service.TradeService      : Executed OnServersFunction result=[{192.168.1.3(server-1:82128)<v1>:41001=true, 192.168.1.3(server-2:82129)<v2>:41002=true}]
```
Each server in this use case has the same behavior as the **onServer** use case.

This use case requires 2 threads:

- 1 ServerConnection on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives and processes the Function execution request:

```
[info 2020/07/25 07:49:30.471 HST <ServerConnection on port 53422 Thread 1> tid=0x4d] About to process a ExecuteFunction70 at 1595699370471

[info 2020/07/25 07:49:30.483 HST <ServerConnection on port 53422 Thread 1> tid=0x4d] Executing function=OnServerFunction at 1595699370483
```
The ServerConnection thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:49:30.492 HST <ServerConnection on port 53422 Thread 1> tid=0x4d] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699370491
```
An unshared P2P message reader thread on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:49:30.494 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=11 dom #1 port=53540> tid=0x34] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699370494

[info 2020/07/25 07:49:30.495 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=11 dom #1 port=53540> tid=0x34] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699370495
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:49:30.495 HST <ServerConnection on port 53422 Thread 1> tid=0x4d] Executing function=OnServerFunction completed at 1595699370495

[info 2020/07/25 07:49:30.496 HST <ServerConnection on port 53422 Thread 1> tid=0x4d] Completed processing a ExecuteFunction70 at 1595699370496
```
### Execute onRegion (Replicated Region) Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-replicated-region-function
...
2020-07-25 07:51:00.338  INFO 82233 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:51:04.681  INFO 82233 --- [           main] example.client.Client                    : Started Client in 4.975 seconds (JVM running for 5.683)
2020-07-25 07:51:04.729  INFO 82233 --- [           main] example.client.service.TradeService      : Executed replicated OnRegionFunction unsharedResources=false; result=[true]
```
This use case requires 4 threads:

- 1 ServerConnection, 1 Function Execution Processor, 1 shared P2P message reader on server-1
- 1 shared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:51:04.717 HST <ServerConnection on port 53422 Thread 2> tid=0x51] About to process a ExecuteRegionFunctionGeode18 at 1595699464717
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:51:04.724 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=1 keys=[0]; region=/ReplicatedTrade; unsharedResources=false at 1595699464724
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:51:04.725 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699464725
```
The shared ordered P2P message reader on server-2 receives and processes the UpdateMessage and sends a ReplyMessage:

```
[info 2020/07/25 07:51:04.726 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699464726

[info 2020/07/25 07:51:04.727 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to send a ReplyMessage to [192.168.1.3(server-1:82128)<v1>:41001] at 1595699464727

[info 2020/07/25 07:51:04.727 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699464727
```
The shared unordered P2P message reader on server-1 receives and processes the ReplyMessage:

```
[info 2020/07/25 07:51:04.727 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] About to process a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699464727

[info 2020/07/25 07:51:04.727 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] Processed a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699464727
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:51:04.728 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699464728
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:51:04.728 HST <ServerConnection on port 53422 Thread 2> tid=0x51] Completed processing a ExecuteRegionFunctionGeode18 at 1595699464728
```
### Execute onRegion (Replicated Region, Unshared Resources) Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-replicated-region-function-unshared-resources
...
2020-07-25 07:52:10.065  INFO 82255 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:52:12.837  INFO 82255 --- [           main] example.client.Client                    : Started Client in 3.11 seconds (JVM running for 3.55)
2020-07-25 07:52:12.866  INFO 82255 --- [           main] example.client.service.TradeService      : Executed replicated OnRegionFunction unsharedResources=true; result=[true]
```
This use case requires 3 threads:

- 1 ServerConnection, 1 Function Execution Processor on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:52:12.862 HST <ServerConnection on port 53422 Thread 3> tid=0x52] About to process a ExecuteRegionFunctionGeode18 at 1595699532861
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:52:12.862 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=1 keys=[0]; region=/ReplicatedTrade; unsharedResources=true at 1595699532862
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:52:12.863 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699532863
```
An unshared P2P message reader on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:52:12.864 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=12 dom #1 port=53999> tid=0x34] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699532864

[info 2020/07/25 07:52:12.865 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=12 dom #1 port=53999> tid=0x34] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699532865
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:52:12.865 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699532865
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:52:12.866 HST <ServerConnection on port 53422 Thread 3> tid=0x52] Completed processing a ExecuteRegionFunctionGeode18 at 1595699532866
```
### Execute onRegion (Partitioned Region, No Filter) Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-partitioned-region-function-no-filter
...
2020-07-25 07:53:21.872  INFO 82277 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:53:24.991  INFO 82277 --- [           main] example.client.Client                    : Started Client in 3.509 seconds (JVM running for 3.963)
2020-07-25 07:53:25.016  INFO 82277 --- [           main] org.apache.geode                         : 
The client metadata contains the following 2 servers with primary buckets for region /PartitionedTrade:
        BucketServerLocation{bucketId=0,host=192.168.1.3,port=53445,isPrimary=true,version=5}->[0, 3, 5, 7, 9, 11, 13, 14, 16, 18, 21, 23, 25, 26, 28, 31, 33, 35, 36, 39, 40, 43, 44, 46, 49, 51, 52, 55, 56, 58, 60, 62, 64, 66, 69, 71, 73, 75, 76, 79, 81, 83, 85, 87, 89, 90, 93, 95, 96, 98, 100, 103, 105, 107, 108, 111]
        BucketServerLocation{bucketId=1,host=192.168.1.3,port=53422,isPrimary=true,version=5}->[1, 2, 4, 6, 8, 10, 12, 15, 17, 19, 20, 22, 24, 27, 29, 30, 32, 34, 37, 38, 41, 42, 45, 47, 48, 50, 53, 54, 57, 59, 61, 63, 65, 67, 68, 70, 72, 74, 77, 78, 80, 82, 84, 86, 88, 91, 92, 94, 97, 99, 101, 102, 104, 106, 109, 110, 112]
2020-07-25 07:53:25.069  INFO 82277 --- [           main] example.client.service.TradeService      : Executed partitioned OnRegionFunction filter=[]; unsharedResources=false; result=[true, true]
```
This use case requires 4 threads:

- 1 ServerConnection, 1 Function Execution Processor, 1 shared P2P message reader on server-1
- 1 shared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:53:25.043 HST <ServerConnection on port 53422 Thread 4> tid=0x54] About to process a ExecuteRegionFunctionSingleHop at 1595699605043
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:53:25.056 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=1 keys=[1]; region=/PartitionedTrade; unsharedResources=false at 1595699605056
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:53:25.059 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699605059
```
The shared ordered P2P message reader on server-2 receives and processes the UpdateMessage and sends a ReplyMessage:

```
[info 2020/07/25 07:53:25.060 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699605060

[info 2020/07/25 07:53:25.061 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to send a ReplyMessage to [192.168.1.3(server-1:82128)<v1>:41001] at 1595699605061

[info 2020/07/25 07:53:25.061 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699605061
```
The shared unordered P2P message reader on server-1 receives and processes the ReplyMessage:

```
[info 2020/07/25 07:53:25.061 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] About to process a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699605061

[info 2020/07/25 07:53:25.061 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] Processed a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699605061
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:53:25.062 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699605062
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:53:25.064 HST <ServerConnection on port 53422 Thread 4> tid=0x54] Completed processing a ExecuteRegionFunctionSingleHop at 1595699605063
```
### Execute onRegion (Partitioned Region, No Filter, Unshared Resources) Use Case
Sample output from the *runclient.sh* script is:

```
...
2020-07-25 07:54:42.415  INFO 82299 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:54:46.732  INFO 82299 --- [           main] example.client.Client                    : Started Client in 4.96 seconds (JVM running for 5.542)
2020-07-25 07:54:46.751  INFO 82299 --- [           main] org.apache.geode                         : 
The client metadata contains the following 2 servers with primary buckets for region /PartitionedTrade:
        BucketServerLocation{bucketId=0,host=192.168.1.3,port=53445,isPrimary=true,version=5}->[0, 3, 5, 7, 9, 11, 13, 14, 16, 18, 21, 23, 25, 26, 28, 31, 33, 35, 36, 39, 40, 43, 44, 46, 49, 51, 52, 55, 56, 58, 60, 62, 64, 66, 69, 71, 73, 75, 76, 79, 81, 83, 85, 87, 89, 90, 93, 95, 96, 98, 100, 103, 105, 107, 108, 111]
        BucketServerLocation{bucketId=1,host=192.168.1.3,port=53422,isPrimary=true,version=5}->[1, 2, 4, 6, 8, 10, 12, 15, 17, 19, 20, 22, 24, 27, 29, 30, 32, 34, 37, 38, 41, 42, 45, 47, 48, 50, 53, 54, 57, 59, 61, 63, 65, 67, 68, 70, 72, 74, 77, 78, 80, 82, 84, 86, 88, 91, 92, 94, 97, 99, 101, 102, 104, 106, 109, 110, 112]
2020-07-25 07:54:46.785  INFO 82299 --- [           main] example.client.service.TradeService      : Executed partitioned OnRegionFunction filter=[]; unsharedResources=true; result=[true, true]
```
This use case requires 3 threads:

- 1 ServerConnection, 1 Function Execution Processor on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:54:46.775 HST <ServerConnection on port 53422 Thread 5> tid=0x57] About to process a ExecuteRegionFunctionSingleHop at 1595699686775
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:54:46.778 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=1 keys=[1]; region=/PartitionedTrade; unsharedResources=true at 1595699686778
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:54:46.779 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699686779
```
An unshared P2P message reader on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:54:46.783 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=14 dom #1 port=54452> tid=0x34] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699686783

[info 2020/07/25 07:54:46.784 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=14 dom #1 port=54452> tid=0x34] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699686783
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:54:46.784 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699686784
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:54:46.784 HST <ServerConnection on port 53422 Thread 5> tid=0x57] Completed processing a ExecuteRegionFunctionSingleHop at 1595699686784
```
### Execute onRegion (Partitioned Region, One Filter) Use Case
Sample output from the *runclient.sh* script is:

```
...
2020-07-25 07:55:59.840  INFO 82321 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:56:02.826  INFO 82321 --- [           main] example.client.Client                    : Started Client in 3.321 seconds (JVM running for 3.691)
2020-07-25 07:56:02.848  INFO 82321 --- [           main] org.apache.geode                         : 
The client metadata contains the following 2 servers with primary buckets for region /PartitionedTrade:
        BucketServerLocation{bucketId=0,host=192.168.1.3,port=53445,isPrimary=true,version=5}->[0, 3, 5, 7, 9, 11, 13, 14, 16, 18, 21, 23, 25, 26, 28, 31, 33, 35, 36, 39, 40, 43, 44, 46, 49, 51, 52, 55, 56, 58, 60, 62, 64, 66, 69, 71, 73, 75, 76, 79, 81, 83, 85, 87, 89, 90, 93, 95, 96, 98, 100, 103, 105, 107, 108, 111]
        BucketServerLocation{bucketId=1,host=192.168.1.3,port=53422,isPrimary=true,version=5}->[1, 2, 4, 6, 8, 10, 12, 15, 17, 19, 20, 22, 24, 27, 29, 30, 32, 34, 37, 38, 41, 42, 45, 47, 48, 50, 53, 54, 57, 59, 61, 63, 65, 67, 68, 70, 72, 74, 77, 78, 80, 82, 84, 86, 88, 91, 92, 94, 97, 99, 101, 102, 104, 106, 109, 110, 112]
2020-07-25 07:56:02.875  INFO 82321 --- [           main] example.client.service.TradeService      : Executed partitioned OnRegionFunction filter=[0]; unsharedResources=false; result=[true]
```
This use case requires 2 threads:

- 1 ServerConnection on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives and processes the Function execution request:

```
[info 2020/07/25 07:56:02.867 HST <ServerConnection on port 53445 Thread 8> tid=0x55] About to process a ExecuteRegionFunctionSingleHop at 1595699762867

[info 2020/07/25 07:56:02.868 HST <ServerConnection on port 53445 Thread 8> tid=0x55] Executing function=OnRegionFunction; numKeys=1 keys=[0]; region=/PartitionedTrade; unsharedResources=false at 1595699762868
```
The ServerConnection thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:56:02.868 HST <ServerConnection on port 53445 Thread 8> tid=0x55] About to send a UpdateMessage to [192.168.1.3(server-2:82128)<v1>:41001] at 1595699762868
```
An unshared P2P message reader thread on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:56:02.871 HST <P2P message reader for 192.168.1.3(server-1:82129)<v2>:41002 unshared ordered uid=16 dom #1 port=54469> tid=0x59] About to process a UpdateMessage from 192.168.1.3(server-1:82129)<v2>:41002 at 1595699762871

[info 2020/07/25 07:56:02.872 HST <P2P message reader for 192.168.1.3(server-1:82129)<v2>:41002 unshared ordered uid=16 dom #1 port=54469> tid=0x59] Processed a UpdateMessage from 192.168.1.3(server-1:82129)<v2>:41002 at 1595699762872
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:56:02.872 HST <ServerConnection on port 53445 Thread 8> tid=0x55] Executing function=OnRegionFunction completed at 1595699762872

[info 2020/07/25 07:56:02.873 HST <ServerConnection on port 53445 Thread 8> tid=0x55] Completed processing a ExecuteRegionFunctionSingleHop at 1595699762873
```
### Execute onRegion (Partitioned Region, Multiple Filters) Use Case
Sample output from the *runclient.sh* script is:

```
...
2020-07-25 07:58:21.076  INFO 82364 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:58:24.209  INFO 82364 --- [           main] example.client.Client                    : Started Client in 3.466 seconds (JVM running for 3.893)
2020-07-25 07:58:24.227  INFO 82364 --- [           main] org.apache.geode                         : 
The client metadata contains the following 2 servers with primary buckets for region /PartitionedTrade:
        BucketServerLocation{bucketId=0,host=192.168.1.3,port=53445,isPrimary=true,version=5}->[0, 3, 5, 7, 9, 11, 13, 14, 16, 18, 21, 23, 25, 26, 28, 31, 33, 35, 36, 39, 40, 43, 44, 46, 49, 51, 52, 55, 56, 58, 60, 62, 64, 66, 69, 71, 73, 75, 76, 79, 81, 83, 85, 87, 89, 90, 93, 95, 96, 98, 100, 103, 105, 107, 108, 111]
        BucketServerLocation{bucketId=1,host=192.168.1.3,port=53422,isPrimary=true,version=5}->[1, 2, 4, 6, 8, 10, 12, 15, 17, 19, 20, 22, 24, 27, 29, 30, 32, 34, 37, 38, 41, 42, 45, 47, 48, 50, 53, 54, 57, 59, 61, 63, 65, 67, 68, 70, 72, 74, 77, 78, 80, 82, 84, 86, 88, 91, 92, 94, 97, 99, 101, 102, 104, 106, 109, 110, 112]
2020-07-25 07:58:24.282  INFO 82364 --- [           main] example.client.service.TradeService      : Executed partitioned OnRegionFunction filter=[0, 1, 2, 3, 4, 5]; unsharedResources=false; result=[true, true]
```
This use case requires 4 threads:

- 1 ServerConnection, 1 Function Execution Processor, 1 shared P2P message reader on server-1
- 1 shared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:58:24.251 HST <ServerConnection on port 53422 Thread 6> tid=0x5e] About to process a ExecuteRegionFunctionSingleHop at 1595699904251
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:58:24.268 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=3 keys=[1, 2, 4]; region=/PartitionedTrade; unsharedResources=false at 1595699904268
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:58:24.273 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699904272
```
The shared ordered P2P message reader on server-2 receives and processes the UpdateMessage and sends a ReplyMessage:

```
[info 2020/07/25 07:58:24.273 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699904273

[info 2020/07/25 07:58:24.274 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] About to send a ReplyMessage to [192.168.1.3(server-1:82128)<v1>:41001] at 1595699904274

[info 2020/07/25 07:58:24.274 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 shared ordered uid=6 port=53449> tid=0x39] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699904274
```
The shared unordered P2P message reader on server-1 receives and processes the ReplyMessage:

```
[info 2020/07/25 07:58:24.274 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] About to process a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699904274

[info 2020/07/25 07:58:24.275 HST <P2P message reader for 192.168.1.3(server-2:82129)<v2>:41002 shared unordered uid=2 port=53439> tid=0x37] Processed a ReplyMessage from 192.168.1.3(server-2:82129)<v2>:41002 at 1595699904275
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:58:24.281 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699904281
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:58:24.282 HST <ServerConnection on port 53422 Thread 6> tid=0x5e] Completed processing a ExecuteRegionFunctionSingleHop at 1595699904282
```
### Execute onRegion (Partitioned Region, Multiple Filters, Unshared Resources) Use Case
Sample output from the *runclient.sh* script is:

```
./runclient.sh execute-on-partitioned-region-function-multiple-filters-unshared-resources
...
2020-07-25 07:59:32.124  INFO 82385 --- [           main] example.client.Client                    : Starting Client on ...
...
2020-07-25 07:59:34.920  INFO 82385 --- [           main] example.client.Client                    : Started Client in 3.119 seconds (JVM running for 3.584)
2020-07-25 07:59:34.937  INFO 82385 --- [           main] org.apache.geode                         : 
The client metadata contains the following 2 servers with primary buckets for region /PartitionedTrade:
        BucketServerLocation{bucketId=0,host=192.168.1.3,port=53445,isPrimary=true,version=5}->[0, 3, 5, 7, 9, 11, 13, 14, 16, 18, 21, 23, 25, 26, 28, 31, 33, 35, 36, 39, 40, 43, 44, 46, 49, 51, 52, 55, 56, 58, 60, 62, 64, 66, 69, 71, 73, 75, 76, 79, 81, 83, 85, 87, 89, 90, 93, 95, 96, 98, 100, 103, 105, 107, 108, 111]
        BucketServerLocation{bucketId=1,host=192.168.1.3,port=53422,isPrimary=true,version=5}->[1, 2, 4, 6, 8, 10, 12, 15, 17, 19, 20, 22, 24, 27, 29, 30, 32, 34, 37, 38, 41, 42, 45, 47, 48, 50, 53, 54, 57, 59, 61, 63, 65, 67, 68, 70, 72, 74, 77, 78, 80, 82, 84, 86, 88, 91, 92, 94, 97, 99, 101, 102, 104, 106, 109, 110, 112]
2020-07-25 07:59:35.049  INFO 82385 --- [           main] example.client.service.TradeService      : Executed partitioned OnRegionFunction filter=[0, 1, 2, 3, 4, 5]; unsharedResources=true; result=[true, true]
```
This use case requires 3 threads:

- 1 ServerConnection, 1 Function Execution Processor on server-1
- 1 unshared P2P message reader on server-2

Shown below is logging on each server that shows the behavior.

A ServerConnection thread on server-1 receives the Function execution request:

```
[info 2020/07/25 07:59:35.024 HST <ServerConnection on port 53422 Thread 7> tid=0x5f] About to process a ExecuteRegionFunctionSingleHop at 1595699975024
```
A Function Execution Processor thread on server-1 processes the Function execution request:

```
[info 2020/07/25 07:59:35.026 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction; numKeys=3 keys=[1, 2, 4]; region=/PartitionedTrade; unsharedResources=true at 1595699975026
```
The Function Execution Processor thread on server-1 sends an UpdateMessage containing the replication of the data to server-2:

```
[info 2020/07/25 07:59:35.027 HST <Function Execution Processor2> tid=0x38] About to send a UpdateMessage to [192.168.1.3(server-2:82129)<v2>:41002] at 1595699975027
```
An unshared P2P message reader on server-2 receives and processes the UpdateMessage:

```
[info 2020/07/25 07:59:35.034 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=16 dom #1 port=54527> tid=0x5e] About to process a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699975031

[info 2020/07/25 07:59:35.035 HST <P2P message reader for 192.168.1.3(server-1:82128)<v1>:41001 unshared ordered uid=16 dom #1 port=54527> tid=0x5e] Processed a UpdateMessage from 192.168.1.3(server-1:82128)<v1>:41001 at 1595699975035
```
The Function Execution Processor thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:59:35.046 HST <Function Execution Processor2> tid=0x38] Executing function=OnRegionFunction completed at 1595699975046
```
The ServerConnection thread on server-1 completes processing the Function execution request:

```
[info 2020/07/25 07:59:35.047 HST <ServerConnection on port 53422 Thread 7> tid=0x5f] Completed processing a ExecuteRegionFunctionSingleHop at 1595699975047
```
### Shutdown Locator and Servers
Sample output from the *shutdownall.sh* script is:

```
./shutdownall.sh 

(1) Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=xxx.xxx.x.xx, port=1099] ..
Successfully connected to: [host=xxx.xxx.x.xx, port=1099]


(2) Executing - shutdown --include-locators=true

Shutdown is triggered
```
