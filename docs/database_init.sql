-- 课程选课系统数据库初始化脚本
-- 基于规范文档生成的数据库结构

-- 创建数据库
CREATE DATABASE IF NOT EXISTS course_selecting DEFAULT CHARACTER SET utf8mb4;
USE course_selecting;

-- 学院表
CREATE TABLE Institution (
    insId INT(11) PRIMARY KEY AUTO_INCREMENT,
    insName VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 学生表
CREATE TABLE Student (
    stuId INT(11) PRIMARY KEY,
    stuName VARCHAR(200) NOT NULL,
    stuPass VARCHAR(200) NOT NULL,
    insId INT(11),
    insName VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 教师表
CREATE TABLE Teacher (
    teaId INT(11) PRIMARY KEY,
    teaName VARCHAR(200) NOT NULL,
    teaPass VARCHAR(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 课程表
CREATE TABLE Course (
    classId INT(11) PRIMARY KEY AUTO_INCREMENT,
    className VARCHAR(200) NOT NULL,
    classNum INT(11) NOT NULL,
    teaId INT(11) NOT NULL,
    classChooseNum INT(11) NOT NULL DEFAULT 0,
    FOREIGN KEY (teaId) REFERENCES Teacher(teaId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 选课记录表
CREATE TABLE Course_choose (
    chooseId INT(11) PRIMARY KEY AUTO_INCREMENT,
    stuId INT(11) NOT NULL,
    classId INT(11) NOT NULL,
    score INT(11) NOT NULL DEFAULT 0,
    FOREIGN KEY (stuId) REFERENCES Student(stuId),
    FOREIGN KEY (classId) REFERENCES Course(classId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 课程学院限制表
CREATE TABLE Course_limit (
    limitId INT(11) PRIMARY KEY AUTO_INCREMENT,
    classId INT(11) NOT NULL,
    insId INT(11) NOT NULL,
    FOREIGN KEY (classId) REFERENCES Course(classId),
    FOREIGN KEY (insId) REFERENCES Institution(insId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试数据 - 学院
INSERT INTO Institution (insName) VALUES ('计算机学院');
INSERT INTO Institution (insName) VALUES ('软件学院');
INSERT INTO Institution (insName) VALUES ('信息学院');
INSERT INTO Institution (insName) VALUES ('数学学院');

-- 插入测试数据 - 学生 (学号第5位不为'1')
INSERT INTO Student (stuId, stuName, stuPass, insId, insName) VALUES 
(2018000001, '张三', '2018000001', 1, '计算机学院'),
(2018000002, '李四', '2018000002', 1, '计算机学院'),
(2018000003, '王五', '2018000003', 2, '软件学院'),
(2018000004, '赵六', '2018000004', 2, '软件学院'),
(2018000005, '钱七', '2018000005', 3, '信息学院'),
(2018000006, '孙八', '2018000006', 3, '信息学院'),
(2018000007, '周九', '2018000007', 4, '数学学院'),
(2018000008, '吴十', '2018000008', 4, '数学学院'),
(2018000009, '郑一', '2018000009', 1, '计算机学院'),
(2018000010, '王二', '2018000010', 2, '软件学院'),
(2018000011, '李三', '2018000011', 3, '信息学院');

-- 插入测试数据 - 教师 (工号第5位为'1')
INSERT INTO Teacher (teaId, teaName, teaPass) VALUES 
(2018100001, '李老师', '2018100001'),
(2018100002, '王老师', '2018100002'),
(2018100003, '张老师', '2018100003'),
(2018100004, '陈老师', '2018100004');

-- 插入测试数据 - 课程
INSERT INTO Course (className, classNum, teaId, classChooseNum) VALUES 
('Java程序设计', 50, 2018100001, 0),
('数据库原理', 40, 2018100001, 0),
('软件工程', 30, 2018100002, 0),
('计算机网络', 35, 2018100003, 0),
('高等数学', 60, 2018100004, 0);

-- 插入测试数据 - 课程学院限制
INSERT INTO Course_limit (classId, insId) VALUES 
(1, 1), (1, 2),  -- Java程序设计限制计算机和软件学院
(2, 1),          -- 数据库原理限制计算机学院
(3, 2), (3, 3),  -- 软件工程限制软件和信息学院
(4, 1), (4, 3),  -- 计算机网络限制计算机和信息学院
(5, 4);          -- 高等数学限制数学学院