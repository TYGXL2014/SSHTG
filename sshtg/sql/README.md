SQL所支持的命令类型
-------------------
-1、DDL(Data Definition Language)数据定义语言
CREATE,ALTER,DROP

-2、DML(Data Manipulation Language)数据操纵语言
INSERT,DELETE,UPDATE,SELECT

-3、TCL(Transaction Control Language)事务控制语言
COMMIT,ROLLBACK

set autocommit = false;手动提交

set autocommit = true;自动提交

保存还原点：savepoint name_point;
回滚到指定还原点：rollback to name_point；

-4、DCL(Data Control Language)数据控制语言

