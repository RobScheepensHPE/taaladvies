package be.vlaanderen.sbs.s6.framework.model;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Base class for resources.
 * @author Gert Vandenbemden
 * @version 1.0
 */
public class BaseResource
{
    private java.lang.String name;

    /**
     * DatabaseResource constructor.
     */
    public BaseResource()
    {
        name = "";
    }

    /**
     * Retrieves the name of the resource.
      * @return java.lang.String
     */
    public java.lang.String getName()
    {
        return name;
    }

    /**
     * Sets the name of this resource.
      * @param newName java.lang.String
     */
    public void setName(java.lang.String newName)
    {
        name = newName;
    }

    /**
     * Returns a String that shows a fieldname/value representation of this object.
     * @return a string Representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            // --- Get information about this Object's getter methods ---
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propDesc = beanInfo.getPropertyDescriptors();
            sb.append("[");

            for (int i = 0; i < beanInfo.getPropertyDescriptors().length; i++)
            {
                // --- Create an empty array because the getter method doesn't take arguments ---
                Object[] args = new Object[0];

                try
                {
                    // --- Retrieve the value by invoking the field's getter method ---
                    sb
                        .append(propDesc[i].getName())
                        .append("=")
                        .append(propDesc[i].getReadMethod().invoke(this, args))
                        .append((i == beanInfo.getPropertyDescriptors().length - 1) ? "" : "; ");
                }
                catch (InvocationTargetException e)
                {
                    System.err.println(
                        "setFieldValue(Object obj, String fieldName) - InvocationTargetException: ");
                    e.printStackTrace(System.err);
                }
                catch (IllegalAccessException e)
                {
                    System.err.println(
                        "setFieldValue(Object obj, String fieldName) - IllegalAccessException: ");
                    e.printStackTrace(System.err);
                }
            }
            sb.append("]");
        }
        catch (IntrospectionException e)
        {
            System.err.println(
                "setFieldValue(Object obj, String fieldName) - IntrospectionException: ");
            e.printStackTrace(System.err);
        }

        return sb.toString();
    }
}