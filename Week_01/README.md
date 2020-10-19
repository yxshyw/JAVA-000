### 1.自定义加载器
run: java ClassLoadMain

### 2.打印加载jar包路径
打印乱码解决: javac -encoding utf-8 JvmClassLoaderPrintPath.java

run: java JvmClassLoaderPrintPath

### 3.堆关系
-xms: 初始堆内存

-xmx: 最大堆内存

-xmn: 年轻代大小

-xss: 每个线程堆栈大小

-MetaSpace: 类，方法，常量池等

-DirectMemory: 堆外内存
