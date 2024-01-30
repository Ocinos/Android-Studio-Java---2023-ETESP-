create database RV
go
use RV
go
create table pessoa(
id int identity primary key,
nome varchar(20),
sobrenome varchar(20),
idade varchar(3),
img varbinary(max)
)
go
--insert into pessoa(nome, sobrenome, idade)values('Teste01','T01','21'),('Teste02','T02','22'),('Teste03','T03','23'),('Teste04','T04','24')

select * from pessoa



