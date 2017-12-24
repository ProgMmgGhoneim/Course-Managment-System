
package Check;


public class validation {
    public static boolean isEmpty(String ... text){
     for(String s:text){
         if(s.isEmpty())
         {
             return true;
         }
     }
     return false;
    }
    
    public static boolean isDigit(String ... text){
        for(String s:text){
        if(!s.matches("[0-9]"))
        
            return false;
        
        }
    
    return true;
    }
    
    public static boolean isText(String ... text){
        for(String s:text){
        if(!s.matches("[a-zA-z]"))
        {
            return false;
        }
        }
    
    return true;
    }
    
}
