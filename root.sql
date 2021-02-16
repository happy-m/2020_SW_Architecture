show databases;
create database PKJInsurance;
use PKJInsurance;
show tables;
alter table accidentReception add statement boolean default false;
 SELECT * from insurance where statement = 0;
SELECT * from insurance where statement = 1;
select * from fireInsuranceRegistrationInfo;
select * from employee;
drop table insurance;
drop table fireInsuranceRegistrationInfo;
drop table insuranceApprove;
drop table rate;
drop table underWriter_Rate;

create table employee(
employeeID int auto_increment not null,
name varchar(20),
age int,
address varchar(30),
phoneNumber varchar(15),
emailAddress varchar(30),
role varchar(10),
primary key (employeeID)
);
SELECT * from insurance where statement = 0;
delete from insurance where insuranceName is NULL;
insert into employee(name, age, address, phoneNumber, emailAddress, role) values("김정필", 24, "서울시 동대문구 이문동 150-2", "010-3004-8999", "asd@naver.com", "보험승인자");
insert into employee(name, age, address, phoneNumber, emailAddress, role) values("서지성", 22, "서울시 중랑구 동일로 170-1", "010-3948-8878", "sdf@naver.com", "보험개발자");
insert into employee(name, age, address, phoneNumber, emailAddress, role) values("김정호", 22, "서울시 중랑구 동일로 170-4", "010-3948-8890", "dfg@naver.com", "언더라이터");
insert into employee(name, age, address, phoneNumber, emailAddress, role) values("최성운", 25, "서울시 중랑구 동일로 170-12", "010-4448-8878", "fgh@naver.com", "보험사후처리사");
select * from employee;

	
delete from fireInsuranceRegistrationInfo where name="1";
delete from accidentReception where insuranceName="안심화재보험";
use pkjInsurance;
select * from insurance;
select * from accidentReception;
select * from fireInsuranceRegistrationInfo;
select * from rate;
select * from insurance where payIn = 3;

create table insurance (
insuranceID int auto_increment not null,
insuranceName varchar(20),
subscriptionTarget varchar(30),
insurancePeriod varchar(20),
paymentMethod varchar(20), /*보험료 내는 방법*/
payIn int, #내는 보험료
insuranceMoney int, #받는 보험료 
note varchar(50), #참고사항
specialSubscription varchar(50),
longTerm boolean default 0,
statement boolean default 0,
insuranceCategory varchar(30),
primary key(insuranceID)
);

select job, rate from rate;
insert into rate(job, rate) values ("현장직","1.2");
drop table rate;
drop table accidentReception;
 select * from accidentReception;
 select * from insurance;
 select * from specialInsuranceRegistrationInfo;
 select clientName, registrationNumber, gender, phoneNumber, email, accidentContent, accidentReception.insuranceName, insuranceCategory
 from accidentReception, insurance where insurance.insuranceName = accidentReception.insuranceName;
 select insuranceCategory from fireInsuranceRegistrationInfo,
 onTheSeaInsuranceRegistrationInfo, carInsuranceRegistrationInfo, specialInsuranceRegistrationInfo, accidentReception
 where accidentReception.clientName = fireInsuranceRegistrationInfo.name and
 accidentReception.clientName = carInsuranceRegistrationInfo.name and
 accidentReception.clientName = onTheSeaInsuranceRegistrationInfo.name and
 accidentReception.clientName = specialInsuranceRegistrationInfo.name;
 
 select *
 from accidentReception, insurance where insurance.insuranceName = accidentReception.insuranceName;
select * from insurance;
select * from fireInsuranceRegistrationInfo;
delete from fireInsuranceRegistrationInfo where insuranceName is NULL;
create table fireInsuranceRegistrationInfo (
FInsuranceID int auto_increment not null,
insuranceName varchar(10),
name varchar(20),
residentRegistrationNumber varchar(20),
age varchar(10),
gender varchar(5),
phoneNumber varchar(15),
address varchar(30),
accountNumber varchar(20),
email varchar(30),
job varchar(10),
accidentHistory varchar(200),
longTerm boolean default 0,
RegistrationDate varchar(20),
property varchar(10),
statement boolean default 0,
insuranceMoney varchar(10),
insuranceCategory varchar(30),
primary key (FInsuranceID)
);

create table carInsuranceRegistrationInfo (
CInsuranceID int auto_increment not null,
insuranceName varchar(10),
name varchar(20),
residentRegistrationNumber varchar(20),
age varchar(10),
gender varchar(5),
phoneNumber varchar(15),
address varchar(30),
accountNumber varchar(20),
email varchar(30),
job varchar(10),
accidentHistory varchar(200),
longTerm boolean default 0,
RegistrationDate varchar(20),
carModel varchar(50),
carNumber varchar(10),
statement boolean default 0,
insuranceMoney varchar(30),
primary key (CInsuranceID)
);
select payIn from insurance where insuranceName = "화재2";
create table onTheSeaInsuranceRegistrationInfo (
OTSInsuranceID int auto_increment not null,
name varchar(20),
insuranceName varchar(10),
residentRegistrationNumber varchar(20),
age varchar(10),
gender varchar(5),
phoneNumber varchar(15),
address varchar(30),
accountNumber varchar(20),
email varchar(30),
job varchar(10),
accidentHistory varchar(200),
longTerm boolean default 0,
RegistrationDate varchar(20),
onTheSeaInsuranceType varchar(30),
seaProperty varchar(10),
statement boolean default 0,
insuranceMoney varchar(30),
primary key (OTSInsuranceID)
);


insert into specialInsuranceRegistrationInfo(insuranceName,name, residentRegistrationNumber, age, gender, phoneNumber, address,accountNumber, email, job, accidentHistory, longTerm) 
    	values(1,1,1,1,"여자",1,1,1,1,1,"사고이력없음",1);
select * from specialInsuranceRegistrationInfo;
create table specialInsuranceRegistrationInfo (
SInsuranceID int auto_increment not null,
name varchar(20),
insuranceName varchar(10),
residentRegistrationNumber varchar(20),
age varchar(10),
gender varchar(5),
phoneNumber varchar(15),
address varchar(30),
accountNumber varchar(20),
email varchar(30),
job varchar(10),
accidentHistory varchar(200),
longTerm boolean default 0,
RegistrationDate varchar(20),
specialInsuranceType varchar(30),
specialProperty varchar(20),
statement boolean default 0,
insuranceMoney varchar(30),
primary key (SInsuranceID)
);

SELECT * from insurance where statement = false;
   
create table financialManagementCommittee (
primary key (Fid),
Fid int auto_increment not null,
name varchar(20),
emailAddress varchar(30)
);
select job,rate from rate where job = "기장";
select * from rate;
Show columns from rate;
drop table rate;
alter table rate add rate float;
insert into rate(job, rate) values ("군인", "2.1");
create table rate (
rateID int auto_increment not null,
job varchar(20),
rate varchar(10),
primary key (rateID)
);

alter table specialInsuranceRegistrationInfo add insuranceCategory varchar(30);
alter table accidentReception add insuranceName varchar(30);
select * from accidentReception;
create table accidentReception(
accidentId int auto_increment not null,
employeeID int,
insuranceName varchar(30),
clientName varchar(20),
registrationNumber varchar(20),
gender varchar(10),
phoneNumber varchar(15),
email varchar(30),
accidentContent varchar(300),
primary key (accidentId),
foreign key (employeeID) references employee (employeeID)
);

create table fireInsuranceaccidentReception(
accidentId int,
FInsuranceID int,
date varchar(50),
foreign key (FInsuranceID) references fireInsuranceRegistrationInfo (FInsuranceID),
foreign key (accidentId) references accidentReception (accidentId)
);

create table onInsuranceaccidentReception(
accidentId int,
OTSInsuranceID int,
date varchar(50),
foreign key (OTSInsuranceID) references onTheSeaInsuranceRegistrationInfo (OTSInsuranceID),
foreign key (accidentId) references accidentReception (accidentId)
);

create table carInsuranceaccidentReception(
accidentId int,
CInsuranceID int,
date varchar(50),
foreign key (CInsuranceID) references carInsuranceRegistrationInfo (CInsuranceID),
foreign key (accidentId) references accidentReception (accidentId)
);

create table specialInsuranceaccidentReception(
accidentId int,
SInsuranceID int,
date varchar(50),
foreign key (SInsuranceID) references specialInsuranceRegistrationInfo (SInsuranceID),
foreign key (accidentId) references accidentReception (accidentId)
);

create table fireInsuranceRegistration(
FInsuranceID int,
insuranceID int,
rate float,
totalPrice int, 
date varchar(50),
foreign key (FInsuranceID) references fireInsuranceRegistrationInfo (FInsuranceID),
foreign key (insuranceID) references insurance (insuranceID)
);
select * from onTheSeaInsuranceRegistrationInfo;
create table specialInsuranceRegistration(
SInsuranceID int,
insuranceID int,
rate float,
totalPrice int, 
date varchar(50),
foreign key (SInsuranceID) references specialInsuranceRegistrationInfo (SInsuranceID),
foreign key (insuranceID) references insurance (insuranceID)
);

create table carInsuranceRegistration(
CInsuranceID int,
insuranceID int,
rate float,
totalPrice int, 
date varchar(50),
foreign key (CInsuranceID) references carInsuranceRegistrationInfo (CInsuranceID),
foreign key (insuranceID) references insurance (insuranceID)
);

create table onTheSeaInsuranceRegistration(
OTSInsuranceID int,
insuranceID int,
rate float,
totalPrice int, 
date varchar(50),
foreign key (OTSInsuranceID) references onTheSeaInsuranceRegistrationInfo (OTSInsuranceID),
foreign key (insuranceID) references insurance (insuranceID)
);

create table underWriter_rate (
employeeID int not null,
rateID int not null,
foreign key (employeeID) references employee (employeeID),
foreign key (rateID) references rate (rateID)
);
create table insuranceApprove (
insuranceID int,
employeeID int,
foreign key (insuranceID) references insurance (insuranceID),
foreign key (employeeID) references employee (employeeID)
);