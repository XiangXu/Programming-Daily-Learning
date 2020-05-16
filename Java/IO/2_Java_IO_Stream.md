## Java IO Stream

Java IO streams are flows of data you can either read from, or write to. Streams are typically connected to a data source, or data destination.

A stream has no concept of an index of the read or written data, like an array does. **Nor can you typically move forth and back in a stream**, like you can in an array, or in a file using RandomAccessFile. A stream is just a continuous flow of data.

Some stream implementations like the **PushbackInputStream allows you to push data back into the stream, to be re-read again later**. But you can only push back a limited amount of data, and you cannot traverse(穿过) the data at will, like you can with an array. Data can only be accessed sequentially.

**The streams that are byte based are typically called something with "stream", like InputStream or OutputStream.**

**The streams that are character based are typically called something with "Reader" or "Writer". The character based streams can read / write characters**.

## InputStream

It is the base class for all Java IO input streams.

You typically read data from an InputStream by calling the read() method. The read() method returns a int containing the byte value of the byte read. If there is no more data to be read, the read() method typically returns -1;

```java
InputStream input = new FileInputStream("c:\\data\\input-file.txt");

int data = input.read();

while(data != -1){
  data = input.read();
}
```

## OutputStream

It is the base class for all Java IO output streams.

Here is a simple example pushing some data out to a file:

```java
OutputStream output = new FileOutputStream("c:\\data\\output-file.txt");
output.write("Hello World".getBytes());
output.close();
```

## Combining Streams

You can combine streams into chains to achieve more advanced input and output operations. For instance, reading every byte one at a time from a file is slow. It is faster to read a larger block of data from the disk and then iterate through that block byte for byte afterwards. To achieve buffering you can wrap your InputStream in an BufferedInputStream. Here is an example:

```java
InputStream input = new BufferedInputStream(
                        new FileInputStream("c:\\data\\input-file.txt"));

```

Reference:

http://tutorials.jenkov.com/java-io/streams.html
