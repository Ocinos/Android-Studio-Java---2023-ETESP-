<%@ Page Language="C#" Debug="true" %>
<%@ import Namespace="System.IO"%>
<%@ import Namespace="System.Data"%>
<%@ import Namespace="System.Data.SqlClient"%>
<%@ import Namespace="System"%>
<%@ import Namespace="System.Text"%>

<script runat="server">

	public void Page_Load()
	{
		String strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
		//String strConexao = "Initial Catalog=Teste;Integrated Security=True;Connect Timeout=20;Data Source=" + Environment.MachineName;
		SqlConnection objConexao = new SqlConnection(strConexao);
		String concatena = ""; //utilizado para unir os campos de dados da tabela contatos do banco de dados
		String strSQL = "SELECT * FROM contatosIMG";
		SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
		SqlDataReader dr;
		objConexao.Open();
		dr = objCommand.ExecuteReader();
		concatena = "#"; //marcador de início de dados
		while (dr.Read())
		{
			concatena += (dr[1].ToString()) + ","; //campo nome do banco de dados
			concatena += (dr[2].ToString()) + ","; //campo fone do banco de dados
			concatena += (dr[3].ToString()) + ";";
			concatena += (ByteToHexString((Byte[])dr[4])) + ";"; //campo imagem do banco de dados
		}
		concatena+="^"; //marcador de fim de dados
		Label1.Text = concatena;
	}
	
	public string ByteToHexString(byte[] bytes)
	{
		char[] c = new char[bytes.Length * 2];
		int b;
		for (int i = 0; i < bytes.Length; i++) { 
			b = bytes[i] >> 4;
			c[i * 2] = (char)(55 + b + (((b-10)>>31)&-7));
			b = bytes[i] & 0xF;
			c[i * 2 + 1] = (char)(55 + b + (((b-10)>>31)&-7));
		}
		return new string(c);
	}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://ww.w3.org/TR/xhtml1/DTD/xhtml1-transitional.detd">

<html xmlns="http://www.w3.org/1999.xhtml">
<head id = "Head1" runat = "server">
 <title></title>
</head>
<body>
 <form id = "form1" runat = "server">
 <div>
	<asp:Label ID = "Label1" runat = "server"></asp:Label>
 </div>
 </form>
</body>
</html>