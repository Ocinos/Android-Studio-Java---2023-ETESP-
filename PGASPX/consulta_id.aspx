<%@ Page Language="C#" Debug="true" %>
<%@ import Namespace="System.IO"%>
<%@ import Namespace="System.Data"%>
<%@ import Namespace="System.Data.SqlClient"%>

<script runat="server">

	public void Page_Load()
	{
		string id = (Request.QueryString["id"]); //pega o parâmetro da URL
		String strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
		SqlConnection objConexao = new SqlConnection(strConexao);
		String contato = "";
		String strSQL = "SELECT * FROM contatos WHERE id = " + id; //usa o parâmetro da URL na query
		SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
		SqlDataReader dr;
		objConexao.Open();
		dr = objCommand.ExecuteReader();
		contato = "#"; //marcador de início de dados
		while (dr.Read())
		{
			contato += (dr[0].ToString()) + ","; //id
			contato += (dr[1].ToString()) + ","; //nome
			contato += (dr[2].ToString()) + ";"; //fone
		}
		contato+="^"; //marcador de fim de dados
		Label1.Text = contato;
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