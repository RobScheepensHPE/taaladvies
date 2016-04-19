package be.vlaanderen.sbs.s6.taaladvies;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.appconf.AppConf;
import be.vlaanderen.sbs.s6.taaladvies.model.Taalvraag;

public class UnloadTaalunie {

	public static void main(String[] args) {

		AppLogger al = AppLogger.getInstance();

		if(al == null)
			System.err.println(
				new StringBuffer("::FATAL ERROR - ")
					.append(" - Could not initialize Applogger. All logging disabled !!!")
					.toString());

		try
		{
		    if (al != null) {
			al.info("Application configured successfully.\n");
		    }
			AppConf.init();
		}
		catch (Exception e)
		{
		    if (al != null) {
			al.fatal("Unable to initialize AppConf", e);
		    }
		}

//		try
//		{
//			ConManagerFactory cmf = new ConManagerFactory();
//			cmf.setConConfig(new be.vlaanderen.sbs.s6.taaladvies.db.conmanager.AppConfConConfig("taaladviesDB"));
//			cmf.getConManager();
//		}
//		catch (SQLException ex)
//		{
//
//		}

		try {
			java.util.ArrayList<String> taalvragen = Taalvraag.findAllToOfload();
			Date datum = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
			String datumString = df.format(datum);
			FileWriter out = new FileWriter("ofload" + datumString + ".csv");
			StringBuffer completeString = new StringBuffer();

			for (int i = 0, j = taalvragen.size(); i < j; i++) {
				String taalvraag = taalvragen.get(i);
				completeString.append(taalvraag);
				completeString.append("\n");
			}
			out.write(completeString.toString());
			out.close();
		}
		catch(IOException io)
		{
		 	System.err.println("error in Unloader " + io.toString());
		}

		catch(Exception e)
		{
	 		System.err.println("error in Unloader " + e.toString());
		}
	}
}

