<%@ Page Language="C#" Debug="true" %>
<%@ import Namespace="System.IO"%>
<%@ import Namespace="System.Data"%>
<%@ import Namespace="System.Data.SqlClient"%>

<script runat="server">
	public void Page_Load()
        {
            string id =Request.QueryString["id"];
            string strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
            SqlConnection objConexao = new SqlConnection(strConexao);
            string strSQL = "DELETE FROM contatos where id = " + id;
            SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
            objConexao.Open();
            objCommand.ExecuteNonQuery();
            Label1.Text = "Dados excluídos com sucesso";
        }
		</script>
		<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="Label1" runat="server"></asp:Label>
        </div>
    </form>
</body>
</html>
	