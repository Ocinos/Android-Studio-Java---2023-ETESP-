using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Data;
using System.Data.SqlClient;

namespace excluir
{
    public partial class update : System.Web.UI.Page
    {
        public void Page_Load()
        {
            string id = Request.QueryString["id"];
            string nome = Request.QueryString["nome"];
            string fone = Request.QueryString["fone"];
            string strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
            SqlConnection objConexao = new SqlConnection(strConexao);
            string strSQL = "UPDATE contatos set nome = '" + nome + "', fone = '" + fone + "' where id = " + id;
            SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
            objConexao.Open();
            objCommand.ExecuteNonQuery();
            Label1.Text = "Dados atualizados com sucesso";
        }
    }
}