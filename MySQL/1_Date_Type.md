# date和datetime、timestamp 的区别
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
