# DATETIME and TIMESTAMP

The **DATETIME** type is used for values contains both date and time parts. MYSQL reterives and displays **DATETIME** values in 'YYYY-MM-DD HH:MM:SS' format. The support range is 1000-01-01 00:00:00' to '9999-12-31 23:59:59'.

The **TIMESTAMP** data type is used for values that contains both date and times parts. **TIMESTAMP** has a range of '1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07' UTC.”

*“MySQL converts TIMESTAMP values from the current time zone to UTC for storage, and back from UTC to the current time zone for retrieval. (This does not occur for other types such as DATETIME.)”.*

When using the TIMESTAMP data type, the values are converted by the database to UTC (universal time zone) and are stored in that timezone. This means that when you fetch (SELECT) this data, a conversion will be done from UTC to the current time zone, and only then the data will be returned. This behavior doesn’t occur for DATETIME stored values.

Well, if you are serving customers in different countries with different application instances, by using TIMESTAMP, you’ll be able to serve the same date and time data in different timezones, directly from the database.

Please note that by default the applied timezone is the server’s timezone. You can set the timezone on a per connection basis if you wish to change it. For example, SET time_zone = '-8:00'; .

To summarize, if you want to serve your date and time data the same way regardless of timezones, you can use the DATETIME type (which will also allow you to use all the date & type functions built in MySQL). Otherwise, you can use TIMESTAMP and serve the data on a per-timezone basis.


## date和datetime、timestamp 的区别
date保存精度到天，格式为：YYYY-MM-DD，如2016-11-07
datetime和timestamp精度保存到秒，格式为：YYYY-MM-DD HH:MM:SS,如：2016-11-07 10:58:27
因此如果只需保存到天的字段（如生日）用date就可以了。

## datetime 和timestamp
两者都是时间类型字段，格式都一致。两者主要有以下几点区别：

最主要的区别-受时区影响不同。timestamp会跟随设置的时区变化而变化，而datetime保存的是绝对值不会变化。
详细可以阅读这篇博客的演示：MySQL: Datetime Versus Timestamp Data Types
一个timestamp字段，一个datetime字段，修改时区SET TIME_ZONE = "america/new_york";后，timestamp字段的值变了!
因此，如果应用场景有跨时区要求的要特别注意这点。

占用存储空间不同。timestamp储存占用4个字节，datetime储存占用8个字节：12.8 Data Type Storage Requirements

可表示的时间范围不同。timestamp可表示范围:1970-01-01 00:00:00~2038-01-09 03:14:07，datetime支持的范围更宽1000-01-01 00:00:00 ~ 9999-12-31 23:59:59

索引速度不同。timestamp更轻量，索引相对datetime更快。


Reference:

https://www.eversql.com/mysql-datetime-vs-timestamp-column-types-which-one-i-should-use/

https://zhuanlan.zhihu.com/p/23663741