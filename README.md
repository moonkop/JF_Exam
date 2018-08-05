
# todo
~~修改考试时复选框状态恢复~~

~~试卷添加转异步~~

~~所有edit jsp改异步~~

~~试卷修改提交后返回~~

~~显示考试使用倒序~~

在没有简答题时自动生成报告

MONGO连接不稳定

知识点加载速度提高

随机出题

完善资源管理中学生资源

# 项目git地址：
http://git.moonkop.com/root/JF_Exam

# 环境要求
若有前端开发需求，包括修改js 修改css等 需要node环境。

初次进行前端开发，请在/webapp目录下 运行npm install以安装依赖库。

该项目前端工程使用gulp构建，日常开发请运行watch任务。

请勿修改/webapp/dist目录下的任何文件，该文件夹所有文件为gulp自动生成。

本项目css由预处理器less生成，代码位于/webapp/less文件夹中，开启gulp watch任务后自动在dist目录下实时更新css文件

mysql mongo数据库地址在配置文件中已被配置为远程服务器，若需要切换为本地数据库请修改配置文件application-context.xml与mongodb.properties

