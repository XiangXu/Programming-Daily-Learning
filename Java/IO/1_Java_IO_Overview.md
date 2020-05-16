# Java IO Overview

## Input and Output - Source and Destination

Java's IO package mostly concerns itself with the reading of raw data from a source and writing of raw data to a destination.

The most typical sources and destinations of data are these:
* Files
* Pipes
* Network Connections
* In-memory Buffers(e.g. arrays)
* System.in, System.out, System.error

### Stream

**IO Streams** are a core conecpt in Java IO. A stream is a conceptually endless flow of data. You can either read from a stream or write to a stream. A stream is connected to a data source or a data destination. 

**Streams in Java IO can be either byte based(reading and writing bytes) or character based(reading and writing characters)**.

### InputStream, OutStream, Reader and Writer

**A program that needs to read data from some source needs a InputStream or a Reader**.

**A program that needs to write data to some destination needs an OutputStream or a Writer**.

## Java IO Purpose

* File Access
* Network Access
* Internal Memory Buffer Access
* Inter-Thread Communication (Pipes)
* Buffering
* Filtering
* Parsing
* Reading and Writing Text (Readers / Writers)
* Reading and Writing Primitive Data (long, int etc.)
* Reading and Writing Objects




Reference:

http://tutorials.jenkov.com/java-io/overview.html