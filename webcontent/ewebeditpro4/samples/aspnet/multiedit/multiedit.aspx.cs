using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;

namespace multiedit
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		protected eWebEditProNet.eWebEditProControl TextHTML1;
		protected eWebEditProNet.eWebEditProControl TextHTML2;
		protected System.Web.UI.WebControls.Label PreviewArea1;
		protected System.Web.UI.WebControls.Label PreviewAreaPlainText1;
		protected System.Web.UI.WebControls.Panel PlainTextPanel1;
		protected System.Web.UI.WebControls.Label PreviewArea2;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			if (this.IsPostBack)
			{
				PreviewArea1.Text = TextHTML1.Fields[0].Text;
				PlainTextPanel1.Visible = TextHTML1.IsChanged;
				if (TextHTML1.IsChanged)
				{
					PreviewAreaPlainText1.Text = TextHTML1.Fields[1].Text;
				}
				PreviewArea2.Text = TextHTML2.Text;
			}
			else
			{
				TextHTML1.Fields[0].Text = "<p>Initial content 1</p>";
				TextHTML2.Text = "<p>Initial content 2</p>";
			}
		}

		#region Web Form Designer generated code
		override protected void OnInit(EventArgs e)
		{
			//
			// CODEGEN: This call is required by the ASP.NET Web Form Designer.
			//
			InitializeComponent();
			base.OnInit(e);
		}
		
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{    
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion
	}
}
