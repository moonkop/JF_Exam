-- MySQL dump 10.13  Distrib 5.6.39, for Linux (x86_64)
--
-- Host: localhost    Database: jf_exam
-- ------------------------------------------------------
-- Server version	5.6.39-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classroom`
--
create DATABASE  jf_exam;
use jf_exam;
DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `school_id` int(16) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmu20qkf4r687db7plx94sb73` (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (2,3,'夏侯1班'),(3,3,'曹家1班'),(4,1,'刘家班'),(5,1,'莽夫1班'),(6,10,'阿伟大的'),(7,4,'孙家一班'),(8,1,'诸葛一班'),(9,3,'帝国提高班'),(10,1,'智囊2班'),(11,3,'智囊2班'),(12,2,'河海1班'),(15,13,'Java6班');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `remark` text COMMENT '备注',
  `subject_id` tinyint(4) NOT NULL,
  `paper_id` char(32) DEFAULT NULL,
  `open_time` bigint(20) DEFAULT NULL,
  `open_duration` bigint(20) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `close_time` bigint(20) DEFAULT NULL,
  `exam_status` int(8) DEFAULT '101',
  `create_teacher_id` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (13,'欢迎页','',9,NULL,1529653597000,0,0,1530793431139,199,'0'),(14,'基本知识点啊','',9,NULL,1529851872000,0,0,1529938261000,199,'0'),(15,'我是考试','',9,NULL,1530862029000,20,60,1530864972527,131,'0'),(16,'woshi','',9,NULL,1530863895000,10,20,1530865056482,199,'0'),(17,'ytest','',9,NULL,1531818513000,0,0,1531830556370,131,'0'),(19,'root','',9,NULL,1531832062000,2,2,1531832302000,111,'0');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_classroom`
--

DROP TABLE IF EXISTS `exam_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_classroom` (
  `exam_id` char(32) DEFAULT NULL,
  `classroom_id` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_classroom`
--

LOCK TABLES `exam_classroom` WRITE;
/*!40000 ALTER TABLE `exam_classroom` DISABLE KEYS */;
INSERT INTO `exam_classroom` VALUES ('1','4'),('2','5'),('3','5'),('7','5'),('8','5'),('9','5'),('10','5'),('11','5'),('12','4'),('13','5'),('14','5'),('15','5'),('15','2'),('16','2'),('17','15'),('19','2');
/*!40000 ALTER TABLE `exam_classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_markteacher`
--

DROP TABLE IF EXISTS `exam_markteacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_markteacher` (
  `exam_id` char(32) DEFAULT NULL,
  `teacher_id` char(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_markteacher`
--

LOCK TABLES `exam_markteacher` WRITE;
/*!40000 ALTER TABLE `exam_markteacher` DISABLE KEYS */;
INSERT INTO `exam_markteacher` VALUES ('1','0'),('10','0'),('9','0'),('13','0'),('14','1'),('14','0'),('15','2'),('15','3'),('15','1'),('15','5'),('15','0'),('15','4'),('16','1'),('16','0'),('17','0');
/*!40000 ALTER TABLE `exam_markteacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` char(32) DEFAULT NULL COMMENT '操作者',
  `module` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `method` varchar(255) DEFAULT NULL COMMENT '详细操作记录',
  `ip` char(15) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `commite` text COMMENT '操作描述',
  `response_time` bigint(20) DEFAULT NULL COMMENT '响应时间',
  `argument` text,
  `realName` varchar(255) DEFAULT NULL,
  `userRole` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3240 DEFAULT CHARSET=utf8 COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` char(32) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `create_teacher_id` char(32) NOT NULL,
  `subject_id` tinyint(4) NOT NULL,
  `questionContain` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='								ddd																																																																																							';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `is_private` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `codes` text,
  `outline` text,
  `options` text,
  `answer` varchar(20) DEFAULT NULL,
  `topic_id` char(32) NOT NULL,
  `create_teacher_id` char(32) NOT NULL,
  `question_type_id` tinyint(4) NOT NULL,
  `subject_id` tinyint(4) NOT NULL,
  `useTime` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `question_topic_id_index` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (383,0,1531796752191,'public class Test1 {\n public static void main(String[] args) {\n   int x = 4;\n   int y = (--x)+(x--)+(x*10);\n   System.out.println(\"x = \" + x + \",y = \" + y);\n }\n}','看程序说结果','[\"25\",\"26\",\"27\",\"28\"]','2','zzzj_5cb5e2b6dcdb48f8','7',2,9,NULL),(384,0,1531796752201,'','提供Java存取数据库能力的包是（）','[\"java.sql\",\"java.awt\",\"java.lang\",\"java.swing\"]','1','jarb_01759a4d116746e3','7',2,9,NULL),(385,0,1531796752272,'','从下面四段（A，B，C，D）代码中选择出正确的代码段（）','[\"abstract class Name {\\n\\nprivate String name;\\n\\npublic abstract boolean isStupidName(String name) {}\\n\\n}\",\" public class Something {\\n\\nvoid doSomething () {\\n\\nprivate String s = ̶”;\\n\\nint l = s.length();\\n\\n}\\n\\n}\",\" public class Something {\\n\\npublic static void main(String[] args) {\\n\\nOther o = new Other();\\n\\nnew Something().addOne(o);\\n\\n}\\n\\npublic void addOne(final Other o) {\\n\\no.i++;\\n\\n}\\n\\n}\\n\\nclass Other {\\n\\npublic int i;\\n\\n}\",\" public class Something {\\n\\npublic int addOne(final int x) {\\n\\nreturn ++x; }\\n\\n}\"]','3','mddx_bb29cba2b3e84de8','7',2,9,NULL),(386,0,1531796752280,'','下面的哪些声明是合法的？（ ）','[\"long 1 = 499\",\"int i = 4L\",\"float f =1.1\",\"char d = 34.4\"]','1','jblx_8082ce3776c44345','7',2,9,NULL),(387,0,1531796752288,'','Java I/O程序设计中，下列描述正确的是','[\"OutputStream用于写操作\",\" InputStream用于写操作\",\" I/O库不支持对文件可读可写API\",\" inputstreamreader可以读写中文\"]','0','IOl_6a3dce4d903c4cbb','7',2,9,NULL),(388,0,1531796752294,'class Super { public int getLength() {return 4;}\n\n}\n\npublic class Sub extends Super { public long getLength() {return 5;}\n\npublic static void main (String[]args) {\n\nSuper sooper = new Super (); Super sub = new Sub(); System.out.printIn(sooper.getLength()+ “，” + sub.getLength() };\n\n}','下述代码的执行结果是','[\"4,4\",\"5,5\",\"4,5\",\".无法编译\"]','3','jc_399e6cc2553f4d84','7',2,9,NULL),(389,0,1531796752351,'','关于Java语言，下列描述正确的是','[\"switch 不能够作用在String类型上\",\" List， Set， Map都继承自Collection接口\",\" Java语言支持goto语句\",\" GC是垃圾收集器，程序员不用担心内存管理\"]','3','javajbzsd_bc2215d7065c4a06','7',2,9,NULL),(390,0,1531796752359,'','下列描述中，哪些符合Java语言的特征','[\"支持跨平台(Windows，Linux，Unix等)\",\" GC(自动垃圾回收)，降低了了代码安全性\",\" 支持类C的指针运算操作\",\" 不支持与其它语言书写的程序进行通讯\"]','0','javajbzsd_bc2215d7065c4a06','7',2,9,NULL),(391,0,1531796752364,'','编写方法，获取到数组中最大的元素，返回该元素的值',NULL,'','sz_9b5c1acce3124537','7',4,9,NULL),(392,0,1531796752370,'','成员变量与局部变量的区别？',NULL,'','mddx_bb29cba2b3e84de8','7',4,9,NULL),(393,0,1531796752376,'','静态代码块，构造代码块，构造方法的执行顺序是什么以及执行特点',NULL,'','mddx_bb29cba2b3e84de8','7',4,9,NULL),(394,0,1531796752383,'','请简述创建线程的3种方式',NULL,'','xc_907c2461db064a42','7',4,9,NULL),(395,0,1531796752392,'','请简述int和Integer有什么区别？',NULL,'','bzl_d4099519eb2d43a4','7',4,9,NULL),(396,0,1531796752398,'','是否可以继承String类？',NULL,'','jblx_8082ce3776c44345','7',4,9,NULL),(397,0,1531796752404,'public class Test\n{\npublic static void main(String arg[] )\n{\nint i = 5;\ndo{\nSystem.out.print(i);\n}while(–i>5)\nSystem.out.print(“finished”);\n}\n}','以下代码执行后的输出是什么？','[\"5\",\"4\",\"6\",\"finished\"]','0,3','xh_8e8304bd1cce44ec','7',3,9,NULL),(398,0,1531796752421,'','选择Java语言中的基本数据类型（ ）','[\"byte\",\"Integer\",\"String\",\"char\"]','0,3','jbsjlx_7fd27f4528204900','7',3,9,NULL),(399,0,1531796752436,'','从下列选项中选择正确的Java表达式（ ）','[\"int k=new String(“aa”)\",\"String str=String(“bb”)\",\"char c=74;\",\"long j=8888;\"]','1,2,3','sjlx_172bd8ea34874123','7',3,9,NULL),(400,0,1531796752448,'','如下哪些不是java的关键字？（ ）','[\"const\",\"TRUE\",\"FALSE\",\"this\"]','1,2,3','gjz_e3fad80dc8ef40c6','7',3,9,NULL),(401,0,1531796752461,'','Java网络程序设计中，下列正确的描述是（ ）','[\"Java网络编程API建立在Socket基础之上\",\"Java网络接口只支持TCP以及其上层协议\",\"Java网络接口只支持UDP以及其上层协议\",\"Java网络接口支持IP以上的所有高层协议\"]','0,3','JAVAWEB_e3b9ab9fe7734f06','7',3,9,NULL),(402,0,1531796752469,'','下列哪些是J2EE的体系（ ）','[\"JSP\",\"JAVA\",\"Servlet\",\"WebService\"]','0,2,3','j2ee_e9d9f3d2a5964d9c','7',3,9,NULL),(403,0,1531796752475,'','下面中哪两个可以在A的子类中使用：（ ）\nclass A {\nprotected int method1 (int a, int b) {\nreturn 0;\n}\n}\n','[\" public int method 1 (int a, int b) { return 0; }\",\"private int method1 (int a, int b) { return 0; }\",\" private int method1 (int a, long b) { return 0; }\",\"public short method1 (int a, int b) { return 0; }\"]','0,2,3','zx_7d5f861f42694043','7',3,9,NULL),(404,0,1531796752484,'','10. 下面关于变量及其范围的陈述哪些是不正确的（ ）','[\"实例变量是类的成员变量\",\"实例变量用关键字static声明\",\"在方法中定义的局部变量在该方法被执行时创建\",\"局部变量在使用前必须被初始化\"]','1,2,3','blfw_73e6a1fdd56a4397','7',3,9,NULL),(405,0,1531796752491,'','1.     执行下列代码后,哪个结论是正确的 String[] s=new String[10];','[\"s[9] 为 null;\",\"s[10] 为 “”;\",\"s[0] 为 未定义\",\"s.length 为10\"]','0,3','sz_9b5c1acce3124537','7',3,9,NULL),(406,0,1531796752497,'','运行jsp需要安装_______Web服务器。','[\"Apache\",\"tomcat\",\"WebLogic\",\"IIS\"]','1,2,3','JAVAWEB_e3b9ab9fe7734f06','7',3,9,NULL),(407,0,1531796752503,'','在服务器的网络编程中，解决会话跟踪的方法有：','[\"使用Cookie\",\"使用URL重写\",\"使用隐藏的表单域\",\"以上方法都不能单独使用\"]','0,1,2','JAVAWEB_e3b9ab9fe7734f06','7',3,9,NULL),(408,0,1531796752509,'','有关JSP隐式对象，以下（ ）描述正确','[\"隐式对象是WEB容器加载的一组类的实例，可以直接在JSP页面使用\",\"不能通过config对象获取ServletContext对象\",\"response对象通过sendRedirect方法实现重定向\",\"只有在出错处理页面才有exception对象\"]','0,2,3','JSP_3bbeab3be6ed4521','7',3,9,NULL),(409,0,1531796752516,'','String 和StringBuffer的区别',NULL,'','jblx_8082ce3776c44345','7',4,9,NULL),(410,0,1531796752524,'','运行时异常与一般异常有何异同',NULL,'','yc_6bff7e230b0d4d0b','7',4,9,NULL),(411,0,1531796752530,'','error和exception有什么区别',NULL,'','yc_6bff7e230b0d4d0b','7',4,9,NULL),(412,0,1531796752536,'','abstract class和interface有什么区别',NULL,'','jc_399e6cc2553f4d84','7',4,9,NULL),(413,0,1531796752543,'','forward 和redirect的区别',NULL,'','JAVAWEB_e3b9ab9fe7734f06','7',4,9,NULL),(414,0,1531796752551,'','数组有没有length()这个方法? String有没有length()这个方法',NULL,'','javajbzsd_bc2215d7065c4a06','7',4,9,NULL),(415,0,1531796752558,'','是否可以继承String类',NULL,'','javajbzsd_bc2215d7065c4a06','7',4,9,NULL),(416,0,1531796752567,'','用最有效率的方法算出2乘以8等於几',NULL,'','javajbzsd_bc2215d7065c4a06','7',4,9,NULL),(417,0,1531796752573,'','下面哪些类可以被继承?','[\"java.lang.Thread\",\"java.lang.Number\",\"java.lang.Double\",\"java.lang.Math\"]','0,1','jc_399e6cc2553f4d84','7',3,9,NULL),(418,0,1531796752580,'','写一个Singleton模式的例子',NULL,'','dl_4075a313bf4643b0','7',4,9,NULL),(419,0,1531796752588,'','下列哪一个不是spring框架提供的注解','[\"Compoennt\",\"Service\",\"Controller\",\"resource\"]','3','Spring_6bd6736923844ac2','7',2,9,NULL),(420,0,1531796752594,'','简述spring在web项目中的作用',NULL,'','Spring_6bd6736923844ac2','7',4,9,NULL),(421,0,1531796752602,'','下列哪一个不是mybatis提供的标签','[\"update\",\"insert\",\"query\",\"select\"]','2','MyBatis_29717578c71c4c85','7',2,9,NULL),(422,0,1531796752611,'','下列哪一个是springmvc使用的注解','[\"service\",\"requestparam\",\"resource\",\"component\"]','1','SpringMVC_daa6eb2195434540','7',2,9,NULL),(423,0,1531796752619,'','简述get请求方式和post请求方式的异同',NULL,'','JAVAWEB_e3b9ab9fe7734f06','7',4,9,NULL),(424,0,1531796752627,'','静态内部类与非静态内部类区别',NULL,'','javajbzsd_bc2215d7065c4a06','7',4,9,NULL),(425,0,1531796752634,'','简述IOC思想',NULL,'','Spring_6bd6736923844ac2','7',4,9,NULL),(426,0,1531796752639,'','简述springMVC的流程',NULL,'','SpringMVC_daa6eb2195434540','7',4,9,NULL),(427,0,1531796752645,'','下面哪些是Thread类的方法','[\"start\",\"run\",\"exit\",\"getPriority\"]','0,1,2','xc_907c2461db064a42','7',3,9,NULL),(428,0,1531796752652,'public static void main(String args[]) {\n\n        Thread t = new Thread() {\n\n            public void run() {\n                pong();\n            }\n        };\n\n        t.run();\n        System.out.print(\"ping\");\n\n    }\n\n    static void pong() {\n\n        System.out.print(\"pong\");\n\n    }','下面程序的输出结果','[\"pingpong\",\"pongping\",\"pingpong和pongping都有可能\",\"都不输出\"]','1','xc_907c2461db064a42','7',2,9,NULL),(429,0,1531796752658,'','下列属于关系型数据库的是','[\"oralce\",\"mysql\",\"ims\",\"mangondb\"]','0,1','xc_907c2461db064a42','7',3,9,NULL),(430,0,1531796752665,'','0.6332的数据类型是','[\"float\",\"double\",\"Float\",\"Doubel\"]','1','javajbzsd_bc2215d7065c4a06','7',2,9,NULL),(431,0,1531796752675,'public static int getValue(int i) {\n        int result = 0;\n        switch (i) {\n        case 1:\n            result = result + i;\n        case 2:\n            result = result + i * 2;\n        case 3:\n            result = result + i * 3;\n        }\n        return result;\n    }','下面的方法，当输入为2的时候返回值是多少?','[\"0\",\"2\",\"4\",\"10\"]','3','javajbzsd_bc2215d7065c4a06','7',2,9,NULL),(432,0,1531796752684,'class Shape {\n       public String name;\n}\n\nclass Circle extends Shape implements Serializable{\n\n       private float radius;\n\n       transient int color;\n\n       public static String type = \"Circle\";\n\n}\n','在序列化一个Circle的对象circle到文件时，下面哪个字段会被保存到文件中？','[\"name\",\"radius\",\"color\",\"type\"]','1','javajbzsd_bc2215d7065c4a06','7',2,9,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (2,'单选题',2.5),(3,'多选题',5),(4,'简答题',10);
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` varchar(32) NOT NULL,
  `jobName` varchar(255) DEFAULT NULL,
  `jobGroup` varchar(255) DEFAULT NULL,
  `jobStatus` int(11) DEFAULT '1' COMMENT '0禁用 1启用 2删除 -1一次性任务',
  `cronexpression` varchar(20) DEFAULT NULL,
  `describes` text,
  `targetVo_id` char(32) DEFAULT NULL,
  `jobType` int(11) DEFAULT NULL COMMENT '0开始  1关闭',
  `affterStatu` int(11) DEFAULT NULL,
  `nowStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('040869766e4f47139dfae157e270de96','woshi_考试结束','16',0,'15 59 15 6 7 ? 2018','定时考试结束.ExamId：16','16',2,NULL,NULL),('0ef7dde3cb4b4bad852ef148038f9ba3','河海大学_考试结束','7',0,'44 39 22 15 6 ? 2018','定时考试结束.ExamId：7','7',2,NULL,NULL),('116830a722dd432fb913b5876c9c6df5','root_考试结束','18',0,'45 48 20 17 7 ? 2018','定时考试结束.ExamId：18','18',2,NULL,NULL),('1ec7b516df0e403b95320c703bf44c2b','我是考试_关闭考试入口','15',0,'9 29 15 6 7 ? 2018','定时关闭考试入口.ExamId：15','15',1,NULL,NULL),('27871c1678594302963f33a20137ed9c','基本知识点啊_考试结束','14',0,'1 51 22 25 6 ? 2018','定时考试结束.ExamId：14','14',2,NULL,NULL),('42dcde02c9434fb7a857cce7a3a769df','java入门测试_考试结束','8',0,'17 58 22 15 6 ? 2018','定时考试结束.ExamId：8','8',2,NULL,NULL),('5908bdb23bda4562b0ad3304bf33032e','Java高级测试_考试结束','10',0,'14 59 22 15 6 ? 2018','定时考试结束.ExamId：10','10',2,NULL,NULL),('6d0aa16280d04af9a9d50f031374e572','欢迎页_考试结束','13',0,'17 46 15 23 6 ? 2018','定时考试结束.ExamId：13','13',2,NULL,NULL),('a30b7ba7a2d74c5e96d9a9043317b189','woshi_关闭考试入口','16',0,'15 59 15 6 7 ? 2018','定时关闭考试入口.ExamId：16','16',1,NULL,NULL),('a32cab196f34471e8f714fc2f9e43b3c','我是考试_考试结束','15',0,'9 29 15 6 7 ? 2018','定时考试结束.ExamId：15','15',2,NULL,NULL),('bfadba49d28845a2b29ee170facf9547','欢迎页_考试结束','12',0,'38 45 15 23 6 ? 2018','定时考试结束.ExamId：12','12',2,NULL,NULL),('c7125725d5ec4fda9ff56743b82183ce','ytest_考试结束','17',0,'21 8 17 18 7 ? 2018','定时考试结束.ExamId：17','17',2,NULL,NULL),('c8250e3614c84ce4b4402c35c17769ba','root_开启考试入口','18',0,'33 48 20 17 7 ? 2018','定时开启考试入口.ExamId：18','18',0,NULL,NULL),('de78abe3024644758cec047827c74a2b','root_关闭考试入口','19',3,'34 54 20 17 7 ? 2018','定时关闭考试入口.ExamId：19','19',1,NULL,NULL),('e0c68d77ecf147e5a5c1f30f47584166','root_开启考试入口','19',3,'22 54 20 17 7 ? 2018','定时开启考试入口.ExamId：19','19',0,NULL,NULL),('ea1808dd5ff141cbb5b3ffc42f77005d','root_关闭考试入口','18',0,'45 48 20 17 7 ? 2018','定时关闭考试入口.ExamId：18','18',1,NULL,NULL),('eda1b79e21874426a2aa152a7582a307','端午考试_考试结束','11',1,'4 35 18 21 6 ? 2018','定时考试结束.ExamId：11','11',2,NULL,NULL),('eeda70907ab3432a828ca77ac3435cd7','Java高级测试_考试结束','9',0,'14 59 22 14 6 ? 2018','定时考试结束.ExamId：9','9',2,NULL,NULL),('fae58ce7bacc4d1085d190c7e9c4763d','root_考试结束','19',3,'34 54 20 17 7 ? 2018','定时考试结束.ExamId：19','19',2,NULL,NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'蜀中皇家学院'),(2,'河海大学'),(3,'魏家帝国大学'),(4,'吴川理工学院'),(5,'香港大学'),(6,'黄埔军校'),(7,'霍格沃兹'),(8,'麻省理工学院'),(9,'普林斯顿大学'),(10,'苏州大学'),(11,'南京大学1'),(13,'徐海学院');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `id_card_no` char(18) DEFAULT NULL,
  `telephone` char(11) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `last_login_time` bigint(20) DEFAULT NULL,
  `school_id` int(16) DEFAULT NULL,
  `classroom_id` int(16) DEFAULT NULL,
  `last_login_ip` char(15) DEFAULT NULL,
  `createtime` bigint(20) DEFAULT NULL,
  `modifytime` bigint(20) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vm0oqhk9viil6eocn49rj1l9` (`school_id`),
  KEY `FK1rs4md9whkjqy20v181d18kfy` (`classroom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (70,'22140283','刘楚右','','','','2af27f5792803eecbdf202ba3f41299b',0,13,15,'-',1531818271828,0,'2',0),(71,'22150414','倪猛','','','','e52ed0f324172b7b124728342f94ff2c',0,13,15,'-',1531818271830,0,'2',0),(72,'22150416','戎超','','','','4caecf7acb6141f20bbaaaf865c0de2b',0,13,15,'-',1531818271832,0,'2',0),(73,'22150440','葛琪琪','','','','d121aaffabec786c24cddd0f8b1e9e4d',0,13,15,'-',1531818271833,0,'2',0),(74,'22150443','鞠骥','','','','c5b5d563de08e385f99a6c400a8a3c56',0,13,15,'-',1531818271835,0,'2',0),(75,'22150445','刘依宁','','','','d3eec9bb403d31c2a4101345940ae899',0,13,15,'-',1531818271837,0,'2',0),(76,'22150449','邵林','','','','4789017a9972aeaa3295b1cec9305783',0,13,15,'-',1531818271841,0,'2',0),(77,'22150450','施文杰','','','','a9e41eb5d2b4826d738a44369d836fd1',0,13,15,'-',1531818271843,0,'2',0),(78,'22150452','田楷文','','','','edc6fb3b7878c02e2e0f45f89af03cb2',0,13,15,'-',1531818271844,0,'2',0),(79,'22150460','周海坤','','','','9e4b70e69437936313cf8688de5bdb94',0,13,15,'-',1531818271846,0,'2',0),(80,'22150473','郭文庆','','','','096642f115b5ca270d9ba05e3ba4a023',0,13,15,'-',1531818271847,0,'2',0),(81,'22150478','刘泽蒙','','','','ec3513dfaa93fad721e375507c827cd7',0,13,15,'-',1531818271849,0,'2',0),(82,'22150485','田鑫','','','','04e7aaf5d430f2783b25fa997fa87e2c',0,13,15,'-',1531818271851,0,'2',0),(83,'22150496','邱思雨','','','','5a67ee15c7f660d13586f0c2c93cffd7',0,13,15,'-',1531818271853,0,'2',1),(84,'22150498','王晓','','','','ee0916fcd1d92fdb3eadb1db213b3bde',0,13,15,'-',1531818271858,0,'2',1),(85,'22150501','张雨馨','','','','63abcdbc1e59a13cdf657256adfce7c1',1531828679155,13,15,'0:0:0:0:0:0:0:1',1531818271860,0,'2',1),(86,'22150502','闵海蓉','','','','75a379a7d8acf483d78fc4f1c1d066a9',1531818312918,13,15,'0:0:0:0:0:0:0:1',1531818271861,0,'2',1),(87,'22150503','陈航','','','','139b39b4ad5280238603b48c526809cb',0,13,15,'-',1531818271863,0,'2',0),(88,'22150511','刘铠源','','','','5e3e9c3fb8684a38e4431332b507457a',0,13,15,'-',1531818271866,0,'2',0),(89,'22150512','明瑞君','','','','8c79ec7d584d5f5546fd51c697e432b8',0,13,15,'-',1531818271868,0,'2',0),(90,'22150513','牛宇','','','','83c3970ae41fdeacd46e41950d863fa6',0,13,15,'-',1531818271870,0,'2',0),(91,'22150522','夏天','','','','dc38da491f49350ca13e933b29f9e664',0,13,15,'-',1531818271877,0,'2',0),(92,'22150530','王孟仙','','','','04d128d41b637032c0f0858b242e3975',0,13,15,'-',1531818271878,0,'2',1),(93,'22150533','袁野','','','','898195109353627828b43abf97c815f5',0,13,15,'-',1531818271880,0,'2',1),(94,'22000001','李中卫','','','','52e3b3077c283e2d6a1f07331c293ef9',0,13,15,'-',1531818271882,0,'2',0),(95,'22000002','石金磊','','','','dfd30f77366bb68a7712ca892b75b402',0,13,15,'-',1531818271884,0,'2',0),(96,'22000003','付凡萌','','','','a7226537f493bd179188fd265230f009',0,13,15,'-',1531818271896,0,'2',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_exam`
--

DROP TABLE IF EXISTS `student_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `student_id` int(16) NOT NULL,
  `exam_id` int(16) NOT NULL,
  `start_time` bigint(20) DEFAULT NULL,
  `submit_time` bigint(20) DEFAULT NULL,
  `totalScore` float DEFAULT NULL,
  `answer_content` mediumtext,
  `state` int(11) DEFAULT '0' COMMENT '学生操作：0 未参加 1 答题中 2 已提交',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_exam`
--

LOCK TABLES `student_exam` WRITE;
/*!40000 ALTER TABLE `student_exam` DISABLE KEYS */;
INSERT INTO `student_exam` VALUES (81,5,13,1529669897099,NULL,47,NULL,1),(82,6,13,NULL,NULL,0,NULL,0),(83,7,13,NULL,NULL,0,NULL,0),(84,8,13,NULL,NULL,0,NULL,0),(89,5,14,1529851902085,1530516610900,59.5,NULL,1),(90,6,14,NULL,NULL,0,NULL,0),(91,7,14,1529852036295,1530516610900,57.5,NULL,1),(92,8,14,NULL,NULL,0,NULL,0),(98,2,15,1530863640157,NULL,NULL,NULL,1),(99,37,15,NULL,NULL,NULL,NULL,0),(100,5,15,1530862127489,1530862839260,NULL,NULL,2),(101,6,15,1530863570839,1530863602504,NULL,NULL,2),(102,8,15,NULL,NULL,NULL,NULL,0),(105,2,16,1530863912959,1530864028340,50,NULL,2),(106,37,16,1530864095952,1530864173419,65.5,NULL,2),(134,70,17,NULL,NULL,NULL,NULL,0),(135,71,17,NULL,NULL,NULL,NULL,0),(136,72,17,NULL,NULL,NULL,NULL,0),(137,73,17,NULL,NULL,NULL,NULL,0),(138,74,17,NULL,NULL,NULL,NULL,0),(139,75,17,NULL,NULL,NULL,NULL,0),(140,76,17,NULL,NULL,NULL,NULL,0),(141,77,17,NULL,NULL,NULL,NULL,0),(142,78,17,NULL,NULL,NULL,NULL,0),(143,79,17,NULL,NULL,NULL,NULL,0),(144,80,17,NULL,NULL,NULL,NULL,0),(145,81,17,NULL,NULL,NULL,NULL,0),(146,82,17,NULL,NULL,NULL,NULL,0),(147,83,17,NULL,NULL,NULL,NULL,0),(148,84,17,NULL,NULL,NULL,NULL,0),(149,85,17,1531828702156,NULL,NULL,NULL,1),(150,86,17,1531818526561,NULL,NULL,NULL,1),(151,87,17,NULL,NULL,NULL,NULL,0),(152,88,17,NULL,NULL,NULL,NULL,0),(153,89,17,NULL,NULL,NULL,NULL,0),(154,90,17,NULL,NULL,NULL,NULL,0),(155,91,17,NULL,NULL,NULL,NULL,0),(156,92,17,NULL,NULL,NULL,NULL,0),(157,93,17,NULL,NULL,NULL,NULL,0),(158,94,17,NULL,NULL,NULL,NULL,0),(159,95,17,NULL,NULL,NULL,NULL,0),(160,96,17,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `student_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_exam_question`
--

DROP TABLE IF EXISTS `student_exam_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam_question` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '此表仅作批改时使用,不作数据永久存储使用',
  `student_exam_id` int(16) NOT NULL,
  `index_` int(11) NOT NULL,
  `teacher_id` int(16) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `questionType` int(11) DEFAULT NULL,
  `workout` text,
  `answer` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_exam_question`
--

LOCK TABLES `student_exam_question` WRITE;
/*!40000 ALTER TABLE `student_exam_question` DISABLE KEYS */;
INSERT INTO `student_exam_question` VALUES (252,150,0,NULL,NULL,NULL,2,'2','2'),(253,150,1,NULL,NULL,NULL,2,'0','1'),(254,150,2,NULL,NULL,NULL,2,'0','3'),(255,150,3,NULL,NULL,NULL,2,'1','1'),(256,150,4,NULL,NULL,NULL,2,'2','0'),(257,150,5,NULL,NULL,NULL,2,'1','3'),(258,150,6,NULL,NULL,NULL,2,'2','3'),(259,150,7,NULL,NULL,NULL,2,NULL,'0'),(260,150,8,NULL,NULL,NULL,2,'3','3'),(261,150,9,NULL,NULL,NULL,2,NULL,'2'),(262,150,10,NULL,NULL,NULL,2,NULL,'1'),(263,150,11,NULL,NULL,NULL,2,NULL,'1'),(264,150,12,NULL,NULL,NULL,2,'2','1'),(265,150,13,NULL,NULL,NULL,2,NULL,'3'),(266,150,14,NULL,NULL,NULL,2,NULL,'1'),(267,150,15,NULL,NULL,NULL,3,NULL,'0,3'),(268,150,16,NULL,NULL,NULL,3,NULL,'0,3'),(269,150,17,NULL,NULL,NULL,3,NULL,'1,2,3'),(270,150,18,NULL,NULL,NULL,3,NULL,'1,2,3'),(271,150,19,NULL,NULL,NULL,3,NULL,'0,3'),(272,150,20,NULL,NULL,NULL,3,NULL,'0,2,3'),(273,150,21,NULL,NULL,NULL,3,NULL,'0,2,3'),(274,150,22,NULL,NULL,NULL,3,NULL,'1,2,3'),(275,150,23,NULL,NULL,NULL,3,NULL,'0,3'),(276,150,24,NULL,NULL,NULL,3,NULL,'1,2,3'),(277,150,25,NULL,NULL,NULL,3,NULL,'0,1,2'),(278,150,26,NULL,NULL,NULL,3,NULL,'0,2,3'),(279,150,27,NULL,NULL,NULL,3,NULL,'0,1'),(280,150,28,NULL,NULL,NULL,3,NULL,'0,1,2'),(281,150,29,NULL,NULL,NULL,3,NULL,'0,1'),(282,150,30,0,8,'',4,NULL,''),(283,150,31,0,10,'',4,NULL,''),(284,150,32,0,3,'',4,NULL,''),(285,150,33,0,2.5,'',4,NULL,''),(286,150,34,0,3,'',4,NULL,''),(287,150,35,0,2,'',4,NULL,''),(288,150,36,0,2,'',4,NULL,''),(289,150,37,0,2.5,'',4,NULL,''),(290,150,38,0,3,'',4,NULL,''),(291,150,39,0,2.5,'',4,NULL,''),(292,150,40,0,2.5,'',4,NULL,''),(293,150,41,0,2,'',4,NULL,''),(294,150,42,0,2.5,'',4,NULL,''),(295,150,43,0,2.5,'',4,NULL,''),(296,150,44,0,2.5,'',4,NULL,''),(297,150,45,0,2,'',4,NULL,''),(298,150,46,0,2.5,'',4,NULL,''),(299,150,47,0,2.5,'',4,NULL,''),(300,150,48,0,2.5,'',4,NULL,''),(301,150,49,0,2.5,'',4,NULL,''),(302,149,0,NULL,NULL,NULL,2,'2','2'),(303,149,1,NULL,NULL,NULL,2,'0','1'),(304,149,2,NULL,NULL,NULL,2,'2','3'),(305,149,3,NULL,NULL,NULL,2,'0','1'),(306,149,4,NULL,NULL,NULL,2,'0','0'),(307,149,5,NULL,NULL,NULL,2,'3','3'),(308,149,6,NULL,NULL,NULL,2,'3','3'),(309,149,7,NULL,NULL,NULL,2,'0','0'),(310,149,8,NULL,NULL,NULL,2,'3','3'),(311,149,9,NULL,NULL,NULL,2,'2','2'),(312,149,10,NULL,NULL,NULL,2,'1','1'),(313,149,11,NULL,NULL,NULL,2,'1','1'),(314,149,12,NULL,NULL,NULL,2,'1','1'),(315,149,13,NULL,NULL,NULL,2,'3','3'),(316,149,14,NULL,NULL,NULL,2,'1','1'),(317,149,15,NULL,NULL,NULL,3,'0,3,','0,3'),(318,149,16,NULL,NULL,NULL,3,'0,1,3,','0,3'),(319,149,17,NULL,NULL,NULL,3,'2,3,','1,2,3'),(320,149,18,NULL,NULL,NULL,3,'0,','1,2,3'),(321,149,19,NULL,NULL,NULL,3,'0,3,','0,3'),(322,149,20,NULL,NULL,NULL,3,'0,2,3,','0,2,3'),(323,149,21,NULL,NULL,NULL,3,'0,2,','0,2,3'),(324,149,22,NULL,NULL,NULL,3,'1,2,3,','1,2,3'),(325,149,23,NULL,NULL,NULL,3,'0,3,','0,3'),(326,149,24,NULL,NULL,NULL,3,'1,2,3,','1,2,3'),(327,149,25,NULL,NULL,NULL,3,'0,1,2,','0,1,2'),(328,149,26,NULL,NULL,NULL,3,'0,2,3,','0,2,3'),(329,149,27,NULL,NULL,NULL,3,'0,1,','0,1'),(330,149,28,NULL,NULL,NULL,3,'0,1,2,','0,1,2'),(331,149,29,NULL,NULL,NULL,3,'0,1,','0,1'),(332,149,30,0,10,'',4,'public int getMax(int[] arrays){\n   int max=0;\n   for(int i : arrays){\n      if(i>max){max=I;}\n   }\n   return max;\n}',''),(333,149,31,0,10,'',4,NULL,''),(334,149,32,0,6,'',4,NULL,''),(335,149,33,0,4.5,'',4,NULL,''),(336,149,34,0,3.5,'',4,NULL,''),(337,149,35,0,4,'',4,'不可以',''),(338,149,36,0,4.5,'',4,NULL,''),(339,149,37,0,4,'',4,NULL,''),(340,149,38,0,4,'',4,NULL,''),(341,149,39,0,4,'',4,NULL,''),(342,149,40,0,3.5,'',4,NULL,''),(343,149,41,0,3.5,'',4,NULL,''),(344,149,42,0,4,'',4,NULL,''),(345,149,43,0,3.5,'',4,NULL,''),(346,149,44,0,4,'',4,NULL,''),(347,149,45,0,3.5,'',4,NULL,''),(348,149,46,0,2.5,'',4,NULL,''),(349,149,47,0,2.5,'',4,NULL,''),(350,149,48,0,2,'',4,NULL,''),(351,149,49,0,2,'',4,NULL,'');
/*!40000 ALTER TABLE `student_exam_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (9,'Java'),(10,'斗地主'),(11,'语文');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` char(32) NOT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `id_card_no` char(18) DEFAULT NULL,
  `telephone` char(11) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `last_login_time` bigint(20) DEFAULT NULL,
  `last_login_ip` char(15) DEFAULT NULL,
  `createtime` bigint(20) DEFAULT NULL,
  `modifytime` bigint(20) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('0','0001','鹿林','1231234564@qq.com','320502199705152254','18694907637','202cb962ac59075b964b07152d234b70',1531818114561,'0:0:0:0:0:0:0:1',NULL,1531098877242,'0',1),('1','00010110','诸葛孔明','wolong@126.com','320503197110070521','13890650001','202cb962ac59075b964b07152d234b70',1526714776372,'0:0:0:0:0:0:0:1',1524573810090,1530929329099,'1',0),('2','2115110220','温小花','moonkop@qq.com','320502199705152254','18694907636','65778349c6817c2574aa6a8e2208d874',1526037154020,'0:0:0:0:0:0:0:1',1524626052241,1526037139831,'1',1),('3','0002','夏岩','xiayan@123.com','6587512264532',NULL,'123',NULL,NULL,NULL,1524464305342,'1',NULL),('4','0009','马孟起','chao@132.com','33426394000000003','13890650001','29549a71a57f587d88209b9c1f1b7999',0,'-',1524573813259,NULL,'1',0),('5','0010','徐元直1','shu@133.com','320502199705152254','13890650001','fc1198178c3594bfdda3ca2996eb65cb',0,'-',1524573813687,1530860994197,'1',0),('7','009','夏方元',NULL,NULL,NULL,'202cb962ac59075b964b07152d234b70',1526043959940,'0:0:0:0:0:0:0:1',NULL,NULL,'1',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` char(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_topic_id` char(32) DEFAULT NULL,
  `subject_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES ('03b058741b7242fc9e085f2757450ec6','Switch语句','fzjg_102827524219452e',9),('123_307b7a3e3b3a4031','123','naizi _fc296f3bea6b465b',11),('23_4fd148be4096480c','23',NULL,10),('bdslxdzdts_2946a447b548470b','表达式类型的自动提升','jblxdlxzh_8595a46565f64880',9),('bex_9184cefb85db4c64','布尔型','jbsjlx_7fd27f4528204900',9),('bjysf_44043dc45f9440f0','比较运算符','ysf_715f5848ffc4463f',9),('bldsygz_1d4c1c8a48064ef7','变量的使用规则','cyblhjbbl_15efa0af3fbb450c',9),('blfw_73e6a1fdd56a4397','变量范围','java',9),('bzl_d4099519eb2d43a4','包装类','java',9),('ccb974653dd14498b437d5a827efc720','NEW','gjz_e3fad80dc8ef40c6',9),('cshk_a0617415f70c4f01','初始化块','mddx_bb29cba2b3e84de8',9),('cybldcsh_08eb6819d26048c4','成员变量的初始化','cyblhjbbl_15efa0af3fbb450c',9),('cyblhjbbl_15efa0af3fbb450c','成员变量和局部变量','mddx_bb29cba2b3e84de8',9),('delete_3938183d67094077','DELETE','gjz_e3fad80dc8ef40c6',9),('dgff_490e4fd6c7f748df','递归方法','ffxj_593ed15cd1e04fa8',9),('dl_4075a313bf4643b0','单例','java',9),('do whilexhyj_1d71394d21ab4c3d','DO WHILE循环语句','xhjg_352afffe753d41ef',9),('dtx_a57e444eb3d2411c','多态性','mddx_bb29cba2b3e84de8',9),('dt_ac24096075a641ef','多态','mddx_bb29cba2b3e84de8',9),('dxdcshsy_a86f61ccba844c61','对象的产生和使用','lhdx_5b5001dafe3e4a9c',9),('dxdthisyy_f3fe829322d6486b','对象的this引用','lhdx_5b5001dafe3e4a9c',9),('dyflgzq_971d19d4fe304451','调用父类构造器','ldjc_27c9d150b1ef4617',9),('dyl_39d478eef7884af9','定义类','lhdx_5b5001dafe3e4a9c',9),('fdx_021b19da071a4116','浮点型','jbsjlx_7fd27f4528204900',9),('ffdcscdjz_cbbc585a94d147c6','方法的参数传递机制','ffxj_593ed15cd1e04fa8',9),('ffdssx_5dae734bf1964b14','方法的所属性','ffxj_593ed15cd1e04fa8',9),('ffdzz_9d5408a585304cca','方法的重载','ffxj_593ed15cd1e04fa8',9),('ffxj_593ed15cd1e04fa8','方法详解','mddx_bb29cba2b3e84de8',9),('forxh_ab8ac4564837470d','for循环','xhjg_352afffe753d41ef',9),('fzjg_102827524219452e','分支结构','lckzysz_69cf976d602d4387',9),('fzysf_0f87bc409c514bbb','赋值运算符','ysf_715f5848ffc4463f',9),('gjz_e3fad80dc8ef40c6','关键字','java',9),('gzqzz_850fb69583d34d85','构造器重载','srgzq_eab06753d2df494d',9),('iftjyj_205a4683e15b4004','IF条件语句','lckzysz_69cf976d602d4387',9),('instanceofysf_31e646838a884e08','INSTANCEOF运算符','dt_ac24096075a641ef',9),('IOl_6a3dce4d903c4cbb','IO流','java',9),('j2ee_e9d9f3d2a5964d9c','J2EE','java',9),('jarb_01759a4d116746e3','JAR包','java',9),('java','JAVA',NULL,9),('javacyb_605ed32c68fa4953','JAVA常用包','yzhfz_1b002b1ec48b4f8b',9),('Javafzs_5ff7ebb43c4a4c5a','JAVA发展史','java',9),('javaGCjz_c5e95912e0db4512','JAVAGC机制','java',9),('javajbzsd_bc2215d7065c4a06','JAVA基本知识点','java',9),('javascript_8bcf90b016bb4300','JAVASCRIPT','JAVAWEB_e3b9ab9fe7734f06',9),('javasjms_7f07d371cb6c4f56','JAVA设计模式','java',9),('JAVAWEB_e3b9ab9fe7734f06','JAVAWEB','java',9),('jblxdlxzh_8595a46565f64880','基本类型的类型转换','sjlx_172bd8ea34874123',9),('jblx_8082ce3776c44345','基本类型','javajbzsd_bc2215d7065c4a06',9),('jbsjlx_7fd27f4528204900','基本数据类型','sjlx_172bd8ea34874123',9),('jcdtd_ca124e79a36a423e','继承的特点','ldjc_27c9d150b1ef4617',9),('jcyzh_60dea83731f742db','继承与组合','mddx_bb29cba2b3e84de8',9),('jc_399e6cc2553f4d84','继承','mddx_bb29cba2b3e84de8',9),('jk_1954ef849f524472','接口','mddx_bb29cba2b3e84de8',9),('JSP_3bbeab3be6ed4521','JSP','JAVAWEB_e3b9ab9fe7734f06',9),('kj_cc35bf1ee29c451e','框架','java',9),('kzhdfzysf_520db2fd55a84056','扩展后的赋值运算符','ysf_715f5848ffc4463f',9),('kzxhjg_38de2e9de3b54d51','控制循环结构','xhjg_352afffe753d41ef',9),('lckzysz_69cf976d602d4387','流程控制与数组','javajbzsd_bc2215d7065c4a06',9),('ldjc_27c9d150b1ef4617','类的继承','mddx_bb29cba2b3e84de8',9),('lhdx_5b5001dafe3e4a9c','类和对象','mddx_bb29cba2b3e84de8',9),('ljfz_dd24f7d621724cc6','理解封装','yzhfz_1b002b1ec48b4f8b',9),('ljysf_4aa45fd8c4334aae','逻辑运算符','ysf_715f5848ffc4463f',9),('mddx_bb29cba2b3e84de8','面向对象','javajbzsd_bc2215d7065c4a06',9),('MyBatis_29717578c71c4c85','MYBATIS','kj_cc35bf1ee29c451e',9),('naizi _fc296f3bea6b465b','NAIZI ',NULL,11),('nz_c347ac733ee4418a','柰子','java',9),('qtxh_a29f2538b3a04390','嵌套循环','xhjg_352afffe753d41ef',9),('qzlxzh_61a192c6221849d8','强制类型转换','jblxdlxzh_8595a46565f64880',9),('sjlx_172bd8ea34874123','数据类型','java',9),('smysf_ed154445487a4fb8','三目运算符','ysf_715f5848ffc4463f',9),('SpringMVC_daa6eb2195434540','SPRINGMVC','kj_cc35bf1ee29c451e',9),('Spring_6bd6736923844ac2','SPRING','kj_cc35bf1ee29c451e',9),('srgzq_eab06753d2df494d','深入构造器','mddx_bb29cba2b3e84de8',9),('srsz_7203632a1912406b','深入数组','sz_9b5c1acce3124537',9),('ssysf_7b6392966bf7427f','算术运算符','ysf_715f5848ffc4463f',9),('superxd_57c98f0209a74082','SUPER限定','ldjc_27c9d150b1ef4617',9),('sxjg_0d2cca011b4d41b8','顺序结构','lckzysz_69cf976d602d4387',9),('sybreakjsxh_d82ba554f8274b06','使用break结束循环','kzxhjg_38de2e9de3b54d51',9),('syfwkzf_6b2fa6536d254d12','使用访问控制符','yzhfz_1b002b1ec48b4f8b',9),('sygzqzxcsh_158cb157c7eb45c3','使用构造器执行初始化','srgzq_eab06753d2df494d',9),('szlx_634fc840758047cf','数组类型','sz_9b5c1acce3124537',9),('sz_9b5c1acce3124537','数组','javajbzsd_bc2215d7065c4a06',9),('txjm_a1867cb08e204c97','图形界面','java',9),('whilexhyj_bed5f9fc9deb4fb8','WHILE循环语句','xhjg_352afffe753d41ef',9),('wysf_384f833f10494f55','位运算符','ysf_715f5848ffc4463f',9),('xcgsdkbff_44c0961c78ec4d3a','形参个数的可变方法','ffxj_593ed15cd1e04fa8',9),('xc_907c2461db064a42','线程','javajbzsd_bc2215d7065c4a06',9),('xhjg_352afffe753d41ef','循环结构','lckzysz_69cf976d602d4387',9),('xh_8e8304bd1cce44ec','循环','xhjg_352afffe753d41ef',9),('yc_6bff7e230b0d4d0b','异常','java',9),('ysf_715f5848ffc4463f','运算符','javajbzsd_bc2215d7065c4a06',9),('yw_827dd4d9ba4e4173','语文',NULL,11),('yybldqzlxzh_92f7afb7aebc4d8d','引用变量的强制类型转换','dt_ac24096075a641ef',9),('yzhfz_1b002b1ec48b4f8b','隐藏和封装','mddx_bb29cba2b3e84de8',9),('zdlxzh_e157a4f6cc804d3f','自动类型转换','jblxdlxzh_8595a46565f64880',9),('zfx_c66610533dcf4060','字符型','jbsjlx_7fd27f4528204900',9),('zxfldff_ee8fa266d7934239','重写父类的方法','ldjc_27c9d150b1ef4617',9),('zx_56f6cb621b19475a','整型','jbsjlx_7fd27f4528204900',9),('zx_7d5f861f42694043','重写','mddx_bb29cba2b3e84de8',9),('zzzj_5cb5e2b6dcdb48f8','自增自减','ysf_715f5848ffc4463f',9);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tresource`
--

DROP TABLE IF EXISTS `tresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tresource` (
  `ID` varchar(36) NOT NULL,
  `ICON` varchar(100) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `TRESOURCETYPE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tresource`
--

LOCK TABLES `tresource` WRITE;
/*!40000 ALTER TABLE `tresource` DISABLE KEYS */;
INSERT INTO `tresource` VALUES ('7ed80ac9a0b14fb0ad5b01d2e04d7b90','fa-user','学生管理','',80100,'/student/manage','xtgl','0'),('b89c8eeea7ed4869820a7a3be974fee7','fa-user-secret','教师管理','',80200,'/teacher/manage','xtgl','0'),('classroom_manage','fa-users','班级管理','',80300,'/manage/classroom','xtgl','0'),('createPaper','fa-folder-open','我的试卷','',2,'/paper','xtcd','0'),('exam_manage_detail','','查看详情','',100,'/exam/manage/detail','myExam','1'),('jsgl','fa-unlock-alt','角色管理','',80500,'/manage/role','xtgl','0'),('jsglAdd',NULL,'教师添加/编辑页面','',80201,'/teacher/manage/edit','b89c8eeea7ed4869820a7a3be974fee7','1'),('jsglAddPage',NULL,'角色添加/编辑页面','',80501,'/manage/role/edit','jsgl','1'),('jsglDelete',NULL,'角色删除','',80504,'/manage/role/delete','jsgl','1'),('jsglEdit',NULL,'角色详情','',80503,'/manage/role/detail','jsgl','1'),('jsglGrantPage',NULL,'角色添加/编辑','',80502,'/manage/role/edit.do','jsgl','1'),('manage_bank_question',NULL,'题目管理','',90400,'/manage/bank/question','tkgl','0'),('manage_bank_questionType',NULL,'题目类型管理','',90300,'/manage/bank/questionType','tkgl','0'),('manage_bank_subject',NULL,'科目管理','',90100,'/manage/bank/subject','tkgl','0'),('manage_bank_topic',NULL,'知识点管理','',90200,'/manage/bank/topic','tkgl','0'),('manage_classroom_delete',NULL,'班级删除','',80304,'/manage/classroom/delete','classroom_manage','1'),('manage_classroom_detail',NULL,'班级详情','',80303,'/manage/classroom/detail','classroom_manage','1'),('manage_classroom_edit',NULL,'班级添加/编辑页面','',80301,'/manage/classroom/edit','classroom_manage','1'),('manage_classroom_edit.do',NULL,'班级添加/编辑','',80302,'/manage/classroom/edit.do','classroom_manage','1'),('manage_resource','fa-wrench','资源管理','',80600,'/manage/resource','xtgl','0'),('manage_resource_delete',NULL,'资源删除','',80604,'/manage/resource/delete','manage_resource','1'),('manage_resource_detail',NULL,'资源详情','',80603,'/manage/resource/detail','manage_resource','1'),('manage_resource_edit',NULL,'资源添加/编辑页面','',80601,'/manage/resource/edit','manage_resource','1'),('manage_resource_edit.do',NULL,'资源添加/编辑','',80602,'/manage/resource/edit.do','manage_resource','1'),('manage_school_delete.do',NULL,'学校删除','',80404,'/manage/school/delete.do','school_manage','1'),('manage_school_detail',NULL,'学校详情','',80403,'/manage/school/detail','school_manage','1'),('manage_school_edit',NULL,'学校添加/编辑页面','',80401,'/manage/school/edit','school_manage','1'),('manage_school_edit.do',NULL,'学校添加/编辑','',80402,'/manage/school/edit.do','school_manage','1'),('myExam','fa-copy','我的考试','',4,'/exam/manage','xtcd','0'),('school_manage','fa-institution','学校管理','',80400,'/manage/school','xtgl','0'),('student_manage_delete.do',NULL,'学生删除','',80106,'/student/manage/delete.do','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_detail',NULL,'学生详情','',80103,'/student/manage/detail','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_edit','','学生添加/编辑页面','',80101,'/student/manage/edit','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_edit.do',NULL,'学生添加/编辑','',80102,'/student/manage/edit.do','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_import.do',NULL,'学生批量导入','',80105,'/student/manage/import.do','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_resetPassword.do',NULL,'学生重置密码','',80104,'/student/manage/resetPassword.do','7ed80ac9a0b14fb0ad5b01d2e04d7b90','1'),('student_manage_welcome','fa-home','欢迎页','',1,'/student/welcome','xtcd','0'),('stuexam','fa-pencil-square-o','我的考试','',100,'/exam/student','xtcd','0'),('teacher_manage_delete.do',NULL,'教师删除','',80206,'/teacher/manage/delete.do','b89c8eeea7ed4869820a7a3be974fee7','1'),('teacher_manage_detail',NULL,'教师详情','',80203,'/teacher/manage/detail','b89c8eeea7ed4869820a7a3be974fee7','1'),('teacher_manage_edit.do',NULL,'教师添加/编辑','',80202,'/teacher/manage/edit.do','b89c8eeea7ed4869820a7a3be974fee7','1'),('teacher_manage_import.do',NULL,'教师批量导入','',80205,'/teacher/manage/import.do','b89c8eeea7ed4869820a7a3be974fee7','1'),('teacher_manage_resetPassword.do',NULL,'教师密码重置','',80204,'/teacher/manage/resetPassword.do','b89c8eeea7ed4869820a7a3be974fee7','1'),('teacher_welcome','fa-home','欢迎页','欢迎',1,'/teacher/welcome','xtcd','0'),('tkgl','fa-sitemap','题库管理',NULL,9,NULL,'xtcd','0'),('xtcd','','系统菜单',NULL,0,NULL,NULL,'0'),('xtgl','fa-gears','系统管理',NULL,8,'','xtcd','0');
/*!40000 ALTER TABLE `tresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tresourcetype`
--

DROP TABLE IF EXISTS `tresourcetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tresourcetype` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tresourcetype`
--

LOCK TABLES `tresourcetype` WRITE;
/*!40000 ALTER TABLE `tresourcetype` DISABLE KEYS */;
INSERT INTO `tresourcetype` VALUES ('0','菜单'),('1','功能');
/*!40000 ALTER TABLE `tresourcetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trole`
--

DROP TABLE IF EXISTS `trole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trole` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trole`
--

LOCK TABLES `trole` WRITE;
/*!40000 ALTER TABLE `trole` DISABLE KEYS */;
INSERT INTO `trole` VALUES ('0','超管','超级管理员角色，拥有系统中所有的资源访问权限',0,NULL),('1','教师','',1,NULL),('2','学生','',2,NULL);
/*!40000 ALTER TABLE `trole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trole_tresource`
--

DROP TABLE IF EXISTS `trole_tresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trole_tresource` (
  `TROLE_ID` varchar(36) DEFAULT NULL,
  `TRESOURCE_ID` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trole_tresource`
--

LOCK TABLES `trole_tresource` WRITE;
/*!40000 ALTER TABLE `trole_tresource` DISABLE KEYS */;
INSERT INTO `trole_tresource` VALUES ('1','teacher_welcome'),('1','exam_manage_detail'),('1','xtcd'),('1','createPaper'),('1','myExam'),('1','manage_bank_question'),('1','tkgl'),('0','teacher_manage_edit.do'),('0','student_manage_detail'),('0','student_manage_import.do'),('0','exam_manage_detail'),('0','xtcd'),('0','manage_classroom_detail'),('0','b89c8eeea7ed4869820a7a3be974fee7'),('0','manage_resource'),('0','teacher_manage_import.do'),('0','jsglAdd'),('0','student_manage_delete.do'),('0','student_manage_resetPassword.do'),('0','manage_bank_subject'),('0','student_manage_edit.do'),('0','xtgl'),('0','school_manage'),('0','manage_school_detail'),('0','jsglAddPage'),('0','jsglDelete'),('0','createPaper'),('0','manage_resource_edit'),('0','manage_resource_delete'),('0','manage_bank_topic'),('0','manage_school_edit'),('0','manage_classroom_edit'),('0','student_manage_edit'),('0','classroom_manage'),('0','7ed80ac9a0b14fb0ad5b01d2e04d7b90'),('0','teacher_welcome'),('0','manage_school_edit.do'),('0','manage_bank_questionType'),('0','teacher_manage_resetPassword.do'),('0','manage_resource_detail'),('0','jsgl'),('0','jsglEdit'),('0','jsglGrantPage'),('0','myExam'),('0','manage_school_delete.do'),('0','teacher_manage_detail'),('0','manage_classroom_delete'),('0','manage_bank_question'),('0','teacher_manage_delete.do'),('0','manage_resource_edit.do'),('0','manage_classroom_edit.do'),('0','tkgl'),('2','xtcd'),('2','stuexam'),('2','student_manage_welcome');
/*!40000 ALTER TABLE `trole_tresource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-18  9:23:23
