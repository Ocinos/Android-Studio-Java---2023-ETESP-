create database Teste
go
use Teste
go
create table contatos(
id int primary key identity,
nome varchar(80),
fone varchar(20)
)

insert into contatos values('Aluno1','111111')
insert into contatos values('Aluno2','222222')

select * from contatos

