create database quiz
go
use quiz
create table tblPergunta
(
id int primary key identity,
pergunta varchar(100),
)
go 
create table tblResposta
(
id int primary key identity,
resposta varchar(50),
correta bit,
pergunta int foreign key references tblPergunta(id)
)

insert tblPergunta(pergunta)
values('Qual a carta assinatura de Seto Kaiba?'),('Qual o ataque do Dragão Branco de Olhos Azuis?'),('Qual carta tem o efeito "Compre Duas Cartas"?'),('Qual o nome do Protagonista de Yu-Gi-Oh GX?')

insert tblResposta(resposta,correta,pergunta)
values('Dragão Branco de Olhos Azuis',1,1),('Mago Negro',0,1),('Exódia, o Proibido',0,1),('Dragão Negro de Olhos Vermelhos',0,1),('2000',0,2),('2500',0,2),('3000',1,2),('4000',0,2),('Pote da Avaríce',0,3),('Pote da Ganância',1,3),('Pote da Prosperidade',0,3),('Pote da Extravagância',0,3),('Yugi Muto',0,4),('Yusei Fudo',0,4),('Jack Atlas',0,4),('Jaden Yuki',1,4)

select P.pergunta, R.resposta, R.correta from tblResposta R inner join tblPergunta P on P.id = R.pergunta and R.pergunta = 1



