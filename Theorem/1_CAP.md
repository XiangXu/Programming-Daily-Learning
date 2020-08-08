# CAP Theorem

In theoretical computer science, the CAP theorem, also named Brewer's theorem after computer scientist Eric Brewer, states that it is impossible for a distrubted data store to simultaneously provide more than two out of the following three guarantees:

* **Consistency**: Every read receives the most recent write or an error.
* **Availability**: Every request receives a(non error) response, without guarantee that it contains the most recent write.
* **Partition tolerance**: The system continues to operate despite an arbitrary number of messages being dropped(or deplayed) by the network between nodes.

When a network partition failure happens should we decide to

* Cancel the operation and thus decrease the availability but ensure consistency.
* Proceed with the operation that thus provde availability but risk consistency.

**The CAP theorem implies that in the presence of a network partition, one has to choose between consistency and availability**. Note that consistency as defined in the CAP theorem is quite different from the consistency guaranteed in ACID database transactions.

Reference

https://en.wikipedia.org/wiki/CAP_theorem