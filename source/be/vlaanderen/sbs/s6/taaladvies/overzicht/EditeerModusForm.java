package be.vlaanderen.sbs.s6.taaladvies.overzicht;

import org.apache.struts.action.ActionForm;

public class EditeerModusForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = -3960406681588417219L;
	boolean useEopro = false;
	String forwardURL = "";
		
    public String getForwardURL() {
        return forwardURL;
    }
    public void setForwardURL(String forwardURL) {
        this.forwardURL = forwardURL;
    }
    /**
     * @return boolean to use eopro editeer modus
     */
    public boolean getUseEopro() {
        return useEopro;
    }
    
    /**
     * @param useEopro boolean to use eopro editeer modus
     */
    public void setUseEopro(boolean useEopro) {
        this.useEopro = useEopro;
    }
}


