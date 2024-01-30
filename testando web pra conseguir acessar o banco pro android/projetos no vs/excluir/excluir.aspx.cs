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
    public partial class excluir : System.Web.UI.Page
    {
        public void Page_Load(object sender, EventArgs e)
        {
            string id =Request.QueryString["id"];
            string strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=Teste; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
            SqlConnection objConexao = new SqlConnection(strConexao);
            string strSQL = $"DELETE FROM contatos where id = {id}";
            SqlCommand objCommand = new SqlCommand(strSQL, objConexao);
            objConexao.Open();
            objCommand.ExecuteNonQuery();
            Label1.Text = "Dados excluídos com sucesso";
        }
    }
}