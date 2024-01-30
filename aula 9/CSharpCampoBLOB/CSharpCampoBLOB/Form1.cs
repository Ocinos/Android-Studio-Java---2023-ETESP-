using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.IO;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;

namespace CSharpCampoBLOB
{
    public partial class Form1 : Form
    {
        ClasseConexao con;
        DataSet ds;
        private string caminhoImagem = null;
        int pos = 0;
        int posMAX = 0;

        public Form1()
        {
            InitializeComponent();
        }

        private void btnAdicionarImagem_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofl = new OpenFileDialog();
            ofl.Title = "Adicionar Imagem";
            ofl.Filter = "All files (*.*)|*.*";
            if (ofl.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    pictureBox1.Image = new Bitmap(ofl.OpenFile());
                    caminhoImagem = ofl.FileName;
                }
                catch (Exception)
                {
                    MessageBox.Show("Falha");
                }
            }
            ofl.Dispose();
        }

        public static byte[] ImgToByte(string camImg)
        {
            FileStream fs = new FileStream(camImg, FileMode.Open, FileAccess.Read);
            BinaryReader br = new BinaryReader(fs);
            byte[] imgByte = br.ReadBytes((int)fs.Length);
            br.Close();
            fs.Close();
            return imgByte;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            btnAdicionarImagem.Visible = false;
            btnSalvar.Visible = false;
            atualizar_dados();
        }

        private void atualizar_dados()
        {
            this.Cursor = Cursors.WaitCursor;
            pos = 0;
            con = new ClasseConexao();
            ds = new DataSet();
            ds = con.executa_sql("select nome,sobrenome,idade,img from pessoa");
            posMAX = ds.Tables[0].Rows.Count - 1;
            if (posMAX > 0)
            {
                TxtNome.Text = ds.Tables[0].Rows[0]["nome"] + "";
                TxtSobrenome.Text = ds.Tables[0].Rows[0]["sobrenome"] + "";
                TxtIdade.Text = ds.Tables[0].Rows[0]["idade"] + "";
                Byte[] byteIMG = (Byte[])(ds.Tables[0].Rows[0]["img"]);
                pictureBox1.Image = Image.FromStream(new MemoryStream(byteIMG));
            }
            this.Cursor = Cursors.Default;
        }

        private void btnNovo_Click(object sender, EventArgs e)
        {
            btnAdicionarImagem.Visible = true;
            btnSalvar.Visible = true;
            TxtNome.Text = "";
            TxtSobrenome.Text = "";
            TxtIdade.Text = "";
            if (pictureBox1.Image != null)
            {
                pictureBox1.Image.Dispose();
                pictureBox1.Image = null;
                pictureBox1.Refresh();
            }
            TxtNome.Focus();
        }

        private void btnSalvar_Click(object sender, EventArgs e)
        {
            con = new ClasseConexao();
            String nome = TxtNome.Text;
            String sobrenome = TxtSobrenome.Text;
            String idade = TxtIdade.Text;
            byte[] imagem = ImgToByte(caminhoImagem);
            SqlCommand cmd = new SqlCommand("INSERT INTO pessoa(nome,sobrenome,idade,img) VALUES(@nome,@sobrenome,@idade,@img)");
            cmd.Parameters.Add("@nome", SqlDbType.NVarChar, 50).Value = nome;
            cmd.Parameters.Add("@sobrenome", SqlDbType.NVarChar, 50).Value = sobrenome;
            cmd.Parameters.Add("@idade", SqlDbType.NVarChar,50).Value = idade;
            cmd.Parameters.Add("@img", SqlDbType.VarBinary, imagem.Length).Value = imagem;
            int x = con.executa_IncAltExcParametros(cmd);
            if(x > 0)
            {
                btnAdicionarImagem.Visible = false;
                btnSalvar.Visible = false;
                atualizar_dados();
                MessageBox.Show("Registro Salvo");
            }
            else
            {
                MessageBox.Show("Falha ao inserir registro");
            }
        }
    }
}
