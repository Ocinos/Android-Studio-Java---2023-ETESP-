<%@ Page Language="C#" Debug="true" %>
<%@ import Namespace="System.IO"%>
<%@ import Namespace="System.Data"%>
<%@ import Namespace="System.Data.SqlClient"%>

<script runat="server">

	public void Page_Load()
	{
		string nome = (Request.QueryString["nome"]);
		string fone = (Request.QueryString["fone"]);
		String strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
		SqlConnection objConexao = new SqlConnection(strConexao);
		String strSQL = "INSERT INTO contatos VALUES ('" + nome + "','" + fone + "')";
		SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
		objConexao.Open();
		objCommand.ExecuteNonQuery();
		strSQL = "SELECT * from contatos where nome ='"+nome+"'and fone = '"+fone+"'";
		objCommand = new SqlCommand(strSQL, objConexao);
		SqlDataReader dr;
		dr = objCommand.ExecuteReader();
		Label1.Text="Cadastro concluído com sucesso";
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