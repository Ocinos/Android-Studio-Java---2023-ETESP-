use Teste

create table cliente(
id int primary key identity,
nome varchar(20),
horas int,
pagamento numeric,
)

insert cliente(nome, horas, pagamento)
values('paia',8, 200.00),('shrek',2,200.00),('arduino',12,10.00),('chopper',3,01.00)




