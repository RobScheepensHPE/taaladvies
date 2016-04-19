/*
 * Created on Jan 21, 2005
 */
package be.vlaanderen.sbs.s6.taaladvies.taalunie;

/**
 * specifieke taalunie exceptie
 */
public class TaalunieException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 760511705227984622L;

    /**
     * @param error de door te geven error string
     */
    public TaalunieException(String error){
        super(error);
    }
    
    /**
     * @param error de door te geven error string
     * @param throwable de door te geven throwable
     */
    public TaalunieException(String error, Throwable throwable){
        super(error, throwable);
    }
}
