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
using System.Xml;
using System.Xml.XPath;
using System.Xml.Xsl;

namespace multiconfig
{
	/// <summary>
	/// Summary description for WebForm1.
	/// </summary>
	public class WebForm1 : System.Web.UI.Page
	{
		protected eWebEditProNet.eWebEditProControl EWebEditProControl1;
		protected System.Web.UI.WebControls.Label Label1;
	
		private void Page_Load(object sender, System.EventArgs e)
		{
			// Put user code to initialize the page here
			XmlDocument xmldoc = new XmlDocument();
			xmldoc.Load(Context.Server.MapPath("/ewebeditpro4/config.xml"));

			XslTransform objXSLT = new XslTransform();
			objXSLT.Load(Context.Server.MapPath("/ewebeditpro4/samples/aspnet/multiconfig/removecmds.xslt"));
					
			XsltArgumentList objArgList = new XsltArgumentList();
			objArgList.AddParam("commands", "", "cmdunderline cmdmfumedia");

			System.IO.StringWriter objStrWriter = new System.IO.StringWriter();
			XPathNavigator nav = xmldoc.CreateNavigator();
			objXSLT.Transform(nav, objArgList, objStrWriter);
					
			string strXML = objStrWriter.GetStringBuilder().ToString();
			strXML = HttpUtility.HtmlEncode(strXML);
			//strXML = strXML.Replace("\x0d\x0a", ""); // remove line breaks if XSLT <xsl:output indent="yes">

			EWebEditProControl1.Config = strXML; 

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
