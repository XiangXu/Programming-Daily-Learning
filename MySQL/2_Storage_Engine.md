# Storage Engine

Storage Engine are MySQL components, that can handle the SQL operations for different table types to store and manage information in a database. **InnoDB** is mostly used general-purpose storage and as MySQL 5.5 and later it is the default engine. 

## InnoDB

This is the default storage engine for MySQL 5.5 and higher. It provides **transaction-safe** (ACID compliant) tables, supports **FOREIGN KEY** referential-integrity constraints. It supports commit, rollback, and crash-recovery capabilities to protect data. It also supports **row-level locking**. It's 'consistent nolocking reads' increases performance when used in a multiuser environment. It stores data in clustered indexes which reduce I/O queries based on primary keys. 

## MyISAM

This storage engine, manage no-transactional tables, provides high-speed storage and retrieval, support full text searching.

## Memory

Provides in-memory tables, formerly known as HEAP. It stores all data in RAM for faster access than storing data on disks. Useful for quick look up of reference and other identical data.

## Merge

Groups more than one similar MyISAM tables to be treated as a single table, can handle non transactional tables, included by default.

## Example

You can create tables with this engine, but cannot store or fetch data. Purpose of this is to teach developers about how to write a new storage engine.

## ARCHIVE

Used to store large amount of data, doesn't support indexes.

## CSV

Stores data in Comma Seperated Value format in a text file.

## BlackHole

Accepts data to store but always return emtpy.

## FEDERATED	

Stores data in a remote database.


Reference

https://www.w3resource.com/mysql/mysql-storage-engines.php
