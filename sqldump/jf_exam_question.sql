CREATE TABLE jf_exam.question
(
    id char(32) PRIMARY KEY NOT NULL,
    is_private int(11),
    create_time bigint(20),
    codes text,
    outline text,
    options text,
    answer varchar(20),
    topic_id char(32) NOT NULL,
    create_teacher_id char(32) NOT NULL,
    question_type_id tinyint(4) NOT NULL,
    subject_id tinyint(4) NOT NULL,
    useTime int(11) DEFAULT '0'
);
CREATE INDEX question_topic_id_index ON jf_exam.question (topic_id);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('06d5176558a943c895d2687b604b7c6f', 0, 1526987790223, '', '请用代码实现一个具有年龄姓名比较类（实现Comparable接口）', null, '', 'jk_1954ef849f524472', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('19b94fd76b7248779625827222cfeaa9', 0, 1526987790242, '', '是否可以继承String类？', null, '', 'jblx_8082ce3776c44345', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('1b813d72aec74e26852892e8562876c8', 0, 1526987790326, '', '1.     执行下列代码后,哪个结论是正确的 String[] s=new String[10];', '["s[9] 为 null;","s[10] 为 “”;","s[0] 为 未定义","s.length 为10"]', '0,3', 'sz_9b5c1acce3124537', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('1c6e1e61c1a347acbb2b9df69e14c176', 0, 1526987790259, '', '有关线程的哪些叙述是对的（ ）', '["一旦一个线程被创建，它就立即开始运行。","使用start()方法可以使一个线程成为可运行的，但是它不一定立即开始运行","当一个线程因为抢先机制而停止运行，它被放在可运行队列的前面。","一个线程可能因为不同的原因停止并进入就绪状态。"]', '1,2,3', 'xc_907c2461db064a42', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('2f5e09ca0adf422f9843645f96fd93a9', 0, 1526987790086, '', '下面的哪些声明是合法的？（ ）', '["long 1 = 499","int i = 4L","float f =1.1","char d = 34.4"]', '1', 'jblx_8082ce3776c44345', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('3cf60596dfaa4e6ba33e6b9a6a3ede8d', 0, 1526987790264, 'public class Test
{
public static void main(String arg[] )
{
int i = 5;
do{
System.out.print(i);
}while(–i>5)
System.out.print(“finished”);
}
}', '以下代码执行后的输出是什么？', '["5","4","6","finished"]', '0,3', 'xh_8e8304bd1cce44ec', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('3dbe20b4ea5c404482cc6c008ce5007f', 0, 1526987790093, '', 'Java I/O程序设计中，下列描述正确的是', '["OutputStream用于写操作"," InputStream用于写操作"," I/O库不支持对文件可读可写API"," inputstreamreader可以读写中文"]', '0', 'IOl_6a3dce4d903c4cbb', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('3ff89848d00a42f39c497de3279550b4', 0, 1526987790296, '', '关于Java语言，下列描述正确的是（ ）', '["switch 不能够作用在String类型上","List， Set， Map都继承自Collection接口","Java语言支持goto语句","GC是垃圾收集器，程序员不用担心内存管理"]', '0,3', 'java', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('4161188911a5479d970cc923e3658c8f', 0, 1526987790184, '', '成员变量与局部变量的区别？', null, '', 'mddx_bb29cba2b3e84de8', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('4aa7ac827dd647088dcaa700a376c906', 0, 1526987790011, '', '提供Java存取数据库能力的包是（）', '["java.sql","java.awt","java.lang","java.swing"]', '0', 'jarb_01759a4d116746e3', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('4cae18621c0c41dca3892655c9eed3dc', 0, 1526987790322, '', '10. 下面关于变量及其范围的陈述哪些是不正确的（ ）', '["实例变量是类的成员变量","实例变量用关键字static声明","在方法中定义的局部变量在该方法被执行时创建","局部变量在使用前必须被初始化"]', '1,2,3', 'blfw_73e6a1fdd56a4397', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('5b687c82acd14ccb94d72fe1ff3d2c9e', 0, 1526987790218, '', '请简述创建线程的3种方式', null, '', 'xc_907c2461db064a42', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('662d2e1309044ff992a0f321fd7bf0e2', 0, 1526987790167, '', '下列描述中，哪些符合Java语言的特征', '["支持跨平台(Windows，Linux，Unix等)"," GC(自动垃圾回收)，降低了了代码安全性"," 支持类C的指针运算操作"," 不支持与其它语言书写的程序进行通讯"]', '0', 'javajbzsd_bc2215d7065c4a06', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('6bbc3edd89134d34bcf2ef94c4c77451', 0, 1526987790306, '', 'Java网络程序设计中，下列正确的描述是（ ）', '["Java网络编程API建立在Socket基础之上","Java网络接口只支持TCP以及其上层协议","Java网络接口只支持UDP以及其上层协议","Java网络接口支持IP以上的所有高层协议"]', '0,3', 'JAVAWEB_e3b9ab9fe7734f06', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('6e4cb61ba6cf4d659273423d2c175253', 0, 1526987790300, '', '如下哪些不是java的关键字？（ ）', '["const","TRUE","FALSE","this"]', '1,2,3', 'gjz_e3fad80dc8ef40c6', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('72b5c16f90ff4043b214850482a759f3', 0, 1526987790237, '', '请简述int和Integer有什么区别？', null, '', 'bzl_d4099519eb2d43a4', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('75e64571f85c45d99c670631cbd515bf', 0, 1526987790335, '', '在服务器的网络编程中，解决会话跟踪的方法有：', '["使用Cookie","使用URL重写","使用隐藏的表单域","以上方法都不能单独使用"]', '0,1,2', 'JAVAWEB_e3b9ab9fe7734f06', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('7f3c1d02ed124a3cbd8746e2271ea047', 0, 1526987790189, '', '静态代码块，构造代码块，构造方法的执行顺序是什么以及执行特点', null, '', 'mddx_bb29cba2b3e84de8', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('8e7e39f371e04c119e8438b8d691abf2', 0, 1526987790340, '', '有关JSP隐式对象，以下（ ）描述正确', '["隐式对象是WEB容器加载的一组类的实例，可以直接在JSP页面使用","不能通过config对象获取ServletContext对象","response对象通过sendRedirect方法实现重定向","只有在出错处理页面才有exception对象"]', '0,2,3', 'JSP_3bbeab3be6ed4521', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('8fe59f00536743989915aa87b3e7eaef', 0, 1526987790228, '', '请简述工厂模式', null, '', 'javasjms_7f07d371cb6c4f56', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('93b556acd30c450385e0e39ade4ddc59', 0, 1526987789968, 'public class Test1 {
 public static void main(String[] args) {
   int x = 4;
   int y = (--x)+(x--)+(x*10);
   System.out.println("x = " + x + ",y = " + y);
 }
}', '看程序说结果
', '["25","26","27","28"]', '2', 'zzzj_5cb5e2b6dcdb48f8', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('a3a8feaed48f470198db75c1997283dd', 0, 1526987790344, '', '下面属于javascript对象的有：( )', '["Window","Document","Form","String"]', '0,2,3', 'javascript_8bcf90b016bb4300', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('a932399c1f984ab688e15b898cdc30d8', 0, 1526987790273, '', '选择Java语言中的基本数据类型（ ）', '["byte","Integer","String","char"]', '0,3', 'jbsjlx_7fd27f4528204900', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('c32aeaf99764474fb607fd37288f3c81', 0, 1526987790331, '', '运行jsp需要安装_______Web服务器。', '["Apache","tomcat","WebLogic","IIS"]', '1,2,3', 'JAVAWEB_e3b9ab9fe7734f06', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('d66a073a81f1472aaa4b76e0896d190c', 0, 1526987790312, '', '下列哪些是J2EE的体系（ ）', '["JSP","JAVA","Servlet","WebService"]', '0,2,3', 'j2ee_e9d9f3d2a5964d9c', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('d7cf591affe74a25b0325212e063fa4a', 0, 1526987790159, '', '关于Java语言，下列描述正确的是', '["switch 不能够作用在String类型上"," List， Set， Map都继承自Collection接口"," Java语言支持goto语句"," GC是垃圾收集器，程序员不用担心内存管理"]', '3', 'javajbzsd_bc2215d7065c4a06', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('d85d3444b5ae41c3a5bf15784c4e111a', 0, 1526987790173, '', '编写方法，获取到数组中最大的元素，返回该元素的值', null, '', 'sz_9b5c1acce3124537', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('e1afea751c6243358e2ad0afbcfb144c', 0, 1526987790100, 'class Super { public int getLength() {return 4;}

}

public class Sub extends Super { public long getLength() {return 5;}

public static void main (String[]args) {

Super sooper = new Super (); Super sub = new Sub(); System.out.printIn(sooper.getLength()+ “，” + sub.getLength() };

}', '下述代码的执行结果是', '["4,4","5,5","4,5",".无法编译"]', '3', 'jc_399e6cc2553f4d84', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('e3f98eb9028b4360a46540c5d814c8b8', 0, 1526987789977, '', '关于sleep()和wait()，以下描述错误的一项是（ ）', '["sleep是线程类（Thread）的方法，wait是Object类的方法","sleep不释放对象锁，wait放弃对象锁","sleep暂停线程、但监控状态仍然保持，结束后会自动恢复","wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。"]', '3', 'xc_907c2461db064a42', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('ea5e2e670109417da1e1522129b05215', 0, 1526987790249, '', 'Java 中会存在内存泄漏吗，请简单描述。', null, '', 'javaGCjz_c5e95912e0db4512', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('ec08fba5dd0149cd820a3160665d4863', 0, 1526987790279, '', '从下列选项中选择正确的Java表达式（ ）', '["int k=new String(“aa”)","String str=String(“bb”)","char c=74;","long j=8888;"]', '1,2,3', 'sjlx_f1f570c20115498b', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('f95a77b9cc2f439080950918cbeaae8e', 0, 1526987790210, '', '请编写一个单例代码', null, '', 'dl_4075a313bf4643b0', 'f680ef403ded42edacf764d0a959b107', 4, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('f96b5a2aef1347039153811c6bffaa04', 0, 1526987790317, '', '下面中哪两个可以在A的子类中使用：（ ）
class A {
protected int method1 (int a, int b) {
return 0;
}
}
', '[" public int method 1 (int a, int b) { return 0; }","private int method1 (int a, int b) { return 0; }"," private int method1 (int a, long b) { return 0; }","public short method1 (int a, int b) { return 0; }"]', '0,2,3', 'zx_7d5f861f42694043', 'f680ef403ded42edacf764d0a959b107', 3, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('f98e4e75723e41c4bcb6d7a48c91ad0d', 0, 1526987790079, '', '从下面四段（A，B，C，D）代码中选择出正确的代码段（）', '["abstract class Name {\\n\\nprivate String name;\\n\\npublic abstract boolean isStupidName(String name) {}\\n\\n}"," public class Something {\\n\\nvoid doSomething () {\\n\\nprivate String s = ̶”;\\n\\nint l = s.length();\\n\\n}\\n\\n}"," public class Something {\\n\\npublic static void main(String[] args) {\\n\\nOther o = new Other();\\n\\nnew Something().addOne(o);\\n\\n}\\n\\npublic void addOne(final Other o) {\\n\\no.i++;\\n\\n}\\n\\n}\\n\\nclass Other {\\n\\npublic int i;\\n\\n}"," public class Something {\\n\\npublic int addOne(final int x) {\\n\\nreturn ++x; }\\n\\n}"]', '3', 'mddx_bb29cba2b3e84de8', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);
INSERT INTO jf_exam.question (id, is_private, create_time, codes, outline, options, answer, topic_id, create_teacher_id, question_type_id, subject_id, useTime) VALUES ('ffb9af739e714a60ad0c7525673045a3', 0, 1526987789989, '', '下面哪个可以改变容器的布局？( )', '["setLayout(aLayoutManager","addLayout(aLayoutManager)"," layout(aLayoutManager)"," setLayoutManager(aLayoutManager)"]', '1', 'txjm_a1867cb08e204c97', 'f680ef403ded42edacf764d0a959b107', 2, 9, null);