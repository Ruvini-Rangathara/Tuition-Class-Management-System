DROP DATABASE IF EXISTS tcms;
CREATE DATABASE IF NOT EXISTS tcms;
SHOW DATABASES;
USE tcms;

CREATE TABLE IF NOT EXISTS Guardian(
                                       guardianNic VARCHAR(15)PRIMARY KEY,
                                       name VARCHAR(50),
                                       contactNo VARCHAR(15),
                                       email TEXT,
                                       occupation VARCHAR (50)
);

INSERT INTO Guardian VALUES
                         ('784358976V','A.P.A.Wajira',0786628467,'subhasinghe.rr2000@gmail.com','House Wife'),
                         ('724778976V','E.U.Perera',0746123460,'subhasinghe.rr2000@gmail.com','Business'),
                         ('764567976V','S.A.Nishantha',0746778467,'subhasinghe.rr2000@gmail.com','Business'),
                         ('824778955V','A.Piyantha',0346778455,'subhasinghe.rr2000@gmail.com','Business'),
                         ('794778966V','A.Santha',0346778455,'subhasinghe.rr2000@gmail.com','Business'),
                         ('844778999V','S.Kamal',0386778400,'subhasinghe.rr2000@gmail.com','Business'),
                         ('904778947V','R.Fernando',0746778455,'subhasinghe.rr2000@gmail.com','Business'),
                         ('924778953V','P.Nimali',0746778495,'subhasinghe.rr2000@gmail.com','Business'),
                         ('914358976V','S.A.Wajira',0723628467,'subhasinghe.rr2000@gmail.com','House Wife');

CREATE TABLE IF NOT EXISTS Student(
                                      studentId VARCHAR (20) PRIMARY KEY,
                                      name VARCHAR (50),
                                      nic VARCHAR (15),
                                      gender VARCHAR (10),
                                      address TEXT,
                                      contactNo VARCHAR(15),
                                      email TEXT,
                                      dob VARCHAR(20),
                                      age INT,
                                      grade VARCHAR (12),
                                      date VARCHAR(20),
                                      guardianNic VARCHAR(15),
                                      CONSTRAINT FOREIGN KEY (guardianNic) REFERENCES Guardian(guardianNic) ON DELETE CASCADE ON UPDATE CASCADE

);

INSERT INTO Student VALUES
                        ('S0001','Ruvini Perera','200176900989','Female','No:48,Miriswaththa, Mahavila, Panadura',0786628489,'ruvini925@gmail.com','2000-9-25',21,'Grade 10','2022-11-1','784358976V'),
                        ('S0002','Sachini Nayanathara','200276900989','Female','No:18,Mahavila, Panadura',0756988489,'sachininayanathara210@gmail.com','2002-2-10',20,'Grade 10','2022-11-1','724778976V'),
                        ('S0003','Banuka Perera','200376900989','Male','No:28,Pamunugama, Panadura',0766988489,'ruvini925@gmail.com','2003-2-10',19,'Grade 10','2022-11-1','764567976V'),
                        ('S0004','Ishara Fernando','200476900989','Male','No:18,Eluvila, Panadura',0716988489,'ruvini925@gmail.com','2004-2-10',18,'Grade 10','2022-11-1','824778955V'),
                        ('S0005','Piyumi Fernando','200276900989','Female','No:18,Waduramulla, Panadura',0766988489,'ruvini925@gmail.com','2002-2-10',20,'Grade 11','2022-11-1','794778966V'),
                        ('S0006','Tharindu Silva','200376900989','Male','No:18,Bolgoda, Panadura',0706988489,'as2021674@sci.sjp.ac.lk','2003-2-10',19,'Grade 10','2022-11-1','844778999V'),
                        ('S0007','Dilashan Fernando','200176900989','Male','No:12,Eluvila, Panadura',0746988489,'ruvini925@gmail.com','2001-2-10',21,'Grade 10','2022-11-1','904778947V'),
                        ('S0008','Nilan Fernando','200376900989','Male','No:23,Bolgoda, Panadura',0713488489,'ruvini925@gmail.com','2003-2-10',19,'Grade 11','2022-11-1','924778953V'),
                        ('S0009','Saminda Fernando','200276900989','Male','No:55,Wekada, Panadura',0756788489,'ruvini925@gmail.com','2022-2-10',23,'Grade 10','2022-11-1','914358976V'),
                        ('S0010','Eranda Silva','200376900989','Male','No:98,Wekada, Panadura',0776988489,'sachininayanathara210@gmail.com','2003-2-10',23,'Grade 10','2022-11-1','784358976V'),
                        ('S0011','Lahiru Pramod','200476900989','Male','No:38,Wadduwa, Panadura',0766988489,'sachininayanathara210@gmail.com','2004-2-10',18,'Grade 10','2022-11-1','724778976V'),
                        ('S0012','Dineth Miyuranga','200176900123','Male','No:98,Wadduwa, Panadura',0708928489,'subhasinghe.rr2000@gmail.com','2001-3-13',21,'Grade 11','2022-11-1','764567976V');

CREATE TABLE StudentImage(
                             studentId VARCHAR (20) PRIMARY KEY,
                             image longblob
);


CREATE TABLE IF NOT EXISTS Tutor(
                                    tutorId VARCHAR (20) PRIMARY KEY,
                                    name VARCHAR (50),
                                    nic VARCHAR (15),
                                    gender VARCHAR (10),
                                    address TEXT,
                                    contactNo VARCHAR (15),
                                    email TEXT,
                                    dob VARCHAR(20),
                                    subject VARCHAR(20),
                                    date VARCHAR(20)
);

INSERT INTO Tutor VALUES
                      ('T0001','Hasitha Madusanka','198076900V','Male','No:34,Wekada, Panadura',0716558489,'ruvini925@gmail.com','1980-5-25','Maths','2022-5-18'),
                      ('T0002','Akila Ramanayake','198376900V','Male','No:55,Bandaragama, Horana',0756534589,'ruvinisubhasinghe200009@yahoo.com','1983-4-6','Science','2022-5-15'),
                      ('T0003','Nishantha Munasinghe','199037690V','Male','No:75,Ingiriya, Horana',0776536789,'subhasinghe.rr2000@gmail.com','1987-9-6','History','2022-5-25'),
                      ('T0004','Nimali Perera','199176900V','Female','No:25,Pokunuwita, Horana',0726534456,'ruvinisubhasinghe200009@yahoo.com','1991-4-7','Dancing','2022-5-11'),
                      ('T0005','Priyanthi Silva','197976900V','Female','No:37,Horana',0746551239,'ruvinisubhasinghe200009@gmail.com','1979-5-25','Sinhala','2022-5-4');

CREATE TABLE TutorImage(
                           tutorId VARCHAR (20) PRIMARY KEY,
                           image longblob
);


CREATE TABLE IF NOT EXISTS Staff(
                                    staffId VARCHAR (20) PRIMARY KEY,
                                    name VARCHAR (75),
                                    job VARCHAR(20),
                                    nic VARCHAR (15),
                                    gender VARCHAR (10),
                                    address TEXT,
                                    contactNo VARCHAR (15),
                                    email TEXT,
                                    dob VARCHAR(20),
                                    salary DOUBLE,
                                    date VARCHAR(20)
);

INSERT INTO Staff VALUES
                      ('E0001','Priyantha Silva','Manager','784358934V','Male','No:78, Alubomulla, Panadura',0764688489,'ruvini925@gmail.com','1975-2-25',40000.00,'2022-2-6'),
                      ('E0002','Samanthi Perera','Assistant','782358934V','Female','No:44, Eluvila, Panadura',0723458489,'ruvinisubhasinghe200009@gmail.com','1979-12-5',30000.00,'2022-2-18'),
                      ('E0003','Erandi Perera','Assistant','784358234V','Female','No:35, Bolgoda, Panadura',0773458489,'subhasinghe.rr2000@gmail.com','1979-12-5',30000.00,'2022-2-18'),
                      ('E0004','Pathum Pramod','Manager','784358780V','Male','No:40, Wekada, Panadura',0783458489,'ruvini925@gmail.com','1979-12-5',30000.00,'2022-2-18'),
                      ('E0005','Nimali Perera','Cleaner','807658934V','Female','No:44, Pinwaththa, Panadura',0703458489,'subhasinghe.rr2000@gmail.com','1979-12-5',30000.00,'2022-2-18'),
                      ('E0006','Kanthi Silva','Cleaner','798758934V','Female','No:89, Bolgoda, Panadura',0759458489,'ruvini925@gmail.com','1989-4-2',30000.00,'2022-5-8');

CREATE TABLE StaffImage(
                           staffId VARCHAR (20) PRIMARY KEY,
                           image longblob
);


CREATE TABLE IF NOT EXISTS User(
                                   staffId VARCHAR (20) PRIMARY KEY,
                                   username VARCHAR (25),
                                   password VARCHAR(25),
                                   passwordHint VARCHAR(50),

                                   CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO User VALUES
                     ('E0001','silva','1234','priyantha'),
                     ('E0002','perera','2345','samanthi'),
                     ('E0004','pathum','3456','pramod');

CREATE TABLE IF NOT EXISTS Hall(
                                   hallNo VARCHAR (20) PRIMARY KEY,
                                   capacity INT,
                                   availableFacilities VARCHAR(100)
);

INSERT INTO Hall VALUES
                     ('H0001',200,'Air Conditioning, Multimedia with remote devices'),
                     ('H0002',100,'Ceiling fans'),
                     ('H0003',300,'Non Air Conditioning, Multimedia with remote devices'),
                     ('H0004',200,'Air Conditioning'),
                     ('H0005',150,'Ceiling fans'),
                     ('H0006',250,'Air Conditioning, Multimedia with remote devices');

CREATE TABLE IF NOT EXISTS Classes(
                                      classCode VARCHAR (20) PRIMARY KEY,
                                      grade VARCHAR (12),
                                      subject VARCHAR(20),
                                      tutorId VARCHAR (20),
                                      day VARCHAR (15),
                                      startTime VARCHAR (15),
                                      endTime VARCHAR (15),
                                      hallNo VARCHAR (20),
                                      classFee DOUBLE,
                                      date VARCHAR (20),

                                      CONSTRAINT FOREIGN KEY (tutorId) REFERENCES Tutor(tutorId) ON DELETE CASCADE ON UPDATE CASCADE ,
                                      CONSTRAINT FOREIGN KEY (hallNo) REFERENCES Hall(hallNo) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Classes VALUES
                        ('C0001','Grade 10','Maths','T0001','Saturday','11.00 am','1.00 pm','H0001',1200.00,'2022-11-1'),
                        ('C0002','Grade 11','Maths','T0001','Thursday','8.00 am','1.00 pm','H0002',1000.00,'2022-11-2'),
                        ('C0003','Grade 10','Sinhala','T0005','Saturday','8.00 am','1.00 pm','H0002',1500.00,'2022-11-4'),
                        ('C0004','Grade 11','Sinhala','T0005','Sunday','8.00 am','1.00 pm','H0002',1500.00,'2022-11-5'),
                        ('C0005','Grade 10','Science','T0002','Monday','8.00 am','1.00 pm','H0001',1800.00,'2022-11-3'),
                        ('C0006','Grade 11','Science','T0002','Saturday','8.00 am','1.00 pm','H0003',1200.00,'2022-11-6'),
                        ('C0007','Grade 10','History','T0003','Tuesday','11.00 am','1.00 pm','H0001',1500.00,'2022-11-7'),
                        ('C0008','Grade 11','History','T0003','Saturday','8.00 am','2.00 pm','H0004',1200.00, '2022-11-7'),
                        ('C0009','Grade 10','Dancing','T0004','Saturday','8.00 am','2.00 pm','H0005',1300.00, '2022-11-3'),
                        ('C0010','Grade 11','Dancing','T0004','Wednesday','8.00 am','2.00 pm','H0001',1200.00, '2022-11-4');


CREATE TABLE IF NOT EXISTS HallReservation(
                                              hallReservationNo VARCHAR (20) PRIMARY KEY,
                                              hallNo VARCHAR (20),
                                              classCode VARCHAR (20),
                                              day VARCHAR (15) ,
                                              startTime VARCHAR (15),
                                              endTime VARCHAR (15),

                                              CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                              CONSTRAINT FOREIGN KEY (hallNo) REFERENCES Hall(hallNo) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO HallReservation VALUES
                                ('HR0001','H0001','C0001','Saturday','11.00 am','1.00 pm'),
                                ('HR0002','H0002','C0002','Thursday','8.00 am','1.00 pm'),
                                ('HR0003','H0002','C0003','Saturday','8.00 am','1.00 pm'),
                                ('HR0004','H0002','C0004','Sunday','8.00 am','1.00 pm'),
                                ('HR0005','H0001','C0005','Monday','8.00 am','1.00 pm'),
                                ('HR0006','H0003','C0006','Saturday','8.00 am','1.00 pm'),
                                ('HR0007','H0001','C0007','Tuesday','11.00 am','1.00 pm'),
                                ('HR0008','H0004','C0008','Saturday','8.00 am','2.00 pm'),
                                ('HR0009','H0005','C0009','Saturday','8.00 am','2.00 pm'),
                                ('HR0010','H0001','C0010','Wednesday','8.00 am','2.00 pm');

CREATE TABLE IF NOT EXISTS ExtraClass(
                                         eClassCode VARCHAR (20) PRIMARY KEY,
                                         classCode VARCHAR (20),
                                         date VARCHAR (20),
                                         startTime VARCHAR (15),
                                         endTime VARCHAR (15),
                                         hallNo VARCHAR (20),

                                         CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                         CONSTRAINT FOREIGN KEY (hallNo) REFERENCES Hall(hallNo) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO ExtraClass VALUES
                           ('EC0001','C0002','2022-12-1','8.00 am','1.00 pm','H0003'),
                           ('EC0002','C0004','2022-12-2','8.00 am','1.00 pm','H0003'),
                           ('EC0003','C0005','2022-12-3','3.00 pm','5.00 pm','H0003'),
                           ('EC0004','C0003','2022-12-3','3.00 pm','5.00 pm','H0002'),
                           ('EC0005','C0008','2022-12-3','3.00 pm','5.00 am','H0002');

CREATE TABLE IF NOT EXISTS ExtraClassHallReservation(
                                                        hallReservationNo VARCHAR (20) PRIMARY KEY,
                                                        hallNo VARCHAR (20),
                                                        eClassCode VARCHAR (20),
                                                        date VARCHAR (20) ,
                                                        startTime VARCHAR (15),
                                                        endTime VARCHAR (15),

                                                        CONSTRAINT FOREIGN KEY (eClassCode) REFERENCES ExtraClass(eClassCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                                        CONSTRAINT FOREIGN KEY (hallNo) REFERENCES Hall(hallNo) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO ExtraClassHallReservation VALUES
                                          ('HR0001','H0003','EC0001','2022-12-1','8.00 am','1.00 pm'),
                                          ('HR0002','H0003','EC0002','2022-12-2','8.00 am','1.00 pm'),
                                          ('HR0003','H0003','EC0003','2022-12-3','3.00 pm','5.00 pm'),
                                          ('HR0004','H0002','EC0004','2022-12-3','3.00 pm','5.00 pm'),
                                          ('HR0005','H0002','EC0005','2022-12-3','3.00 pm','5.00 pm');

CREATE TABLE IF NOT EXISTS StudentClass(
                                           studentId VARCHAR (20),
                                           classCode VARCHAR (20),
                                           guardianNic VARCHAR(15),
                                           date VARCHAR(20),

                                           PRIMARY KEY (studentId,classCode),

                                           CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                           CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
                                           CONSTRAINT FOREIGN KEY (guardianNic) REFERENCES Guardian(guardianNic) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO StudentClass VALUES
                             ('S0001','C0001','784358976V','2022-11-1'),
                             ('S0001','C0003','784358976V','2022-11-1'),
                             ('S0001','C0005','784358976V','2022-11-2'),
                             ('S0001','C0007','784358976V','2022-11-2'),
                             ('S0001','C0009','784358976V','2022-11-2'),

                             ('S0002','C0001','724778976V','2022-11-2'),
                             ('S0002','C0003','724778976V','2022-11-1'),
                             ('S0002','C0005','724778976V','2022-11-2'),
                             ('S0002','C0007','724778976V','2022-11-2'),
                             ('S0002','C0009','724778976V','2022-11-2'),

                             ('S0003','C0001','764567976V','2022-11-2'),
                             ('S0003','C0003','764567976V','2022-11-1'),
                             ('S0003','C0005','764567976V','2022-11-2'),
                             ('S0003','C0007','764567976V','2022-11-2'),
                             ('S0003','C0009','764567976V','2022-11-2'),

                             ('S0004','C0001','824778955V','2022-11-2'),
                             ('S0004','C0003','824778955V','2022-11-1'),
                             ('S0004','C0005','824778955V','2022-11-2'),
                             ('S0004','C0007','824778955V','2022-11-2'),
                             ('S0004','C0009','824778955V','2022-11-2'),

                             ('S0006','C0001','844778999V','2022-11-2'),
                             ('S0006','C0003','844778999V','2022-11-1'),
                             ('S0006','C0005','844778999V','2022-11-2'),
                             ('S0006','C0007','844778999V','2022-11-2'),
                             ('S0006','C0009','844778999V','2022-11-2'),

                             ('S0007','C0001','824778955V','2022-11-2'),
                             ('S0007','C0003','824778955V','2022-11-1'),
                             ('S0007','C0005','824778955V','2022-11-2'),
                             ('S0007','C0007','824778955V','2022-11-2'),
                             ('S0007','C0009','824778955V','2022-11-2'),

                             ('S0009','C0001','914358976V','2022-11-2'),
                             ('S0009','C0003','914358976V','2022-11-1'),
                             ('S0009','C0005','914358976V','2022-11-2'),
                             ('S0009','C0007','914358976V','2022-11-2'),
                             ('S0009','C0009','914358976V','2022-11-2'),

                             ('S0010','C0001','784358976V','2022-11-2'),
                             ('S0010','C0003','784358976V','2022-11-1'),
                             ('S0010','C0005','784358976V','2022-11-2'),
                             ('S0010','C0007','784358976V','2022-11-2'),
                             ('S0010','C0009','784358976V','2022-11-2'),

                             ('S0011','C0001','724778976V','2022-11-2'),
                             ('S0011','C0003','724778976V','2022-11-1'),
                             ('S0011','C0005','724778976V','2022-11-2'),
                             ('S0011','C0007','724778976V','2022-11-2'),
                             ('S0011','C0009','724778976V','2022-11-2'),

                             ('S0005','C0002','794778966V','2022-11-2'),
                             ('S0005','C0004','794778966V','2022-11-1'),
                             ('S0005','C0006','794778966V','2022-11-2'),
                             ('S0005','C0008','794778966V','2022-11-2'),
                             ('S0005','C0010','794778966V','2022-11-2'),

                             ('S0008','C0002','924778953V','2022-11-2'),
                             ('S0008','C0004','924778953V','2022-11-1'),
                             ('S0008','C0006','924778953V','2022-11-2'),
                             ('S0008','C0008','924778953V','2022-11-2'),
                             ('S0008','C0010','924778953V','2022-11-2'),

                             ('S0012','C0002','764567976V','2022-11-2'),
                             ('S0012','C0004','764567976V','2022-11-1'),
                             ('S0012','C0006','764567976V','2022-11-2'),
                             ('S0012','C0008','764567976V','2022-11-2'),
                             ('S0012','C0010','764567976V','2022-11-2');


CREATE TABLE IF NOT EXISTS StudentPayment(
                                             paymentCode VARCHAR (20) PRIMARY KEY,
                                             description VARCHAR(25),
                                             classCode VARCHAR (20),
                                             fee DOUBLE,
                                             studentId VARCHAR (20),
                                             year INT,
                                             month VARCHAR (20),
                                             date VARCHAR (20),
                                             staffId VARCHAR (20),

                                             CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                             CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
                                             CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO StudentPayment VALUES
                               ('P0001','Class Fee','C0001',1200.00,'S0001', 2022 ,'November','2022-11-18','E0001'),
                               ('P0002','Class Fee','C0001',1200.00,'S0002', 2022 ,'November','2022-11-18','E0001'),
                               ('P0003','Class Fee','C0001',1200.00,'S0003', 2022 , 'November','2022-11-19','E0001'),
                               ('P0004','Class Fee','C0001',1200.00,'S0004', 2022, 'November','2022-11-15','E0001'),
                               ('P0005','Class Fee','C0001',1200.00,'S0006', 2022, 'November','2022-11-15','E0001'),
                               ('P0006','Class Fee','C0001',1200.00,'S0007', 2022, 'November','2022-11-25','E0001'),
                               ('P0007','Class Fee','C0001',1200.00,'S0009', 2022, 'November','2022-11-25','E0001'),
                               ('P0008','Class Fee','C0001',1200.00,'S0011', 2022, 'November','2022-11-23','E0001');


CREATE TABLE IF NOT EXISTS RegistrationPayment(
                                                  code INT(5) PRIMARY KEY AUTO_INCREMENT,
                                                  fee DOUBLE,
                                                  studentId VARCHAR (20),
                                                  year INT,
                                                  month VARCHAR (20),
                                                  date VARCHAR (20),
                                                  staffId VARCHAR (20),

                                                  CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
                                                  CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO RegistrationPayment (fee, studentId, year, month, date, staffId) VALUES
                                                                                 (1000.00,'S0001', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0002', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0003', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0004', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0005', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0006', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0007', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0008', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0009', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0010', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0011', 2022 ,'November','2022-11-1','E0001'),
                                                                                 (1000.00,'S0012', 2022 ,'November','2022-11-1','E0001');


CREATE TABLE IF NOT EXISTS Refund(
                                     paymentCode VARCHAR (20) PRIMARY KEY,
                                     studentId VARCHAR (20),
                                     description VARCHAR(25),
                                     amount DOUBLE,
                                     date VARCHAR (20),
                                     staffId VARCHAR (20),
                                     month VARCHAR (20),

                                     CONSTRAINT FOREIGN KEY (paymentCode) REFERENCES StudentPayment(paymentCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                     CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Refund VALUES
                       ('P0001','S0001','Registration Fee',1000.00,'2022-11-18','E0001','November'),
                       ('P0002','S0002','Registration Fee',1000.00,'2022-11-18','E0002','November');

CREATE TABLE IF NOT EXISTS StudentAttendance(
                                                studentId VARCHAR (20) ,
                                                classCode VARCHAR (20) ,
                                                presentOrAbsent VARCHAR (10),
                                                date VARCHAR (20) ,
                                                year INT,
                                                month VARCHAR (20),

                                                PRIMARY KEY(studentId, classCode, date, year ),

                                                CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                                CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE

);

INSERT INTO StudentAttendance VALUES
                                  ('S0001','C0001','Present','2022-11-5',2022,'November'),
                                  ('S0002','C0001','Present','2022-11-5',2022,'November'),
                                  ('S0003','C0001','Present','2022-11-5',2022,'November'),
                                  ('S0004','C0001','Present','2022-11-5',2022,'November'),
                                  ('S0006','C0001','Absent','2022-11-5',2022,'November'),
                                  ('S0007','C0001','Present','2022-11-5',2022,'November'),
                                  ('S0009','C0001','Absent','2022-11-5',2022,'November'),
                                  ('S0011','C0001','Present','2022-11-5',2022,'November'),

                                  ('S0001','C0001','Present','2022-11-12',2022,'November'),
                                  ('S0002','C0001','Present','2022-11-12',2022,'November'),
                                  ('S0003','C0001','Absent','2022-11-12',2022,'November'),
                                  ('S0004','C0001','Present','2022-11-12',2022,'November'),
                                  ('S0006','C0001','Absent','2022-11-12',2022,'November'),
                                  ('S0007','C0001','Present','2022-11-12',2022,'November'),
                                  ('S0009','C0001','Present','2022-11-12',2022,'November'),
                                  ('S0011','C0001','Present','2022-11-12',2022,'November'),

                                  ('S0001','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0002','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0003','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0004','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0006','C0001','Absent','2022-11-19',2022,'November'),
                                  ('S0007','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0009','C0001','Present','2022-11-19',2022,'November'),
                                  ('S0011','C0001','Present','2022-11-19',2022,'November'),

                                  ('S0001','C0001','Present','2022-11-26',2022,'November'),
                                  ('S0002','C0001','Present','2022-11-26',2022,'November'),
                                  ('S0003','C0001','Present','2022-11-26',2022,'November'),
                                  ('S0004','C0001','Present','2022-11-26',2022,'November'),
                                  ('S0006','C0001','Absent','2022-11-26',2022,'November'),
                                  ('S0007','C0001','Present','2022-11-26',2022,'November'),
                                  ('S0009','C0001','Absent','2022-11-26',2022,'November'),
                                  ('S0011','C0001','Present','2022-11-26',2022,'November');


CREATE TABLE IF NOT EXISTS TutorSalary(
                                          paymentCode VARCHAR (20) PRIMARY KEY,
                                          classCode  VARCHAR (20) ,
                                          tutorId VARCHAR (20),
                                          year INT,
                                          month VARCHAR(20),
                                          salary DOUBLE,
                                          date VARCHAR(20),
                                          payerId VARCHAR (20),

                                          CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                          CONSTRAINT FOREIGN KEY (tutorId) REFERENCES Tutor(tutorId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO TutorSalary VALUES
                            ('TP0001','C0001','T0001',2022,'November',20000.00,'2022-11-30','E0002'),
                            ('TP0002','C0002','T0001',2022,'November',25000.00,'2022-11-30','E0002'),
                            ('TP0003','C0003','T0005',2022,'November',20000.00,'2022-11-30','E0002'),
                            ('TP0004','C0004','T0005',2022,'November',30000.00,'2022-11-30','E0002'),
                            ('TP0005','C0005','T0002',2022,'November',27000.00,'2022-11-30','E0002'),
                            ('TP0006','C0006','T0002',2022,'November',29000.00,'2022-11-30','E0002'),
                            ('TP0007','C0007','T0003',2022,'November',15000.00,'2022-11-30','E0002'),
                            ('TP0008','C0008','T0003',2022,'November',12000.00,'2022-11-30','E0002'),
                            ('TP0009','C0009','T0004',2022,'November',13000.00,'2022-11-30','E0002'),
                            ('TP0010','C0010','T0004',2022,'November',15000.00,'2022-11-30','E0002');

CREATE TABLE IF NOT EXISTS NotPaidTutorSalary(
                                                 tutorId VARCHAR (20),
                                                 classCode VARCHAR (20),
                                                 year INT,
                                                 month VARCHAR(20),
                                                 salary DOUBLE,

                                                 PRIMARY KEY(tutorId, classCode),

                                                 CONSTRAINT FOREIGN KEY (classCode) REFERENCES Classes(classCode) ON DELETE CASCADE ON UPDATE CASCADE ,
                                                 CONSTRAINT FOREIGN KEY (tutorId) REFERENCES Tutor(tutorId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO NotPaidTutorSalary VALUES
                                   ('T0001','C0001',2022,'December',0),
                                   ('T0002','C0005',2022,'December',0),
                                   ('T0003','C0007',2022,'December',0),
                                   ('T0004','C0009',2022,'December',0),
                                   ('T0005','C0003',2022,'December',0),
                                   ('T0001','C0002',2022,'December',0),
                                   ('T0002','C0006',2022,'December',0),
                                   ('T0003','C0008',2022,'December',0),
                                   ('T0004','C0010',2022,'December',0),
                                   ('T0005','C0004',2022,'December',0);


CREATE TABLE IF NOT EXISTS StaffSalary(
                                          paymentCode VARCHAR (20) PRIMARY KEY,
                                          staffId VARCHAR (20),
                                          year INT,
                                          month VARCHAR(20),
                                          salary DOUBLE,
                                          date VARCHAR(20),
                                          payerId VARCHAR (20),

                                          CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO StaffSalary VALUES
                            ('EP0001','E0001',2022,'November',30000.00,'2022-11-30','E0002'),
                            ('EP0002','E0002',2022,'November',30000.00,'2022-11-30','E0002'),
                            ('EP0003','E0003',2022,'November',25000.00,'2022-11-30','E0002'),
                            ('EP0004','E0004',2022,'November',25000.00,'2022-11-30','E0002'),
                            ('EP0005','E0005',2022,'November',20000.00,'2022-11-30','E0002'),
                            ('EP0006','E0006',2022,'November',20000.00,'2022-11-30','E0002');

CREATE TABLE IF NOT EXISTS NotPaidStaffSalary(
                                                 staffId VARCHAR (20) PRIMARY KEY,
                                                 year INT,
                                                 month VARCHAR(20),
                                                 salary DOUBLE,

                                                 CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO NotPaidStaffSalary VALUES
                                   ('E0001',2022,'December',30000.00),
                                   ('E0002',2022,'December',30000.00),
                                   ('E0003',2022,'December',25000.00),
                                   ('E0004',2022,'December',25000.00),
                                   ('E0005',2022,'December',20000.00),
                                   ('E0006',2022,'December',20000.00);

CREATE TABLE IF NOT EXISTS IncomeExpenditure(
                                                code INT(5) PRIMARY KEY AUTO_INCREMENT,
                                                staffId VARCHAR (20),
                                                type TEXT,
                                                description TEXT,
                                                amount DOUBLE,
                                                year INT,
                                                month VARCHAR(20),
                                                date VARCHAR(20),

                                                CONSTRAINT FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO IncomeExpenditure (staffId ,type ,description ,amount ,year,month ,date ) VALUES
                                                                                          ('E0002','Income', 'Installment', 600000.00, 2022,  'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),
                                                                                          ('E0002','Income', 'Registration Fee', 1000.00, 2022,'November', '2022-11-1'),

                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-18'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-18'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-19'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-15'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-15'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-25'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-25'),
                                                                                          ('E0002','Income', 'Class Fee', 1200.00, 2022, 'November', '2022-11-23'),

                                                                                          ('E0002','Expenditure', 'Refund', 1000.00, 2022, 'November', '2022-11-18'),
                                                                                          ('E0002','Expenditure', 'Refund', 1000.00, 2022, 'November', '2022-11-18'),

                                                                                          ('E0002','Expenditure', 'Tutor Salary', 20000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 25000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 20000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 30000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 27000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 29000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 15000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 12000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 13000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Tutor Salary', 15000.00, 2022, 'November', '2022-11-30'),

                                                                                          ('E0002','Expenditure', 'Staff Salary', 30000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Staff Salary', 30000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Staff Salary', 25000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Staff Salary', 25000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Staff Salary', 20000.00, 2022, 'November', '2022-11-30'),
                                                                                          ('E0002','Expenditure', 'Staff Salary', 20000.00, 2022, 'November', '2022-11-30');


SHOW TABLES;
