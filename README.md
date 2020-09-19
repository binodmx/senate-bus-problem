# senate-bus-problem
Concurrent solution to the Senate Bus Problem using Java

## Steps to run this program
1. Compile the main.java file using ```javac Main.java``` command.
2. Run Main file using ```java Main``` command.

## Problem definition
This problem was originally based on the Senate bus at Wellesley College. Riders come to a bus stop and wait for a bus. When the bus arrives, all the waiting riders invoke boardBus, but anyone who arrives while the bus is boarding has to wait for the next bus. The capacity of the bus is 50 people; if there are more than 50 people waiting, some will have to wait for the next bus. When all the waiting riders have boarded, the bus can invoke depart. If the bus arrives when there are no riders, it should depart immediately.

**Puzzle**: Write synchronization code that enforces all of these constraints.

**Note**: Busses and riders will continue to arrive throughout the day. Assume inter-arrival time of busses and riders are exponentially distributed with a mean of 20 min and 30 sec, respectively.

> Taken from The Little Book of Semaphores by Allen B. Downey

## Constraints
There are 2 main classes (Bus & Rider) that we have to observe in differnt points of view.

### Bus
1. Only 1 bus can arrive to the bus stop at one time.
2. A bus cannot depart until all the riders are boarded or bus is full.

### Rider
1. Riders cannot board bus until a bus arrives.
2. Only 1 rider can board bus at one time.
3. Only 50 riders can board a single bus.
4. Riders who come when bus is boarding have to wait for the next bus.

## Solution


## Acknowledgement
This solution is developed by taking the insights from https://github.com/nishadi/senate-bus-problem.
