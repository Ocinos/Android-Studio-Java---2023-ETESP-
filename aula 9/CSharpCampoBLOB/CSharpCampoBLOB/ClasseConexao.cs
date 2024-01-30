using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;

    public class ClasseConexao
    {
        SqlConnection conexao = new SqlConnection();

        private SqlConnection conectar()
        {
            try
            {
                String strConexao = "Password=etesp; Persist Security Info=True; User ID=sa; Initial Catalog=RV; Data Source=" + Environment.MachineName + "\\SQLEXPRESS";
                conexao.ConnectionString = strConexao;
                conexao.Open();
                return conexao;
            }
            catch (Exception)
            {
                desconectar();
                return null;
            }
        }

        public void desconectar()
        {
            try
            {
                if ((conexao.State == ConnectionState.Open))
                {
                    conexao.Close();
                    conexao.Dispose();
                    conexao = null;
                }
            }
            catch (Exception) { }
        }

        public DataSet executa_sql(String comando_sql)
        {
            try
            {
                SqlDataAdapter adaptador = new SqlDataAdapter(comando_sql, conectar());
                DataSet ds = new DataSet();
                adaptador.Fill(ds);
                return ds;
            }
            catch (Exception)
            {
                return null;
            }
            finally
            {
                desconectar();
            }
        }

        public int executa_IncAltExcParametros(SqlCommand comando) //incluir, alterar, excluir
        {
			int retorno = 0;
            try
            {
                comando.Connection = conectar();  //adiciona a conexão ao SQLCommand
                retorno = comando.ExecuteNonQuery(); //devolve o número de linhas afetadas no banco
            }
            catch (Exception){}
            desconectar();
            return retorno;
        }


        public bool manutencao(String comando_sql) //teste
        {
            try
            {
                SqlCommand comando = new SqlCommand();
                comando.CommandText = comando_sql;
                comando.Connection = conectar();
                conexao.Open();
                comando.ExecuteScalar();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
            finally
            {
                desconectar();
            }
        }
    }
