/**
 *
 */
package com.javaj2eefsd.workshop.errormessageconfig;
//IDIOM:Item-0032: Add a useful javadoc comment to each class,interface,Enum declaration.
/**
 * @author sathsubramanian ExpenseErrorMassage class is used to define the custom messages for expense module
 */
//IDIOM:Item-0008: Use descriptive Identifiers
//IDIOM:Item-0065: Upper case naming
public class ExpenseErrorMassage {
    public static final String INVALIDEXPENSEID = "expense Id is not correct";
    public static final String SEARCHKEYISNULL = "search key is null";
    public static final String INVALIDUSERID = "invalid user id or user not create any expense data";
    public static final String INVALIDKEY = "no data found for related keywords";
}
