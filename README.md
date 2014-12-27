Psycho-Compiler
==============

##TODO
* 语义错误显示到UI
* UI显示变量类型

##更新记录
* 2014/11/14：项目开始。
* 2014/11/21：完成了program，function，type部分的jj定义。
* 2014/11/22：完成了全部的jj定义，添加了命令行版的token，parser报错，能够生成抽象语法树。
* 2014/11/23：增加UI。
* 2014/12/06：增加静态分析结构框架。
* 2014/12/07：完成main中变量申明的静态分析，完成函数静态分析初步，重新包装SimpleNode。
* 2014/12/16：完成全部编译，完成快速排序的demo测试
* 2014/12/18：添加八皇后demo
* 2014/12/22：添加说明文档和demo

##简介
这是一个编译原理课程(SE302)的project，只是一个用于玩乐的编译器。

##环境
* Runtime : Java JDK1.8 32bit
* IDE     : IDEA 14 with gradle
* LIB     : JavaCC-5.0
* Support : LLVM-runtime
